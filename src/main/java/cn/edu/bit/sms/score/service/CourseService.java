package cn.edu.bit.sms.score.service;

import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.service.BaseService;
import cn.edu.bit.sms.score.entity.Course;
import cn.edu.bit.sms.score.vo.req.FindScoreByPageParam;

public interface CourseService extends BaseService<Course> {
	
	QueryResult<Course> findByPage(FindScoreByPageParam param);
	
}
