package cn.edu.bit.sms.common.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.bit.sms.common.util.IPUtil;
import cn.edu.bit.sms.common.util.JsonUtil;
import cn.edu.bit.sms.common.util.Logger;
import cn.edu.bit.sms.common.util.StringUtil;

public class LoginFilter extends HttpServlet implements Filter {

    /**
     * 排除检查的url
     */
    private final Set<String> excludeUrlSet = new HashSet<String>();

    /**
     * 排除检查的路径
     */
    private final Set<String> excludePahtSet = new HashSet<String>();

    /**
	 * 
	 */
    private static final long serialVersionUID = 6168107841998924840L;

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String url = request.getServletPath();
        String comeFrom = request.getParameter("comeFrom");

        // 静态资源不做处理
        for (String excludePath : excludePahtSet) {
            if (url.startsWith(excludePath.toLowerCase())) {
                filterChain.doFilter(req, res);
                return;
            }
        }

        // 记录访问日志
        logAccess(request);

        boolean isLogin = SessionUtil.getCurrentUserId(session) != null;
        boolean isFromMobile = comeFrom != null && (comeFrom.equalsIgnoreCase("ios") || comeFrom.equalsIgnoreCase("android"));
        // 未登录登录界面和登录请求不做处理
        if (!isLogin) {
            for (String excludeUrl : excludeUrlSet) {
                if (url.equalsIgnoreCase(excludeUrl)) {
                    filterChain.doFilter(req, res);
                    return;
                }
            }

            // 如果来源于移动端未登录统一提示登录超时
            if (isFromMobile) {
                // 如果是异步请求
                PrintWriter writer = response.getWriter();
                writer.write("{\"code\": \"-10000\", \"desc\":\"false\", \"msg\":\"the session is timeout!\"}");
                return;
            }

            // 如果是PC端普通请求跳转到登录界面非普通请求提示登录超时
            String requestType = request.getHeader("X-Requested-With");
            if (requestType != null && requestType.equals("XMLHttpRequest")) {
                // 如果是异步请求
                response.setContentType("application/json");
                PrintWriter writer = response.getWriter();
                writer.write("{\"code\": \"-10000\", \"desc\":\"false\", \"msg\":\"the session is timeout!\"}");
                return;
            } else {
                // 如果是同步请求
                response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
                return;
            }
        }

        if (isLogin) {
            for (String excludeUrl : excludeUrlSet) {
                if (url.equalsIgnoreCase(excludeUrl)) {
                    // 返回给客户端用户已经登录
                    req.setAttribute("isLogin", true);
                    filterChain.doFilter(req, res);
                    return;
                }
            }
        }
        filterChain.doFilter(req, res);
    }

    /**
     * 记录访问日志
     * @param request
     */
    private void logAccess(HttpServletRequest request) {
        if (!request.getRequestURI().endsWith(".js") && !request.getRequestURI().endsWith(".css") && !request.getRequestURI().endsWith(".png")) {
            StringBuffer sb = new StringBuffer();
            sb.append("URI:\t" + request.getRequestURI());
            sb.append("\tIP:\t" + IPUtil.getIpAddr(request) + "\tPort:\t" + request.getRemotePort());
            String params = JsonUtil.toJson(request.getParameterMap());
            sb.append("\tPARAM:\t" + params);
            Logger.debug(sb.toString());
        }
    }


    public void init(FilterConfig config) throws ServletException {

        String excludeUrls = config.getInitParameter("exclueUrl");
        String excluePaths = config.getInitParameter("excluePath");
        if (!StringUtil.isNil(excludeUrls)) {
            excludeUrls = excludeUrls.trim();
            String[] excludeUrlArr = excludeUrls.split(",");
            for (String excludeUrl : excludeUrlArr) {
                if (!StringUtil.isNil(excludeUrl)) {
                    excludeUrl = excludeUrl.trim();
                    excludeUrlSet.add(excludeUrl);
                }
            }
        }

        if (!StringUtil.isNil(excluePaths)) {
            excluePaths = excluePaths.trim();
            String[] excluePathArr = excluePaths.split(",");
            for (String excluePath : excluePathArr) {
                if (!StringUtil.isNil(excluePath)) {
                    excluePath = excluePath.trim();
                    excludePahtSet.add(excluePath);
                }
            }
        }
    }

}