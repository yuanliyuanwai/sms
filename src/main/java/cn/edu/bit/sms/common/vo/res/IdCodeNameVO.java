package cn.edu.bit.sms.common.vo.res;


public class IdCodeNameVO extends IdNameVO {

	private String code;
	
	public IdCodeNameVO(Long id, String code, String name) {
		this(id, name);
		this.code = code;
	}
    
    public IdCodeNameVO(Long id, String name) {
       super(id, name);
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
