Ext.define('bit.MenuModel', {
		     extend: 'Ext.data.Model',
		     fields : [{
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
				}, {
					name : "sort",
					type : "int"
				} ]
});