package cn.edu.bit.sms.common.vo.res;

public enum ErrorCode {
	
	SESSION_TIME_OUT("-10000", "the session is time out!");
	
	private String code;
	
	private String message;
	
	private ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
