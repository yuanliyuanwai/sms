package cn.edu.bit.sms.common.util;

import org.apache.log4j.Level;

public class Logger {
	
    /**
     * 设置系统日志级别
     * @param level 级别:debug,info,warn,error
     * @return 0成功,1失败
     */
    public static int setLevel(String level) {
        level = level.toUpperCase();
        if (level.equals("DEBUG")) {
        	org.apache.log4j.Logger.getRootLogger().setLevel(Level.DEBUG);
            return 0;
        } else if (level.equals("INFO")) {
        	org.apache.log4j.Logger.getRootLogger().setLevel(Level.INFO);
            return 0;
        } else if (level.equals("WARN")) {
        	org.apache.log4j. Logger.getRootLogger().setLevel(Level.WARN);
            return 0;
        } else if (level.equals("ERROR")) {
        	org.apache.log4j.Logger.getRootLogger().setLevel(Level.ERROR);
            return 0;
        } else if (level.equals("FATAL")) {
        	org.apache.log4j.Logger.getRootLogger().setLevel(Level.FATAL);
            return 0;
        }
        return -1;
    }

    /**
     * 取得日志级别
     * @return
     */
    public static Level getLevel() {
        return org.apache.log4j.Logger.getRootLogger().getLevel();
    }

    /**
     * 取得日志级别
     * @return
     */
    public static String getLevelToString() {
        return org.apache.log4j.Logger.getRootLogger().getLevel().toString();
    }

    public static void info(Object o) {
        getLogger().log(FQCN, Level.INFO, o, null);
    }

    public static void debug(Object o) {
        getLogger().log(FQCN, Level.DEBUG, o, null);

    }

    public static void error(Object o) {
        getLogger().log(FQCN, Level.ERROR, o, null);
    }

    public static void error(Object o, Throwable t) {
        getLogger().log(FQCN, Level.ERROR, o, t);
    }
    
    public static void warning(Object o) {
        getLogger().log(FQCN, Level.WARN, o, null);
    }

    public static void fetal(Object o) {
        getLogger().log(FQCN, Level.FATAL, o, null);
    }

    // 注意这里要使用Log.class.getName()，否则在使用log.log()时打印的日志中会出现乱码
    private static String FQCN = Logger.class.getName();

    /**
     * 获得Logger
     * @return
     */
    private static org.apache.log4j.Logger getLogger() {
        // [0] Thread
        // [1] Log
        // [2] Log 前两个都是本地，一个是定位到类，一个定位到方法
        String nameString = Thread.currentThread().getStackTrace()[3].getClassName();
        return org.apache.log4j.Logger.getLogger(nameString);
    }

    public static boolean isDebug() {
        return getLogger().isDebugEnabled();
    }

    public static boolean isInfo() {
        return getLogger().isInfoEnabled();
    }

    public static boolean isWarn() {
        return getLogger().isEnabledFor(Level.WARN);
    }

    public static boolean isError() {
        return getLogger().isEnabledFor(Level.ERROR);
    }

    public static boolean isFatal() {
        return getLogger().isEnabledFor(Level.FATAL);
    }
}
