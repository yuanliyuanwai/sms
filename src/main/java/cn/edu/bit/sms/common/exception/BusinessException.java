package cn.edu.bit.sms.common.exception;

/**
 * 自定义带code和描述的业务异常
 * 
 * @author yzb
 *
 */
public class BusinessException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1329436654665068570L;

	private String code;
    
    private String description;
    
    private Object msg;

    public BusinessException(String description) {
        super();
        this.code = "-1";
        this.description = description;
    }
    public BusinessException(String description, Object msg) {
        this(description);
        this.msg = msg;
    }
    public BusinessException(String code, String description) {
        super();
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Object getMsg() {
		return msg;
	}

    public void setCode(String code) {
        this.code = code;
    }

	public void setDescription(String description) {
        this.description = description;
    }

	public void setMsg(Object msg) {
		this.msg = msg;
	}
    
}
