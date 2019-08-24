Ext.define('bit.Menu',{ 
    extend: 'Ext.panel.Panel', 
    initComponent : function(){ 
        Ext.apply(this,{ 
        	id : "MainMenu",
        	title : "导航菜单",
    		region : "west",		
    		width : 230,
    		minWidth: 100,
    		maxWidth: 250,

    		//margins: '5 0 3 3',
    		animCollapse: true,
    		autoScroll : false,		
    		collapsible : true,
    		/*tools:[
    		 {
    		    type:'refresh',
    		    tooltip: 'Refresh form Data',
    		    // hidden:true,
    		    handler: function(event, toolEl, panelHeader) {
    		        // refresh logic
    		    }
    		},
    		{
    		    type:'help',
    		    tooltip: 'Get Help',
    		    callback: function(panel, tool, event) {
    		        // show help here
    		    }
    		}],*/
    		layout:{
                type: 'accordion',
                animate: true
            },
    		split : true	
        }); 
        this.callParent(arguments); 
    } 
}) 
