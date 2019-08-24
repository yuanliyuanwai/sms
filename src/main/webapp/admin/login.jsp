<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登录</title>
<link rel="stylesheet" href="<%=basePath %>admin/css/login.css" type="text/css" />
<script language="javascript">
function fEvent(sType,oInput){
   switch (sType){
    case "focus" :
     oInput.isfocus = true;
    case "mouseover" :
     oInput.id = 'userloginname_hover';
     break;
    case "blur" :
     oInput.isfocus = false;
    case "mouseout" :
     if(!oInput.isfocus){
      oInput.id='userloginname';
     }
     break;
   }
}

function pEvent(sType,oInput){
   switch (sType){
    case "focus" :
     oInput.isfocus = true;
    case "mouseover" :
     oInput.id = 'passwd_hover';
     break;
    case "blur" :
     oInput.isfocus = false;
    case "mouseout" :
     if(!oInput.isfocus){
      oInput.id='passwd';
     }
     break;
   }
}

function aEvent(sType,oInput){
   switch (sType){
    case "focus" :
     oInput.isfocus = true;
    case "mouseover" :
     oInput.id = 'authcd_hover';
     break;
    case "blur" :
     oInput.isfocus = false;
    case "mouseout" :
     if(!oInput.isfocus){
      oInput.id='authcd';
     }
     break;
   }
}
</script>
</head>
<%
	String error = (String)session.getAttribute("error");
    String errorMsg = "";
    if ("1".equals(error)) {
    	errorMsg = "用户名和密码不能为空！";
    } else if ("2".equals(error)) {
    	errorMsg = "用户名或密码错误！";
    }
    session.setAttribute("error", "");
%>
<body>
<form id="loginForm" action="<%=basePath%>system/user/login" method="post">
	<p class="username">
	    <label for="userloginname" >用户名：</label>
		<input type="text" maxlength="15" name="username" id="userloginname" value="admin" onFocus="fEvent('focus',this)" onBlur="fEvent('blur',this)" onMouseOver="fEvent('mouseover',this)" onMouseOut="fEvent('mouseout',this)"/>
		<br />	
	</p>
	<p class="key">
	    <label for="passwd" >密&nbsp;码：</label>
		<input type="password" size="20" maxlength="30"  name="password" id="passwd" value="123456" onFocus="pEvent('focus',this)" onBlur="pEvent('blur',this)" onMouseOver="pEvent('mouseover',this)" onMouseOut="pEvent('mouseout',this)"/>
		<br />
		<label id="loginerror" ><font size="2" color="red"><%=errorMsg %></font></label>
		<br />
		<label><font size="1" color = "yellow">账号：admin/teacher/student-123456</font></label>
	</p>
	<div class="login_bar" >
		<input class="login" type="submit" value="" onFocus="this.blur()" hidefocus="true"  />
		<input class="reset" type="reset" value="" onFocus="this.blur()" hidefocus="true" />
	</div>
</form>
</body>
</html>