Ext.define("bit.system.TeacherPanel" ,{
    extend: "Ext.panel.Panel",
    border: false,
    
	initComponent: function() {
		this.layout= "border";
		
	    this.items= [{
                region: "center",
                split: false,
                layout: "fit",
                collapsible: false,
                items: [{
                	xtype: "panel",
                	layout: {
				        type:"vbox",
				        align:"stretch"
				    },
                	items: [{
		            	title : "查询条件",
		                layout : "fit",
		                border : false,
		                split : false,
		                collapsible : true,
		                items : [Ext.create("bit.system.TeacherConditionPanel", {storeId : "teacher_teacherGridStore"})]
		            }, 
                	Ext.create("bit.system.TeacherGrid", {
                		storeId : "teacher_teacherGridStore",
		            	flex: 1,
	                	isShowTbar: true
	                }
	                )]
                }
                
                ]
            }
        ];
		this.callParent(arguments);
	}
});
