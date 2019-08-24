Ext.define("bit.score.CourseForm", {
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
    	
    	var teacherCombo = new Ext.form.ComboBox({
    		xtype : "combo",
			id : "courseForm_teacherCombo",
			fieldLabel : '授课老师',
			name : 'teacher.id',
			triggerAction : "all",
			store : new Ext.data.JsonStore( {
			     fields : [ 'id', 'name' ],
			     proxy: {
			         type: 'ajax',
			         url: 'system/teacher/findAllIdName',
			         reader: {
			             type: 'json',
			             root: 'data'
			         }
			     },
			     autoLoad: true,
			     listeners : {
			    	 load : function(store, records, success, eOpts) {
			    		 var selected = teacherCombo.getValue();
			    		 console.debug(selected);
			    		 if (selected) {
			    			 me.onComboxSelect(teacherCombo.id, selected);
			    		 } else {
			    			 teacherCombo.select(records[0]);
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
    	
        this.items = [
    	    {
                xtype : 'hidden',
                name : 'id'
            },
            {
        	    xtype : 'textfield',
        	    name : 'name',
        	    fieldLabel : '课程名称',
        	    allowBlank: false
            },
            {
        	    xtype : 'textfield',
        	    name : 'no',
        	    fieldLabel : '课程编号',
        	    allowBlank: false
            },
            {
        	    xtype : 'textfield',
        	    name : 'address',
        	    fieldLabel : '授课地点',
        	    allowBlank: true
            },
            teacherCombo
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