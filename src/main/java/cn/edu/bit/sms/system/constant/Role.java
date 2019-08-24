package cn.edu.bit.sms.system.constant;

public enum Role {
	
	Admin(0, "管理员"),
	Teacher(1, "老师"),
	Student(2, "学生");
	
	private int code;
	
	private String name;
	
	private Role(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
