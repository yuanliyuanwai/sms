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
import cn.edu.bit.sms.system.entity.Teacher;
import cn.edu.bit.sms.system.service.TeacherService;
import cn.edu.bit.sms.system.vo.req.FindTeacherByPageParam;

@RequestMapping("/system/teacher")
@RestController
@Comment("老师信息")
public class TeacherAction extends BaseServiceAction<Teacher, TeacherService> {
	
	@RequestMapping("/findByPage")
	@Comment("分页查询学生信息")
	public MessageResult<QueryResult<Teacher>> findByPage(FindTeacherByPageParam param) {
		return new MessageResult<QueryResult<Teacher>>(super.service.findByPage(param));
	}
	
	@RequestMapping("/findAllIdName")
	@Comment("查询所有老师的id和Name")
	public MessageResult<List<IdNameVO>> findAllIdName() {
		List<Teacher> teachers = super.service.findAll();
		List<IdNameVO> list = new ArrayList<IdNameVO>();
		for (Teacher teacher : teachers) {
			IdNameVO idNameVo = new IdNameVO(teacher.getId(), teacher.getUser().getName());
			list.add(idNameVo);
		}
		return new MessageResult<List<IdNameVO>>(list);
	}
	
	@RequestMapping("/getCurrentTeacher")
	@Comment("查询当前老师")
	public MessageResult<Teacher> getCurrentTeacher(HttpServletRequest request) {
		SessionUser user = SessionUtil.getCurrentUser(request.getSession());
		return new MessageResult<Teacher>(super.service.getByUsername(user.getUsername()));
	}

}
