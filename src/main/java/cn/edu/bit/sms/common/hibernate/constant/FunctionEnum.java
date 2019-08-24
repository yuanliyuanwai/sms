package cn.edu.bit.sms.common.hibernate.constant;

/**
 * hibernate常用函数
 * @author zhengchong.wan
 *
 */
public enum FunctionEnum {
	
	/**
	 * 求和
	 */
	SUM,
	
	/**
	 * 求记录条数
	 */
	COUNT, 
	
	/**
	 * 取唯一
	 */
	DISTINCT,
	
	/**
	 * 取最大值
	 */
	MAX,
	
	/**
	 * 取最小值
	 */
	MIN,
	
	/**
	 * 分组
	 */
	GROUPBY,
	
	/**
	 * 查询制定的属性
	 */
	PROPERTY,
	
	/**
	 * 取不相同的记录总数
	 */
	COUNTDISTINCT
}
