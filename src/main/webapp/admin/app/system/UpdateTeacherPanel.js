Ext.define("bit.system.UpdateTeacherPanel" ,{
    extend: "Ext.panel.Panel",
    
    border: false,
    split: false,
    layout: "fit",
    collapsible: false,
    
	initComponent: function() {
		var me = this;
		var sexCombo = new Ext.form.ComboBox({
    		xtype : "combo",
			id : "updateTeacherPanel#sexCombo",
			fieldLabel : '性别',
			name : 'user.sex',
			margins: "10 0 0 20",
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
		
	    this.items= [
	    	{
	    	xtype: "form",
            flex: 1,
            bodyPadding: 5,
            fieldDefaults: {
	    	anchor: "100%",
		    labelAlign: "right",
	        labelWidth: 70
            },
            
            items: [{
            	defaults: {
		            border: false,
		            xtype: 'panel',
		            bodyPadding: 5,
		            layout: 'anchor'
		        },
		
		        layout: 'vbox',
		        items: [
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
		         	    margins: "10 0 0 20",
		         	    fieldLabel : '登录名',
		         	    readOnly : true,
		         	    allowBlank: false
		             },
		             {
		         	    xtype : 'textfield',
		         	    name : 'user.password',
		         	    margins: "10 0 0 20",
		         	    fieldLabel : '密码',
		         	    allowBlank: false,
		         	    readOnly : me.isUpdate,
		             },
		             {
		         	    xtype : 'textfield',
		         	    name : 'user.name',
		         	    fieldLabel : '姓名',
		         	   margins: "10 0 0 20",
		         	    allowBlank: true
		             },
		             sexCombo,
		             {
		         	    xtype : 'textfield',
		         	    name : 'user.number',
		         	    margins: "10 0 0 20",
		         	    fieldLabel : '工号',
		         	    allowBlank: true
		             },
		             {
		         	    xtype : 'textfield',
		         	    name : 'user.phone',
		         	    margins: "10 0 0 20",
		         	    fieldLabel : '手机号码',
		         	    allowBlank: true
		             },
		             {
		         	    xtype : 'textfield',
		         	    name : 'user.idNo',
		         	    fieldLabel : '身份证号',
		         	    margins: "10 0 0 20",
		         	    allowBlank: true
		             },
		             {
		         	    xtype : 'textfield',
		         	    name : 'user.email',
		         	    fieldLabel : '邮箱',
		         	    margins: "10 0 0 20",
		         	    allowBlank: true
		             },
		             {
		         	    xtype : 'textfield',
		         	    name : 'user.homeAddress',
		         	    fieldLabel : '家庭地址',
		         	    margins: "10 0 0 20",
		         	    allowBlank: true
		             },
		             {
		         	    xtype : 'textfield',
		         	    name : 'officeAddress',
		         	    fieldLabel : '办公地址',
		         	    margins: "10 0 0 20",
		         	    allowBlank: true
		             },
		             {
		         	    xtype : 'textfield',
		         	    name : 'officePhone',
		         	    fieldLabel : '办公电话',
		         	    margins: "10 0 0 20",
		         	    allowBlank: true
		             },
		             {
		         	    xtype : 'textfield',
		         	    name : 'title',
		         	    fieldLabel : '职称',
		         	    margins: "10 0 0 20",
		         	    allowBlank: true
		             },
		             {
		        	defaults: {
			            border: false,
			            xtype: 'panel',
			            flex: 1,
			            layout: 'anchor'
			        },
			        layout: 'hbox',
			        items: [
			            {
		            	xtype: "button",
		            	margins: "0 0 0 80",
		            	iconCls: "bis-panel-save",
		            	text: "保存",
		            	handler: this.onSubmit,
		            	scope: this
		            }, {
		            	xtype: "button",
		            	margins: "0 0 0 10",
		            	iconCls: "bis-idc-clean-condition",
		            	text: "重置",
		            	handler: function(bt, e){
                			this.onReset();
		            	},
		            	scope: this
		            }
			        ]
		        }]
		      
            }]
        }];
		this.callParent(arguments);
		this.onReset();
		
	},
	
	onSubmit : function() {
    	var form = this.down('form').getForm();
        if (form.isValid()) {
            form.submit({
            	url: 'system/teacher/set',
            	waitTitle : "数据传输",
				waitMsg : "数据传输中,请稍等...",
                success: function(form, action) {
                },
                failure: function(form, action) {                   
                	var responseText = action.response.responseText;
                	var json= Ext.JSON.decode(responseText);
                	Ext.Msg.alert('失败', json.message);
                }
            });
        }
    },
    
    onReset : function() {
    	var updateTeacherForm = this.down("form").getForm();
		var data = bit.Ajax.Synchronize("system/teacher/getCurrentTeacher").data;
		var obj = {
			'id':data.id,
			'user.id':data.user.id,
			'user.username': data.user.username,
			'user.password': data.user.password,
			'user.name': data.user.name,
			'user.sex': data.user.sex,
			'user.number': data.user.number,
			'user.phone': data.user.phone,
			'user.idNo': data.user.idNo,
			'user.email': data.user.email,
			'user.homeAddress': data.user.homeAddress,
			'officeAddress': data.officeAddress,
			'officePhone': data.officePhone,
			'title': data.title
		}
		updateTeacherForm.setValues(obj);
    }
});
