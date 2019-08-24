Ext.define('bit.score.TeacherScoreGrid', {
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
				header : "更新时间",
				width : 100,
				flex : 1,
				dataIndex : 'updateTime'
			},
			{
				header: "课程名称",
				width: 100,
				flex:1,
				dataIndex: 'course.name'
			},
			{
				header: "课程编号",
				width: 100,
				flex:1,
				dataIndex: 'course.no'
			},
			{
				header: "授课老师",
				width: 100,
				flex:1,
				dataIndex: 'course.teacher.user.name'
			},
			{
				header: "老师工号",
				width: 100,
				flex:1,
				dataIndex: 'course.teacher.user.number'
			},
			{
				header: "学生姓名",
				width: 100,
				flex:1,
				dataIndex: 'student.user.name'
			},
			{
				header: "学生学号",
				width: 100,
				flex:1,
				dataIndex: 'student.user.number'
			},	
			{
				header: "分数",
				width: 100,
				flex:1,
				dataIndex: 'score'
			},	
     ];
     this.store = Ext.create("Ext.data.Store",{
        	storeId : me.storeId,
			fields : ["id", "course.id", "course.name", "course.no", "course.teacher.user.name","course.teacher.user.number", "student.user.name", 
				"student.user.number", "student.id", "score", "createTime", "updateTime"],
			scope: this,
			pageSize : 20,
			proxy : {
				type: 'ajax',
				url : 'score/studentCourse/findByPage',
		        reader: {
		            type: 'json',
		            root: 'data.list'
		        }           		        
			},
			autoLoad : true
     });
     if (me.isShowTbar) {
    	  this.tbar= [{
  			text : "录入",
  			iconCls : "bis-idc-checkIcon", // 图标样式
  			tooltip : "选择一条信息进行修改",
  			handler : this.onEditDD,
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
    
	onEditDD : function() {
		var me = this;
		var rec = me.getSelectionModel().getSelection();
		if(rec.length == 0) {
			Ext.Msg.alert("操作提示", "请先选择需要修改的数据！");
            return;
		}
	    var edit = Ext.create('bit.score.TeacherScoreWindow',{
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
		 Ext.MessageBox.confirm("确认", "删除名字为[" + record.get("name") + "]的课程？", function(btn){
			  if(btn == "yes"){
				  Ext.getBody().mask("正在删除数据…");
				  //提交删除
				  Ext.Ajax.request({
					  url: "score/studentCourse/del?id=" + record.get("id"),
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
