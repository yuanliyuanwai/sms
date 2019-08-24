package cn.edu.bit.sms.common.util;


public class MathUtil {
	
	/**
	 * 获取最下公倍数
	 * @param arrys
	 * @return
	 */
	public static int getMinCommonMultiple(Integer... arrays) {
		int result = 0;
		for (int i = 1; i < Integer.MAX_VALUE; i++) {
			int num = arrays.length;
			while (num > 0) {
				int count = 0;
				for (int array : arrays) {
					if (i % array != 0) {
						break;
					} else {
						count++;
					}
				}
				if (count == arrays.length) {
					result = i;
					break;
				}
				num--;
			}
			if (result > 0) {
				break;
			}
		}
		return result;
	}

}
