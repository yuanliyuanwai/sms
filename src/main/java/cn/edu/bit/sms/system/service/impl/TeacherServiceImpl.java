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
import cn.edu.bit.sms.system.dao.TeacherDao;
import cn.edu.bit.sms.system.dao.UserDao;
import cn.edu.bit.sms.system.entity.Teacher;
import cn.edu.bit.sms.system.service.TeacherService;
import cn.edu.bit.sms.system.vo.req.FindTeacherByPageParam;

@Service
public class TeacherServiceImpl  extends BaseServiceImpl<Teacher, TeacherDao> implements TeacherService  {
	
	@Autowired
	private UserDao userDao;

	@Override
	public Long save(Teacher entity) {
		userDao.save(entity.getUser());
		return super.save(entity);
	}
	
	@Override
	public void update(Teacher entity) {
		userDao.update(entity.getUser());
		super.update(entity);
	}

	@Override
	public QueryResult<Teacher> findByPage(FindTeacherByPageParam param) {
		QueryCondition teacherCondition = new QueryCondition();
		teacherCondition.setStart(param.getStart());
		teacherCondition.setLimit(param.getLimit());
		if (!StringUtil.isNil(param.getName())) {
			teacherCondition.addAlias("user", "user");
			teacherCondition.addCriterion(Restrictions.like("user.name", param.getName(), MatchMode.ANYWHERE));
		}
		if (!StringUtil.isNil(param.getUsername())) {
			teacherCondition.addAlias("user", "user");
			teacherCondition.addCriterion(Restrictions.like("user.username", param.getUsername(), MatchMode.ANYWHERE));
		}
		QueryResult<Teacher> teacherResult = super.dao.findList(teacherCondition);
		return teacherResult;
	}

	@Override
	public void delete(Serializable id) {
		Teacher teacher = super.get(id);
		long userId = teacher.getUser().getId();
		super.delete(id);
		userDao.delete(userId);
	}

	@Override
	public Teacher getByUsername(String username) {
		QueryCondition teacherCondition = new QueryCondition();
		teacherCondition.addAlias("user", "user");
		teacherCondition.addCriterion(Restrictions.eq("user.username", username));
		return super.dao.findUnique(teacherCondition);
	}
	
	

}
