Ext.define("bit.system.TeacherForm", {
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
    	
    	var sexCombo = new Ext.form.ComboBox({
    		xtype : "combo",
			id : "teacherForm#sexCombo",
			fieldLabel : '性别',
			name : 'user.sex',
			triggerAction : "all",
			store : new Ext.data.SimpleStore( {
			     fields : [ 'code', 'value'],
			     data : [
			     	[0, "男"],
			     	[1, "女"]
			     ]
			 }),
			displayField : 'value',
			valueField : 'code',
			mode : 'local',
			editable : false,
			emptyText : '请选择',
		    value : 1
    	});
    	
        this.items = [
    	    {
                xtype : 'hidden',
                name : 'id'
            },
            {
                xtype : 'hidden',
                name : 'user.id'
            },
            {
                xtype : 'hidden',
                name : 'user.role',
                value : 1
            },
            {
        	    xtype : 'textfield',
        	    name : 'user.username',
        	    fieldLabel : '登录名',
        	    readOnly : me.isUpdate,
        	    allowBlank: false
            },
            {
        	    xtype : 'textfield',
        	    name : 'user.password',
        	    fieldLabel : '密码',
        	    allowBlank: false,
        	    readOnly : me.isUpdate,
            },
            {
        	    xtype : 'textfield',
        	    name : 'user.name',
        	    fieldLabel : '姓名',
        	    allowBlank: true
            },
            sexCombo,
            {
        	    xtype : 'textfield',
        	    name : 'user.number',
        	    fieldLabel : '工号',
        	    allowBlank: true
            },
            {
        	    xtype : 'textfield',
        	    name : 'user.phone',
        	    fieldLabel : '手机号码',
        	    allowBlank: true
            },
            {
        	    xtype : 'textfield',
        	    name : 'user.idNo',
        	    fieldLabel : '身份证号',
        	    allowBlank: true
            },
            {
        	    xtype : 'textfield',
        	    name : 'user.email',
        	    fieldLabel : '邮箱',
        	    allowBlank: true
            },
            {
        	    xtype : 'textfield',
        	    name : 'user.homeAddress',
        	    fieldLabel : '家庭地址',
        	    allowBlank: true
            },
            {
        	    xtype : 'textfield',
        	    name : 'officeAddress',
        	    fieldLabel : '办公地址',
        	    allowBlank: true
            },
            {
        	    xtype : 'textfield',
        	    name : 'officePhone',
        	    fieldLabel : '办公电话',
        	    allowBlank: true
            },
            {
        	    xtype : 'textfield',
        	    name : 'title',
        	    fieldLabel : '职称',
        	    allowBlank: true
            }
    	];
        
        this.callParent(arguments);
    }
});