package cn.edu.bit.sms.system.vo.req;

import cn.edu.bit.sms.common.vo.req.PageParam;

public class FindTeacherByPageParam extends PageParam {
	
	private String name;
	
	private String username;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
