<%@ page language="java" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>访问被拒绝</title>
  </head>

<body>
<h1>抱歉,访问被拒绝！</h1>

<p>
<%= request.getAttribute("SPRING_SECURITY_403_EXCEPTION")%>
</p>
<p>
</p>
</body>
</html>
