package cn.edu.bit.sms.system.service.impl;

import java.io.Serializable;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.bit.sms.common.dto.QueryResult;
import cn.edu.bit.sms.common.hibernate.dto.QueryCondition;
import cn.edu.bit.sms.common.service.BaseServiceImpl;
import cn.edu.bit.sms.common.util.StringUtil;
import cn.edu.bit.sms.system.dao.StudentDao;
import cn.edu.bit.sms.system.dao.UserDao;
import cn.edu.bit.sms.system.entity.Student;
import cn.edu.bit.sms.system.service.StudentService;
import cn.edu.bit.sms.system.vo.req.FindStudentByPageParam;

@Service
public class StudentServiceImpl  extends BaseServiceImpl<Student, StudentDao> implements StudentService  {
	
	@Autowired
	private UserDao userDao;

	@Override
	public Long save(Student entity) {
		userDao.save(entity.getUser());
		return super.save(entity);
	}
	
	@Override
	public void update(Student entity) {
		userDao.update(entity.getUser());
		super.update(entity);
	}

	@Override
	public QueryResult<Student> findByPage(FindStudentByPageParam param) {
		QueryCondition studentCondition = new QueryCondition();
		studentCondition.setStart(param.getStart());
		studentCondition.setLimit(param.getLimit());
		if (!StringUtil.isNil(param.getName())) {
			studentCondition.addAlias("user", "user");
			studentCondition.addCriterion(Restrictions.like("user.name", param.getName(), MatchMode.ANYWHERE));
		}
		if (!StringUtil.isNil(param.getUsername())) {
			studentCondition.addAlias("user", "user");
			studentCondition.addCriterion(Restrictions.like("user.username", param.getUsername(), MatchMode.ANYWHERE));
		}
		QueryResult<Student> studentResult = super.dao.findList(studentCondition);
		return studentResult;
	}
	
	@Override
	public void delete(Serializable id) {
		Student student = super.get(id);
		long userId = student.getUser().getId();
		super.delete(id);
		userDao.delete(userId);
	}
	
	@Override
	public Student getByUsername(String username) {
		QueryCondition teacherCondition = new QueryCondition();
		teacherCondition.addAlias("user", "user");
		teacherCondition.addCriterion(Restrictions.eq("user.username", username));
		return super.dao.findUnique(teacherCondition);
	}
	

}
