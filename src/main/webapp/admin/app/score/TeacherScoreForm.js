Ext.define("bit.score.TeacherScoreForm", {
    extend: "Ext.form.Panel",

    readOnly : false,
    allowBlank : false,
    onUpdate : false,
    
    fieldDefaults: {
        anchor: "100%",
        labelAlign: "right",
        labelWidth: 100,
        combineErrors: true,
        msgTarget: "side"
    },
    
    padding: "5 5 0 5",
    border: false,
    style: "background-color: #fff;",
    
    initComponent: function() {
    	var me = this;
    	
    	var courseCombo = new Ext.form.ComboBox({
    		xtype : "combo",
			id : "studentCourseForm_courseCombo",
			fieldLabel : '课程列表',
			name : 'course.id',
			triggerAction : "all",
			store : new Ext.data.JsonStore( {
			     fields : [ 'id', 'name' ],
			     proxy: {
			         type: 'ajax',
			         url: 'score/course/findAll',
			         reader: {
			             type: 'json',
			             root: 'data'
			         }
			     },
			     autoLoad: true,
			     listeners : {
			    	 load : function(store, records, success, eOpts) {
			    		 var selected = courseCombo.getValue();
			    		 if (selected) {
			    			 me.onComboxSelect(courseCombo.id, selected);
			    		 } else {
			    			 courseCombo.select(records[0]);
			    		 }
			    	 }
			     }
			 }),
			displayField : 'name',
			valueField : 'id',
			mode : 'remote',
			editable : false,
			emptyText : '请选择'
    	});
    	
    	var studentCombo = new Ext.form.ComboBox({
    		xtype : "combo",
			id : "studentCourseForm_studentCombo",
			fieldLabel : '学生列表',
			name : 'student.id',
			triggerAction : "all",
			store : new Ext.data.JsonStore( {
			     fields : [ 'id', 'user.name' ],
			     proxy: {
			         type: 'ajax',
			         url: 'system/student/findAll',
			         reader: {
			             type: 'json',
			             root: 'data'
			         }
			     },
			     autoLoad: true,
			     listeners : {
			    	 load : function(store, records, success, eOpts) {
			    		 var selected = studentCombo.getValue();
			    		 if (selected) {
			    			 me.onComboxSelect(studentCombo.id, selected);
			    		 } else {
			    			 studentCombo.select(records[0]);
			    		 }
			    	 }
			     }
			 }),
			displayField : 'user.name',
			valueField : 'id',
			mode : 'remote',
			editable : false,
			emptyText : '请选择'
    	});
    	
        this.items = [
    	    {
                xtype : 'hidden',
                name : 'id'
            },
            courseCombo,
            studentCombo,
            {
        	    xtype : 'numberfield',
        	    name : 'score',
        	    fieldLabel : '分数',
        	    allowBlank: true,
        	    minValue : 0
            }
    	];
        
        this.callParent(arguments);
    },
    
    onComboxSelect : function(comboId, selected) {
    	var combox = Ext.getCmp(comboId);
    	var store = combox.getStore();
    	var obj = store.findRecord(combox.valueField, selected);
    	combox.select(obj);  
    }
});