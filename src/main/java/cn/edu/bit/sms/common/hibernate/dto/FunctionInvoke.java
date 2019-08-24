package cn.edu.bit.sms.common.hibernate.dto;

import cn.edu.bit.sms.common.hibernate.constant.FunctionEnum;

/**
 * hibernate的函数调用
 * @author zhengchong.wan
 *
 */
public class FunctionInvoke {
	
	private String propertyName;
	
	private FunctionEnum type;
	
	public FunctionInvoke(String propertyName, FunctionEnum type) {
		super();
		this.propertyName = propertyName;
		this.type = type;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public FunctionEnum getType() {
		return type;
	}

	public void setType(FunctionEnum type) {
		this.type = type;
	}
	

}
