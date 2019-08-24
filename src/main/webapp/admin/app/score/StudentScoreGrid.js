Ext.define('bit.score.StudentScoreGrid', {
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
    }
});
