Ext.define('bit.MenuTree', { 
    extend: 'Ext.tree.Panel', 
    initComponent: function() {    	
        Ext.applyIf(this, {        	
			listeners : {
				afterlayout : function() {},
				itemclick :this.onMenuClick				
			}
        }); 
        this.callParent(arguments); 
    },    

	onMenuClick : function(view, _node) {
		var main = Ext.getCmp("content-panel");
		main.openTab(_node.raw.linkUrl,_node.raw.text,{id:_node.get('id')});
	}
}); 
