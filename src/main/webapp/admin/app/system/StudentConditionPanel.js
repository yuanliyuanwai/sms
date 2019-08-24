Ext.define("bit.system.StudentConditionPanel" ,{
    extend: "Ext.panel.Panel",
    
    border: false,
    split: false,
    layout: "fit",
    collapsible: false,
    
    storeId : '',
    
	initComponent: function() {
		
	    this.items= [{
	    	xtype: "form",
            flex: 1,
//            bodyPadding: 5,
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
		        	defaults: {
			            border: false,
			            xtype: 'panel',
			            flex: 1,
			            layout: 'anchor'
			        },
			        layout: 'hbox',
			        items: [
			            {
			            	xtype: "textfield",
				    		name: "name",
				    		fieldLabel: "名称"
			            },
			            {
			            	xtype: "textfield",
				    		name: "username",
				    		fieldLabel: "登录名"
			            },
			            {
		            	xtype: "button",
		            	margins: "0 0 0 20",
		            	iconCls: "bis-idc-search",
		            	text: "查询",
		            	handler: function(bt, e){
                			var condition= this.getCondition();
		            		var store = Ext.data.StoreManager.lookup(this.storeId);
		            		store.getProxy().extraParams= condition;
				    		store.loadPage(1); 
		            	},
		            	scope: this
		            }, {
		            	xtype: "button",
		            	margins: "0 0 0 10",
		            	iconCls: "bis-idc-clean-condition",
		            	text: "清除",
		            	handler: function(bt, e){
                			var _f= this.down("form").getForm();
                			if(_f){
                				_f.reset();	
                			}
		            	},
		            	scope: this
		            }
			        ]
		        }]
		      
            }]
        }];
	    
		this.callParent(arguments);
	},
	getCondition: function(){
		var condition= {
			"name":"",
			"username":""
		};
		            		
		var form= this.down("form").getForm();
		
		var name = form.findField("name");
		var username = form.findField("username");
		
		if(name.getValue()){
			condition.name = name.getValue();
		}
		if(username.getValue()){
			condition.username = username.getValue();
		}
		return condition;
	}
});
