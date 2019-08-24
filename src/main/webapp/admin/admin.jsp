<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <base href="<%=basePath%>">    
    <title></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="X-UA-Compatible" content="IE=7" /> 
	<link rel="stylesheet" type="text/css" href="admin/resources/css/ext-all.css">
	<script type="text/javascript" src="admin/extjs4.2/bootstrap.js"></script>
	
	<link rel="stylesheet" type="text/css" href="admin/css/bis-idc-icon.css">
	<link rel="stylesheet" type="text/css" href="admin/css/header.css">
	<link rel="stylesheet" type="text/css" href="admin/css/home-panel.css">
	
	<script type="text/javascript" src="admin/app/app.js"></script>
	<script type="text/javascript" src="admin/app/utils.js"></script>	
	<script type="text/javascript" src="admin/extjs4.2/locale/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" >
	PwdEdit = function(){
    	var updatePassWin = Ext.create("bit.system.UpdatePasswordWindow");
    	updatePassWin.show(); 
    }
	</script>
  </head>
  <body>
  </body>
 
  </body>
</html>
