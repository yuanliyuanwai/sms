package cn.edu.bit.sms.common.http;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * HttpServlet的上下文信息
 * @author zhengchong.wan
 *
 */
public class HttpServletContext {
	
	 private final static ThreadLocal<HttpServletContext> contextLocal = new ThreadLocal<HttpServletContext>();
	 
	 private HttpSession session;
	 
	 private HttpServletRequest request;
	 
	 private HttpServletResponse response;
	 
	 private Map<String, Cookie> cookies = new HashMap<String, Cookie>();
	 
	 private HttpServletContext() {}
	 
	 public static void create(HttpServletRequest request, HttpServletResponse response) {
		 HttpServletContext httpServletContext = new HttpServletContext();
		 httpServletContext.session = request.getSession();
		 httpServletContext.request = request;
		 httpServletContext.response = response;
		 Cookie[] cookies = request.getCookies();
         if(cookies != null) {
            for(Cookie cookie : cookies) {
            	httpServletContext.cookies.put(cookie.getName(), cookie);
            }
	     }
         contextLocal.set(httpServletContext);
	 }
	 
	 public void destroy() {
		this.session = null;
	    this.request = null;
	    this.response = null;
	    this.cookies = null;
	    contextLocal.remove();
	 }
	 
	 public static HttpServletContext getContext() {
		 HttpServletContext context = contextLocal.get();
		 return context != null ? context : new HttpServletContext();
	 }
	 
	 public HttpServletRequest getRequest() {
		 return this.request;
	 }
	 
	 public HttpServletResponse getResponse() {
		 return this.response;
	 }
	 
	 public HttpSession getSession() {
		 return this.session;
	 }
	 
	 public SessionUser getCurrentUser() {
		 if (this.session == null) return null;
		 return SessionUtil.getCurrentUser(session);
	 }
	 
	 public SessionSystem getCurrentSystem() {
		 if (this.session == null) return null;
		 return SessionUtil.getCurrentSystem(session);
	 }
	 
	 public Long getCurrentUserId() {
		 if (this.session == null) return null;
		 return SessionUtil.getCurrentUserId(session);
	 }
	 
	 public Map<String, Cookie> getCookies() {
		 if (cookies == null) return null;
		 return Collections.unmodifiableMap(cookies);
	 }

}
