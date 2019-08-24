package cn.edu.bit.sms.common.hibernate.util;

import cn.edu.bit.sms.common.hibernate.constant.FunctionEnum;
import cn.edu.bit.sms.common.hibernate.dto.FunctionInvoke;


/**
 * 函数调用的辅助类
 * @author zhengchong.wan
 *
 */
public class FunctionInvokes {
	
	public static FunctionInvoke sum(String propertyName) {
		return new FunctionInvoke(propertyName, FunctionEnum.SUM);
	}
	
	public static FunctionInvoke count(String propertyName) {
		return new FunctionInvoke(propertyName, FunctionEnum.COUNT);
	}
	
	public static FunctionInvoke distinct(String propertyName) {
		return new FunctionInvoke(propertyName, FunctionEnum.DISTINCT);
	}
	
	public static FunctionInvoke max(String propertyName) {
		return new FunctionInvoke(propertyName, FunctionEnum.MAX);
	}
	
	public static FunctionInvoke min(String propertyName) {
		return new FunctionInvoke(propertyName, FunctionEnum.MIN);
	}
	
	public static FunctionInvoke groupBy(String propertyName) {
		return new FunctionInvoke(propertyName, FunctionEnum.GROUPBY);
	}
	
	public static FunctionInvoke property(String propertyName) {
		return new FunctionInvoke(propertyName, FunctionEnum.PROPERTY);
	}
	
	public static FunctionInvoke countDistinct(String propertyName) {
		return new FunctionInvoke(propertyName, FunctionEnum.COUNTDISTINCT);
	}

}
