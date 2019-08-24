Ext.define('bit.TreeNodeModel', {
    extend: 'Ext.data.Model',
    fields: [
    	{
			name : "id",
			type : "string"
    	}, 
    	{
			name : "text",
			type : "string"
	    }, 
	    {
			name : "leaf",
			type : "boolean"
	    },
	    {
	    	name : "expanded",
			type : "boolean"
	    },
	    {
	    	name : "checked",
	    	type : "boolean"
	    },
	    {
	    	name : "checkStatus",
	    	type : "string"
	    }
	   
	]
}); 