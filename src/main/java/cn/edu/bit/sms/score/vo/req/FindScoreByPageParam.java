package cn.edu.bit.sms.score.vo.req;

import cn.edu.bit.sms.common.vo.req.PageParam;

public class FindScoreByPageParam extends PageParam {
	
	private String name;
	
	private String no;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

}
