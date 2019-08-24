package cn.edu.bit.sms.common.vo.req;

import javax.validation.constraints.NotNull;

public class BaseParam {
	
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
