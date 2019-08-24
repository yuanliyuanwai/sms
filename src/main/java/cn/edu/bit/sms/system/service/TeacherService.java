package cn.edu.bit.sms.system.service;

import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.service.BaseService;
import cn.edu.bit.sms.system.entity.Teacher;
import cn.edu.bit.sms.system.vo.req.FindTeacherByPageParam;

public interface TeacherService extends BaseService<Teacher> {
	
	QueryResult<Teacher> findByPage(FindTeacherByPageParam param);
	
	Teacher getByUsername(String username);

}
