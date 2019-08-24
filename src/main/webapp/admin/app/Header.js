Ext.define('bit.Header', { 
    extend: 'Ext.Component', 
    userName: '',
    initComponent: function() { 
    	
        Ext.applyIf(this, { 
            xtype: 'box', 
            cls: 'header', 
            region: 'north', 
            html : "<div class='header'><img class='logo' src='admin/images/header/bit-logo.png'>" +"<div class='logofont'>&nbsp;北京理工成绩管理系统"+"</div>"+
         	"<div class='user' id ='helloUserName'>欢迎您 </div>" +
         	"<ul><li><a class='home' title='首页' alt='首页' href='/p2p/index.action'></a></li>" +
         	"<img src='admin/images/header/depart.png'/>" +
         	"<li><a class='pwd' title='修改密码' alt='修改密码' href='javascript:PwdEdit()'></a></li>" +
         	"<img src='admin/images/header/depart.png'/>" +
         	"<li><a class='quit' title='退出' alt='退出' href='system/user/logOut'></a></li>" +
         	"</ul>" +
         	"</div>", 
            height: 50 
        }); 
        Ext.Ajax.request({
			  url: "system/user/getName",
			  disableCaching: true,
			  method: "get",
			  //删除成功
			  success: function(arg){
				  var json= Ext.JSON.decode(arg.responseText);
				  if (json.data){
					  var el = Ext.get('helloUserName'); 
					  el.dom.innerHTML='欢迎您：' + json.data;  
				  }
			  }
		});
        this.callParent(arguments); 
    }    

}); 
