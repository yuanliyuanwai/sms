Ext.define("bit.score.StudentScorePanel" ,{
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
		                items : [Ext.create("bit.score.StudentScoreConditionPanel", {storeId : "studentScore_studentScoreGridStore"})]
		            }, 
                	Ext.create("bit.score.StudentScoreGrid", {
                		storeId : "studentScore_studentScoreGridStore",
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
