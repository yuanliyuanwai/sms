package cn.edu.bit.sms.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    /**
     * 中文月
     */
    public static final String[] CHINESE_MONTH = { "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" };
    
    /**
     * 年月日
     */
    public static final String YYYYMMDD = "yyyyMMdd";

    /**
     * 年月日
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 时分秒
     */
    public static final String HH_MM_SS = "HH:mm:ss";
    
    /**
     * 时分
     */
    public static final String HH_MM = "HH:mm";

    /**
     * 年月日时分
     */
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * 年月日时分秒
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年月日时分秒毫秒
     */
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    public static Calendar toCalendar(String dateStr) {
        return toCalendar(dateStr, YYYY_MM_DD);
    }

    public static String toString(Calendar cal) {
        return toString(cal, YYYY_MM_DD);
    }

    public static Timestamp toTimestamp(String str) {
        return toTimestamp(str, YYYY_MM_DD);
    }

    public static String toString(Timestamp stamp) {
        return toString(stamp, YYYY_MM_DD);
    }

    public static Calendar toCalendar(String dateStr, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        cal.clear();
        try {
            cal.setTime(format.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public static String toString(Calendar cal, String pattern) {
        if (cal == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(cal.getTime());
    }

    public static String toString(long ms, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(ms);
    }

    public static String toString(Date date, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        return toString(cal, pattern);
    }

    public static String toString(Timestamp stamp, String pattern) {
        return toString((Date) stamp, pattern);
    }

    public static Timestamp toTimestamp(String str, String pattern) {
        Calendar cal = toCalendar(str, pattern);
        Timestamp stamp = new Timestamp(cal.getTimeInMillis());
        return stamp;
    }

    /**
     * 拷贝一个时间
     * 
     * @param from
     * @return
     */
    public static Calendar copyYYYYMMDD(Calendar from) {
        Calendar to = Calendar.getInstance();
        to.clear();
        to.set(Calendar.YEAR, from.get(Calendar.YEAR));
        to.set(Calendar.MONTH, from.get(Calendar.MONTH));
        to.set(Calendar.DAY_OF_MONTH, from.get(Calendar.DAY_OF_MONTH));
        to.set(Calendar.HOUR_OF_DAY, 0);
        to.set(Calendar.MINUTE, 0);
        to.set(Calendar.SECOND, 0);
        to.set(Calendar.MILLISECOND, 0);
        return to;
    }

    /**
     * 只保留年月日，其它的清零
     * 
     * @param cal
     * @return
     */
    public static Calendar retainYYYYMMDD(Calendar cal) {
        Calendar target = copyYYYYMMDD(cal);
        return target;
    }

    public static Calendar getLocalTime() {
        // 1、取得本地时间：
        return Calendar.getInstance();
    }

    public static Calendar getUtcTime() {
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        return cal;
    }

    /**
     * 转换为时间（天,时:分:秒.毫秒）
     * 
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }
}
