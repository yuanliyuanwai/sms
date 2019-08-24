package cn.edu.bit.sms.common.vo.req;

import javax.validation.constraints.NotNull;

import cn.edu.bit.sms.common.annotation.Comment;

public class CodeParam {

    @Comment("编号")
    @NotNull
    private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
    


}
