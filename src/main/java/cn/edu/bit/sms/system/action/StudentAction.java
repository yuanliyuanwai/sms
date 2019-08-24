package cn.edu.bit.sms.system.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.bit.sms.common.action.BaseServiceAction;
import cn.edu.bit.sms.common.annotation.Comment;
import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.http.SessionUser;
import cn.edu.bit.sms.common.http.SessionUtil;
import cn.edu.bit.sms.common.vo.res.IdNameVO;
import cn.edu.bit.sms.common.vo.res.MessageResult;
import cn.edu.bit.sms.system.entity.Student;
import cn.edu.bit.sms.system.service.StudentService;
import cn.edu.bit.sms.system.vo.req.FindStudentByPageParam;

@RequestMapping("/system/student")
@RestController
@Comment("学生信息")
public class StudentAction extends BaseServiceAction<Student, StudentService> {
	
	@RequestMapping("/findByPage")
	@Comment("分页查询学生按钮信息")
	public MessageResult<QueryResult<Student>> findByPage(FindStudentByPageParam param) {
		return new MessageResult<QueryResult<Student>>(super.service.findByPage(param));
	}
	
	@RequestMapping("/getCurrentStudent")
	@Comment("查询当前学生")
	public MessageResult<Student> getCurrentTeacher(HttpServletRequest request) {
		SessionUser user = SessionUtil.getCurrentUser(request.getSession());
		return new MessageResult<Student>(super.service.getByUsername(user.getUsername()));
	}
	
	@RequestMapping("/findAllIdName")
	@Comment("查询所有学生的id和Name")
	public MessageResult<List<IdNameVO>> findAllIdName() {
		List<Student> students = super.service.findAll();
		List<IdNameVO> list = new ArrayList<IdNameVO>();
		for (Student student : students) {
			IdNameVO idNameVo = new IdNameVO(student.getId(), student.getUser().getName());
			list.add(idNameVo);
		}
		return new MessageResult<List<IdNameVO>>(list);
	}

}
