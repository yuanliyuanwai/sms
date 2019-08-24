Ext.define('bit.South',{ 
    extend: 'Ext.Toolbar', 
    initComponent : function(){ 
        Ext.apply(this,{ 
            id:"bottom", 
            //frame:true, 
            region:"south", 
            height:23 
        }); 
        this.callParent(arguments); 
    } 
}) 
