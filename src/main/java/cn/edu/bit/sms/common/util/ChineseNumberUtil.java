package cn.edu.bit.sms.common.util;

/**
 * 中文数字的辅助类
 * @author zhengchong.wan
 *
 */
public class ChineseNumberUtil {

	private static final String[] UNITS = { "", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿", "百亿", "千亿", "万亿" };
	
	private static final char[] NUM_ARRAY = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int num = 245000006;
		String numStr = formatInteger(num);
		System.out.println("num= " + num + ", convert result: " + numStr);
		double decimal = 245006.234206;
		System.out.println("============================================================");
		String decStr = formatDecimal(decimal);
		System.out.println("decimal= " + decimal + ", decStr: " + decStr);
	}

	/**
	 * 将整数装换成中文
	 * @param num
	 * @return
	 */
	public static String formatInteger(long num) {
		char[] val = String.valueOf(num).toCharArray();
		int len = val.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			String m = val[i] + "";
			int n = Integer.valueOf(m);
			boolean isZero = n == 0;
			String unit = UNITS[(len - 1) - i];
			if (isZero) {
				if ('0' == val[i - 1]) {
					// not need process if the last digital bits is 0
					continue;
				} else {
					// no unit for 0
					sb.append(NUM_ARRAY[n]);
				}
			} else {
				sb.append(NUM_ARRAY[n]);
				sb.append(unit);
			}
		}
		return sb.toString();
	}

	public static String formatDecimal(double decimal) {
		String decimals = String.valueOf(decimal);
		int decIndex = decimals.indexOf(".");
		int integ = Integer.valueOf(decimals.substring(0, decIndex));
		int dec = Integer.valueOf(decimals.substring(decIndex + 1));
		String result = formatInteger(integ) + "." + formatFractionalPart(dec);
		return result;
	}

	private static String formatFractionalPart(int decimal) {
		char[] val = String.valueOf(decimal).toCharArray();
		int len = val.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			int n = Integer.valueOf(val[i] + "");
			sb.append(NUM_ARRAY[n]);
		}
		return sb.toString();
	}

}
