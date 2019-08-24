Ext.define("bit.system.StudentForm", {
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
                value : 2
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
            {
				xtype : "combo",
				fieldLabel : '性别',
				name : 'user.sex',
				store : new Ext.data.SimpleStore( {
					fields : [ 'value', 'text' ],
					data : [ [ 0, '男' ], [ 1, '女' ] ]
				}),
				displayField : 'text',
				valueField : 'value',
				mode : 'local',
				editable : false,
				value : 1,
				emptyText : '请选择'
			},
            {
        	    xtype : 'textfield',
        	    name : 'user.number',
        	    fieldLabel : '学号',
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
        	    name : 'college',
        	    fieldLabel : '学院',
        	    allowBlank: true
            },
            {
        	    xtype : 'textfield',
        	    name : 'department',
        	    fieldLabel : '系',
        	    allowBlank: true
            },
            {
        	    xtype : 'textfield',
        	    name : 'dormitory',
        	    fieldLabel : '宿舍',
        	    allowBlank: true
            },
            {
        	    xtype : 'numberfield',
        	    name : 'grade',
        	    fieldLabel : '年级',
        	    allowBlank: true,
        	    minValue : 2012
            }
    	];
        
        this.callParent(arguments);
    }
});