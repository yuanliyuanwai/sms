package cn.edu.bit.sms.score.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.bit.sms.common.action.BaseServiceAction;
import cn.edu.bit.sms.common.annotation.Comment;
import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.http.SessionUser;
import cn.edu.bit.sms.common.http.SessionUtil;
import cn.edu.bit.sms.common.vo.res.MessageResult;
import cn.edu.bit.sms.score.entity.StudentCourse;
import cn.edu.bit.sms.score.service.StudentCourseService;
import cn.edu.bit.sms.score.vo.req.FindStudentCourseByPageParam;

@RequestMapping("/score/studentCourse")
@RestController
@Comment("学生课程信息")
public class StudentCourseAction extends BaseServiceAction<StudentCourse, StudentCourseService> {
	
	@RequestMapping("/findByPage")
	@Comment("分页查询课程信息")
	public MessageResult<QueryResult<StudentCourse>> findByPage(HttpServletRequest request, FindStudentCourseByPageParam param) {
		SessionUser sessionUser = SessionUtil.getCurrentUser(request.getSession());
		if (sessionUser.getRole() == 2) {
			return new MessageResult<QueryResult<StudentCourse>>(super.service.findByPage(param, sessionUser.getUsername()));
		}
		return new MessageResult<QueryResult<StudentCourse>>(super.service.findByPage(param, null));
	}
	
}
