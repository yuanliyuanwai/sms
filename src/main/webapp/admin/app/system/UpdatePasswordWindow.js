Ext.define('bit.system.UpdatePasswordWindow', {
    extend: 'Ext.window.Window',
    requires: ['Ext.form.Panel','Ext.form.field.Text'],
    title : '修改密码',
    layout: 'fit',    
    width: 350,
    
    iconCls: 'bis-panel-header-user',

    initComponent: function() {
        this.items = [
            {
                xtype: 'form',
                padding: '5 5 0 5',
                border: false,
                style: 'background-color: #fff;',                
                
                fieldDefaults: {
                    anchor: '100%',
                    labelAlign: 'left',
                    allowBlank: false,
                    combineErrors: true,
                    msgTarget: 'side'
                },

                items : [ {
            		xtype : 'textfield',
            		name : 'name',
            		fieldLabel : '名称',
            		readOnly : true,
            		value: bit.Ajax.Synchronize("system/user/getUsername").data
            	}, {
            		 xtype: 'textfield',
                     name: 'password',
                     inputType: 'password',
                     fieldLabel: '原始密码',
                     allowBlank: false,
            	}, {
            		xtype: 'textfield',
                    name: 'password2',
                    inputType: 'password',
                    fieldLabel: '新密码',
                    allowBlank: false
            	} ]
            }
        ];
        
        this.dockedItems = [{
            xtype: 'toolbar',
            dock: 'bottom',
            id:'buttons',
            ui: 'footer',
            items: ['->', {
            	iconCls: 'bis-panel-save',               
                text: '保存',
                scope: this,
                handler: this.onSubmit
            },{
            	iconCls: 'bis-panel-cancel',
                text: '取消',
                scope: this,
                handler: this.close
            }]
        }];

        this.callParent(arguments);
    },
		
    onSubmit : function() {
    	var form = this.down('form').getForm();
        if (form.isValid()) {
            form.submit({
            	url: 'system/user/updatePassword',
            	waitTitle : "数据传输",
				waitMsg : "数据传输中,请稍等...",
                success: function(form, action) {
                	this.form.owner.up('window').close();
                },
                failure: function(form, action) {                   
                    //Ext.Msg.alert('Failed', action.failureType);
                	var responseText = action.response.responseText;
                	var json= Ext.JSON.decode(responseText);
                	Ext.Msg.alert('失败', json.message);
                }
            });
        }
    }
});
