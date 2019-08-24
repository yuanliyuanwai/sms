Ext.define('bit.system.TeacherGrid', {
    extend: 'Ext.grid.Panel',
    isShowTbar : true,
    
    viewConfig: {
	    autoScroll : true,//自动滚动条
        stripeRows: true,//斑马线效果        
        enableTextSelection: true//启用文字选择
    },
    
    initComponent: function() {
        var me = this;
        this.columns = [
        	{
				header : "创建时间",
				width : 100,
				flex : 1,
				dataIndex : 'createTime'
			},
			{
				header : "最后更新时间",
				width : 100,
				flex : 1,
				dataIndex : 'updateTime'
			},
			{
				header: "登录名",
				width: 100,
				flex:1,
				dataIndex: 'user.username'
			},
			{
				header: "姓名",
				width: 100,
				flex:1,
				dataIndex: 'user.name'
			},
			{
				header: "性别",
				width: 100,
				flex:1,
				dataIndex: 'user.sex',
				renderer: function(value) {
		            return value == 0 ? '男':'女';
		        }
			},
			{
				header: "工号",
				width: 100,
				flex:1,
				dataIndex: 'user.number'
			},
			{
				header: "手机号码",
				width: 100,
				flex:1,
				dataIndex: 'user.phone'
			},
			{
				header: "身份证",
				width: 100,
				flex:1,
				dataIndex: 'user.idNo'
			},
			{
				header: "邮箱",
				width: 100,
				flex:1,
				dataIndex: 'user.email'
			},
			{
				header: "家庭住址",
				width: 100,
				flex:1,
				dataIndex: 'user.homeAddress'
			},
			{
				header: "办公住址",
				width: 100,
				flex:1,
				dataIndex: 'officeAddress'
			},
			{
				header: "办公电话",
				width: 100,
				flex:1,
				dataIndex: 'officePhone'
			},
			{
				header: "职称",
				width: 100,
				flex:1,
				dataIndex: 'title'
			}
			
     ];
     this.store = Ext.create("Ext.data.Store",{
        	storeId : me.storeId,
			fields : ["id", "user.id", "user.username", "user.name", "user.sex", "user.number", "user.phone", "user.password",
				"user.idNo", "user.email","user.homeAddress","officeAddress", "officePhone","title",
				"createTime", "updateTime"
				],
			scope: this,
			pageSize : 20,
			proxy : {
				type: 'ajax',
				url : 'system/teacher/findByPage',
		        reader: {
		            type: 'json',
		            root: 'data.list'
		        }           		        
			},
			autoLoad : true
     });
     if (me.isShowTbar) {
    	  this.tbar= [{
  			iconCls : "bis-idc-add", // 图标样式
  			text : "添加",
  			tooltip : "添加一条新的数据",
  			handler : this.onAddDD,
  			scope : this
  		}, "-", {
  			text : "修改",
  			iconCls : "bis-idc-checkIcon", // 图标样式
  			tooltip : "选择一条信息进行修改",
  			handler : this.onEditDD,
  			scope : this
  		}, "-", {
  			iconCls : "bis-idc-delete",
  			text : "删除",
  			handler : this.onDeleteDD,
  			scope : this
  		}];
     }
      this.dockedItems = [
		 {
		    xtype: 'pagingtoolbar',
		    dock:'bottom',
		    store: this.store,
		    displayInfo: true,
		    displayMsg: '显示记录 {0} - {1} 共 {2}',
		    emptyMsg: "无法显示数据",
		    afterPageText:'共{0}',
		    beforePageText:'页'
		}];
     me.callParent(arguments);
    },
    
	onAddDD : function() {
		var me = this;
		var edit = Ext.create('bit.system.TeacherWindow',{
			title:'添加',
			storeId : me.storeId
		});
		edit.show();
	},
	
	onEditDD : function() {
		var me = this;
		var rec = me.getSelectionModel().getSelection();
		if(rec.length == 0) {
			Ext.Msg.alert("操作提示", "请先选择需要修改的数据！");
            return;
		}
	    var edit = Ext.create('bit.system.TeacherWindow',{
						title:'编辑',
						storeId : me.storeId,
						isUpdate : true
         });
        edit.down('form').loadRecord(rec[0]);
        edit.show();
	},	
	
	onDeleteDD : function() {
		var me = this;
		var rec = me.getSelectionModel().getSelection();
		if(rec.length == 0) {
			Ext.Msg.alert("操作提示", "请先选择需要删除的数据！");
            return;
		}
		var record = rec[0];	
		var store = Ext.data.StoreManager.lookup(me.storeId);
		 Ext.MessageBox.confirm("确认", "删除名字为[" + record.get("user.name") + "]的老师？", function(btn){
			  if(btn == "yes"){
				  Ext.getBody().mask("正在删除数据…");
				  //提交删除
				  Ext.Ajax.request({
					  url: "system/teacher/del?id=" + record.get("id"),
					  disableCaching: true,
					  method: "get",
					  //删除成功
					  success: function(arg){
						  if(arg){
							  var json= Ext.JSON.decode(arg.responseText);
							  if(json.success){
								  //重新加载
								  store.reload();
							  }
							  else{
								  Ext.Msg.alert("操作提示", "数据删除失败，正在使用时不可删除！");
							  }
						  }
						  Ext.getBody().unmask();
					  },
					  failure: function(response){
						  Ext.getBody().unmask();
						  Ext.Msg.alert("操作提示", "数据删除失败！");
					  }
				  });
			  }
		 });
	}
});
