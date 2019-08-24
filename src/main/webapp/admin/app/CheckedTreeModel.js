Ext.define('bit.CheckedTreeModel', {
    extend: 'Ext.data.Model',
    fields: [{
		name : "id",
		type : "string"
	}, {
		name : "text",
		type : "string"
	}, {
		name : "href",
		type : "string"
	}, {
		name : "leaf",
		type : "boolean"
	},{
		name : "checked",
		type : "boolean"
	},
	{
		name : "checkStatus",
		type : "string"
	}
	]
}); 