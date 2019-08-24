Ext.ns("bit.Ajax");

// 同步请求
bit.Ajax.Synchronize = function(url) {
	function createXhrObject() {
		var http;
		var activeX = ['MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP',
				'Microsoft.XMLHTTP'];

		try {
			http = new XMLHttpRequest();
		} catch (e) {
			for (var i = 0; i < activeX.length; ++i) {
				try {
					http = new ActiveXObject(activeX[i]);
					break;
				} catch (e) {
				}
			}
		} finally {
			return http;
		}
	};

	if (url.indexOf("?") > 0) {
		url += "&cache_time=" + (new Date().getTime());
	} else {
		url += "?cache_time=" + (new Date().getTime());
	}

	var conn = createXhrObject();
	conn.open("GET", url, false);
	conn.send(null);
	if (conn.responseText != '') {
		return Ext.decode(conn.responseText);
	} else {
		return null;
	}
};

function commMethod(title, msg,url, storeId){
	Ext.MessageBox.confirm(title, msg, function(btn){
		if(btn !="yes"){
			return;
		} else {
			Ext.getBody().mask("正在" + title +"中...");
			Ext.Ajax.request({
				url: url,
				disableCaching: true,
				method: "get",
				success: function(arg){
				    if(arg){
					    var json= Ext.JSON.decode(arg.responseText);
						if(json.success){
						    //重新加载
							Ext.data.StoreManager.lookup(storeId).reload();
						} else {
							Ext.Msg.alert("操作失败", json.message);
							Ext.data.StoreManager.lookup(storeId).reload();
						}
					}
				    Ext.getBody().unmask();
				},
				failure: function(response){
					Ext.getBody().unmask();
					Ext.Msg.alert("操作提示", title + "失败");
				}
			});
		}
	});
};
