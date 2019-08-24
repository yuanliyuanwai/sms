package cn.edu.bit.sms.common.http;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import cn.edu.bit.sms.common.util.IPUtil;
import cn.edu.bit.sms.common.util.JsonUtil;
import cn.edu.bit.sms.common.util.Logger;

/**
 * 记录日志的filter
 * @author zhengchong.wan
 *
 */
public class AccessLogFilter extends HttpServlet implements Filter {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7436370953476445940L;

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
    	HttpServletRequest request = (HttpServletRequest) req;
        // 记录访问日志
    	try {
    		logAccess(request);
    	} finally {
    		filterChain.doFilter(req, res);
    	}
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

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}