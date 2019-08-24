package cn.edu.bit.sms.common.hibernate.dto;

import org.hibernate.sql.JoinType;


/**
 * 表别名用于多表连接
 * @author wanzc
 *
 */
public class TableAlias {
	
	/**
	 * 别名
	 */
	private String alias;
	
	/**
	 * 连接类型
	 */
	private JoinType type;

	public TableAlias(String alias, JoinType type) {
		super();
		this.alias = alias;
		this.type = type;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public JoinType getType() {
		return type;
	}

	public void setType(JoinType type) {
		this.type = type;
	}

}
