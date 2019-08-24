package cn.edu.bit.sms.system.service;

import cn.edu.bit.sms.common.service.BaseService;
import cn.edu.bit.sms.system.entity.User;

public interface UserService extends BaseService<User> {
	
	User login(String username);
	
	void updatePassword(String username, String oldPassword, String newPassword);
	

}
