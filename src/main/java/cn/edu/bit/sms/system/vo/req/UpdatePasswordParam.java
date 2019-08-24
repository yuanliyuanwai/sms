package cn.edu.bit.sms.system.vo.req;

import org.hibernate.validator.constraints.NotEmpty;

public class UpdatePasswordParam {
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String password2;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	

}
