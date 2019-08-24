package cn.edu.bit.sms.common.inter;

/**
 * 系统认证的接口
 * @author zhengchong.wan
 *
 */
public interface ISystemAuth {
	
	String getSystemCode();
	
	String getTenantCode();
	
	String getUsername();
	
	String getPassword();

}
