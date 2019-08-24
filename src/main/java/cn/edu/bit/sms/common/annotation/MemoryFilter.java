package cn.edu.bit.sms.common.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(TYPE)
@Retention(RUNTIME)
public @interface MemoryFilter {
	
	/**
	 * 属性路径
	 * 用于反射来获取值
	 * 多层用.来分割
	 * @return
	 */
	String propertyPath();
}
