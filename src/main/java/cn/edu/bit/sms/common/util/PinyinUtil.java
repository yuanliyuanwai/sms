package cn.edu.bit.sms.common.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;


/**
 * 汉语拼音的辅助类
 * @author zhengchong.wan
 *
 */
public class PinyinUtil {
	
	/**
	 * 将汉字转换成全拼
	 * @param str
	 * @return
	 */
	public static String getPinYin(String str) {
		char[] strCharArr = str.toCharArray();
		// 设置汉字拼音输出的格式
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		
		StringBuilder pinyinBuilder = new StringBuilder();
		try {
			for (int i = 0; i < strCharArr.length; i++) {
				// 判断能否为多字节字符
				if (Character.toString(strCharArr[i]).matches("[\\u4E00-\\u9FA5]+")) {
					// 处理多音字
					String[] multiYinArr = PinyinHelper.toHanyuPinyinStringArray(strCharArr[i], format);
					// 多音字取一个发音
					pinyinBuilder.append(multiYinArr[0]);
				} else {
					// 如果不是汉字字符，间接取出字符并连接到字符串t4后
					pinyinBuilder.append(Character.toString(strCharArr[i]));
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return pinyinBuilder.toString();
	}
	
	public static String getPinyinFirstLetter(String str) {
		StringBuilder builder = new StringBuilder();
		for (int j = 0; j < str.length(); j++) {
			String pinyin = getPinYin(Character.toString(str.charAt(j)));
			builder.append(pinyin.charAt(0));
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		String cnStr = "我们";
		System.out.println(getPinYin(cnStr));
		System.out.println(getPinyinFirstLetter(cnStr));
	}
}