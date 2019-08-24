Ext.Ajax.on('requestcomplete', function(conn,response,options){  
    if(response.hasOwnProperty("getResponseHeader") && response.getResponseHeader("sessionstatus") == 'timeout'){  
    	var message = "您已较长时间未进行操作或者在别的地方重复登录，<br/>" +
		"系统已自动退出，请重新登录！";
    	Ext.Msg.alert("安全警示", message, function(){
			window.location.href = "admin/login.jsp";
		});
    }  
    if(response.hasOwnProperty("getResponseHeader") && response.getResponseHeader("access-is-denied") == 'true'){  
    	var message = "访问拒绝！";
    	Ext.Msg.alert("安全警示",message);
    }  
});