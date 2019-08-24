package cn.edu.bit.sms.score.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.bit.sms.common.action.BaseServiceAction;
import cn.edu.bit.sms.common.annotation.Comment;
import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.vo.res.MessageResult;
import cn.edu.bit.sms.score.entity.Course;
import cn.edu.bit.sms.score.service.CourseService;
import cn.edu.bit.sms.score.vo.req.FindScoreByPageParam;

@RequestMapping("/score/course")
@RestController
@Comment("课程信息")
public class CourseAction extends BaseServiceAction<Course, CourseService> {
	
	@RequestMapping("/findByPage")
	@Comment("分页查询课程信息")
	public MessageResult<QueryResult<Course>> findByPage(FindScoreByPageParam param) {
		return new MessageResult<QueryResult<Course>>(super.service.findByPage(param));
	}
	
}
