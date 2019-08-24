package cn.edu.bit.sms.common.util;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	/**
	 * 字符串是否为null或者为空
	 * @param str
	 * @return
	 */
	public static boolean isNil(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	public static boolean isNotNil(String str) {
		return !isNil(str);
	}
	
	/**
	 * 获取一个唯一数
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static int getNumberFromStr(String str) {
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return Integer.parseInt(m.group());
		}
		return -1;
	}
	
	/**
	 * 简单的字符串拼接成一个key
	 */
	public static String generateKey(String separator, Object ...objs) {
		if (objs == null) return "";
		if (StringUtil.isNil(separator)) separator = "_";
		if (objs.length == 1) return objs[0].toString();
		StringBuilder builder = new StringBuilder();
		for (Object obj : objs) {
			builder.append(obj.toString()).append(separator);
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}
	
	/**
	 * 填充固定长度的字符串
	 * @param str
	 * @param fillStr
	 * @param length
	 * @return
	 */
	public static String getFixedLengthStr(String str, String fillStr, int length) {
		if (str.length() >= length) return str;
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length - str.length(); i++) {
			builder.append(fillStr);
		}
		builder.append(str);
		return builder.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(generateKey("_", "1", "2"));
	}
	

}
