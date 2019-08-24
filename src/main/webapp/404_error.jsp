<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%

    Object errorUrlObj = request.getAttribute("javax.servlet.error.request_uri");
    if (errorUrlObj != null) {
    	String errorUrl = errorUrlObj.toString();
    	// 如果是jsp文件或者是mvc的url获取不到抛个异常信息
    	if (errorUrl.indexOf(".") == -1 || errorUrl.endsWith(".jsp") || errorUrl.endsWith(".action") || errorUrl.endsWith(".do")) {
    		response.setStatus(500);
    	}
    }
%>
