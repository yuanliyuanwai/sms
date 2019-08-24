package cn.edu.bit.sms.common.http;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 控制层的上下文的拦截器
 * 
 * @author zhengchong.wan
 *
 */
public class HttpServletContextFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpServletContext.create(httpRequest, httpResponse);
		try {
			chain.doFilter(request, response);
		} finally {
			HttpServletContext.getContext().destroy();
		}
	}

	@Override
	public void destroy() {

	}

}
