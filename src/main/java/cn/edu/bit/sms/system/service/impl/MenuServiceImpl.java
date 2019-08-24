package cn.edu.bit.sms.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.edu.bit.sms.system.constant.Role;
import cn.edu.bit.sms.system.dto.TreeNode;
import cn.edu.bit.sms.system.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	public static final TreeNode SCORE_MGR_NODE = new TreeNode("1", "成绩管理", false, false); // 成绩管理
	public static TreeNode COURSE_NODE = new TreeNode("101", "课程管理", true, false);
	public static TreeNode STUDENT_COURSE_NODE = new TreeNode("102", "课程安排", true, false);
	public static TreeNode TEACHER_SCORE_NODE = new TreeNode("103", "学生分数", true, false);
	public static TreeNode STUDENT_SCORE_NODE = new TreeNode("104", "分数查询", true, false);
	
	public static final TreeNode SYSTEM_MGR_NODE = new TreeNode("2", "系统管理", false, false); // 系统管理
	public static TreeNode TEACHER_NODE = new TreeNode("201", "教师管理", true, false);
	public static TreeNode STUDENT_NODE = new TreeNode("202", "学生管理", true, false);
	public static TreeNode UPDATE_TEACHER_NODE = new TreeNode("203", "修改信息", true, false);
	public static TreeNode UPDATE_STUDENT_NODE = new TreeNode("204", "修改信息", true, false);
	
	TreeNode treeNode11 = new TreeNode("201", "教师管理", true, false);
	TreeNode treeNode12 = new TreeNode("202", "学生管理", true, false);
	
	public static final Map<String, List<TreeNode>> ROLE_PARENT_MAP = new HashMap<>();
	
	static {
		COURSE_NODE.setParentId("1");
		COURSE_NODE.setLinkUrl("bit.score.CoursePanel");
		
		STUDENT_COURSE_NODE.setParentId("1");
		STUDENT_COURSE_NODE.setLinkUrl("bit.score.StudentCoursePanel");
		
		TEACHER_SCORE_NODE.setParentId("1");
		TEACHER_SCORE_NODE.setLinkUrl("bit.score.TeacherScorePanel");
		
		STUDENT_SCORE_NODE.setParentId("1");
		STUDENT_SCORE_NODE.setLinkUrl("bit.score.StudentScorePanel");
		
		TEACHER_NODE.setParentId("2");
		TEACHER_NODE.setLinkUrl("bit.system.TeacherPanel");
		
		STUDENT_NODE.setParentId("2");
		STUDENT_NODE.setLinkUrl("bit.system.StudentPanel");
		
		UPDATE_TEACHER_NODE.setParentId("2");
		UPDATE_TEACHER_NODE.setLinkUrl("bit.system.UpdateTeacherPanel");
		
		UPDATE_STUDENT_NODE.setParentId("2");
		UPDATE_STUDENT_NODE.setLinkUrl("bit.system.UpdateStudentPanel");
		
	}
	
	static {
		ROLE_PARENT_MAP.put("0", Arrays.asList(SCORE_MGR_NODE, SYSTEM_MGR_NODE));
		ROLE_PARENT_MAP.put("1", Arrays.asList(SCORE_MGR_NODE, SYSTEM_MGR_NODE));
		ROLE_PARENT_MAP.put("2", Arrays.asList(SCORE_MGR_NODE, SYSTEM_MGR_NODE));
		
		ROLE_PARENT_MAP.put("0-1", Arrays.asList(COURSE_NODE, STUDENT_COURSE_NODE));
		ROLE_PARENT_MAP.put("0-2", Arrays.asList(TEACHER_NODE, STUDENT_NODE));
		
		ROLE_PARENT_MAP.put("1-1", Arrays.asList(TEACHER_SCORE_NODE));
		ROLE_PARENT_MAP.put("2-1", Arrays.asList(STUDENT_SCORE_NODE));
		
		ROLE_PARENT_MAP.put("1-2", Arrays.asList(UPDATE_TEACHER_NODE));
		ROLE_PARENT_MAP.put("2-2", Arrays.asList(UPDATE_STUDENT_NODE));
	}

	@Override
	public List<TreeNode> findByParentId(Long parentId, Byte role) {
		List<TreeNode> menuNodes= new ArrayList<TreeNode>();
		int roleCode = Role.Admin.getCode();
		if (role != null) roleCode = role;
		String key = roleCode + (parentId == null ? "" : "-" + String.valueOf(parentId));
		if (ROLE_PARENT_MAP.containsKey(key)) {
			menuNodes.addAll(ROLE_PARENT_MAP.get(key));
		}
		return menuNodes;
	}

}
