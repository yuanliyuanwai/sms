package cn.edu.bit.sms.system.service.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import cn.edu.bit.sms.common.exception.BusinessException;
import cn.edu.bit.sms.common.hibernate.dto.QueryCondition;
import cn.edu.bit.sms.common.service.BaseServiceImpl;
import cn.edu.bit.sms.system.dao.UserDao;
import cn.edu.bit.sms.system.entity.User;
import cn.edu.bit.sms.system.service.UserService;

@Service
public class UserServiceImpl  extends BaseServiceImpl<User, UserDao> implements UserService  {

	@Override
	public User login(String username) {
		QueryCondition condition = new QueryCondition();
		condition.addCriterion(Restrictions.eq("username", username));
		User user = super.dao.findUnique(condition);
		return user;
	}

	@Override
	public void updatePassword(String username, String oldPassword, String newPassword) {
		QueryCondition condition = new QueryCondition();
		condition.addCriterion(Restrictions.eq("username", username));
		User user = super.dao.findUnique(condition);
		if (user == null) throw new BusinessException("1", "用户名不存在!");
		if (!user.getPassword().equals(oldPassword)) throw new BusinessException("2", "原始密码不正确!");
		user.setPassword(newPassword);
		super.update(user);
	}

}
