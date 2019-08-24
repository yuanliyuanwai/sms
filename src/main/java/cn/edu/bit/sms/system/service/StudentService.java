package cn.edu.bit.sms.system.service;

import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.service.BaseService;
import cn.edu.bit.sms.system.entity.Student;
import cn.edu.bit.sms.system.vo.req.FindStudentByPageParam;

public interface StudentService extends BaseService<Student> {
	
	QueryResult<Student> findByPage(FindStudentByPageParam param);
	
	Student getByUsername(String username);
	

}
