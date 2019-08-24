Ext.define('bit.system.StudentWindow', {
    extend: 'Ext.window.Window',

    requires: ['Ext.form.Panel','Ext.form.field.Text'],

    title : '学生信息',
    layout: 'fit',    
    width: 350,
    modal: true,
    
    storeId : '',
    readOnly : false,
    allowBlank : false,
    isUpdate : false,

    initComponent: function() {
        this.items = [
          Ext.create("bit.system.StudentForm",
				{onReadOnly: this.readOnly, onAllowBlank: this.allowBlank, onUpdate: this.isUpdate})
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
    	var me = this;
    	var form = this.down('form').getForm();
    	var idVaue = form.findField("id").getValue();
    	var url = '';
    	if (idVaue != undefined && idVaue != null && idVaue != '') {
    		url = 'system/student/set';
    	} else {
    		url = 'system/student/add';
    	}
        if (form.isValid()) {
            form.submit({
            	url: url,
            	waitTitle : "数据传输",
				waitMsg : "数据传输中,请稍等...",
                success: function(form, action) {
                	this.form.owner.up('window').close();
                	var store = Ext.data.StoreManager.lookup(me.storeId);
                	store.reload();
                    Ext.Msg.alert('操作提示','操做成功!');                   
                },
                failure: function(form, action) {                   
                    Ext.Msg.alert('操作提示', action.failureType);
                }
            });
        }
    },
    
    loadRecord: function(obj){
    	var form = this.down("form").getForm();
    	form.setValues(obj);
    }
});
