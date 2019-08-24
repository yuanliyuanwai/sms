package cn.edu.bit.sms.common.abstracts;

import java.util.HashMap;
import java.util.Map;


/**
 * 带参数的任务
 * @author zhegnchong.wan
 *
 */
public abstract class AbstractTask implements Runnable {
	
	private Map<String, Object> contextMap = new HashMap<String, Object>();
	
	public AbstractTask addParameter(String key, Object value) {
		this.contextMap.put(key, value);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getParameter(String key) {
		return (T)this.contextMap.get(key);
	}
	

}
