package cn.edu.bit.sms.common.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({FIELD, METHOD})
@Retention(RUNTIME)
public @interface PermissionField {
	
	/**
	 * 对应的权限实体
	 * @return
	 */
	Class<?> permissionEntity();

}
