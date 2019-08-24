package cn.edu.bit.sms.common.vo.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import cn.edu.bit.sms.common.annotation.Comment;

@JsonSerialize
@JsonInclude(Include.NON_NULL)
public class MessageResult<T> {
	
	@Comment(value = "返回码")
    private String code = "0";
	
	@Comment("成功失败标志")
	protected boolean success = true;
	
	@Comment("描述信息")
	protected String message;
	
    @Comment("数据")
    protected T data;
    
    public MessageResult() {
    }

    public MessageResult(T t) {
    	this.data = t;
    }
    
	public boolean getSuccess() {
		return code.equals("0");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		if (this.code.equals("0")) {
			this.success = false;
		}
	}

}
