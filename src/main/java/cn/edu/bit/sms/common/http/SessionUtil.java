package cn.edu.bit.sms.common.http;

import javax.servlet.http.HttpSession;

/**
 * 用户会话信息的辅助类
 * 
 * @author zhengchong.wan
 *
 */
public class SessionUtil {

    public static final String USER_SESSION_KEY = "currentUser";
    
    public static final String SYSTEM_SESSION_KEY = "currentSystem";

    /**
     * 保存用户
     * 
     * @param session
     * @param user
     */
    public static void setCurrentUser(HttpSession session, SessionUser user) {
        session.setAttribute(USER_SESSION_KEY, user);
    }
    
    /**
     * 保存系统
     * 
     * @param session
     * @param user
     */
    public static void setCurrentSystem(HttpSession session, SessionSystem system) {
        session.setAttribute(SYSTEM_SESSION_KEY, system);
    }

    /**
     * 获取用户
     * 
     * @param session
     * @return
     */
    public static SessionUser getCurrentUser(HttpSession session) {
        return (SessionUser) session.getAttribute(USER_SESSION_KEY);
    }
    
    /**
     * 获取系统
     * 
     * @param session
     * @return
     */
    public static SessionSystem getCurrentSystem(HttpSession session) {
        return (SessionSystem) session.getAttribute(SYSTEM_SESSION_KEY);
    }

    /**
     * 删除用户
     * 
     * @param session
     */
    public static void removeCurrentUser(HttpSession session) {
        session.removeAttribute(USER_SESSION_KEY);
    }

    /**
     * 获取当前用户的ID
     * 
     * @param session
     * @return
     */
    public static Long getCurrentUserId(HttpSession session) {
        SessionUser user = getCurrentUser(session);
        return user == null ? null : user.getUserId();
    }

}
