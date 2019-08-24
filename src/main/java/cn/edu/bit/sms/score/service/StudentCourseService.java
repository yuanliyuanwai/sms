package cn.edu.bit.sms.score.service;

import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.service.BaseService;
import cn.edu.bit.sms.score.entity.StudentCourse;
import cn.edu.bit.sms.score.vo.req.FindStudentCourseByPageParam;

public interface StudentCourseService extends BaseService<StudentCourse> {
	
	QueryResult<StudentCourse> findByPage(FindStudentCourseByPageParam param, String student);
	
}
