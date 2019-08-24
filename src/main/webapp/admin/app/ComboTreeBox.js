Ext.define('bit.ComboTreeBox',{
	
	extend : 'Ext.form.field.ComboBox',
	
	/**
	 * 是否多选
	 * @type Boolean
	 */
	multiSelect : true,
	
	/**
	 * 是否只能选择叶子节点
	 * @type Boolean
	 */
	onlyLeafSelect : false,
	
	/**
	 * 填充的表单项的名称
	 * @type String
	 */
	valueName : '',
	
	editable : false,
	
	createPicker : function(){
		var me = this;
		//创建树控件
		var picker = Ext.create('Ext.tree.Panel', {
		    store: me.store,
		    rootVisible: false,
		    selModel: {
                mode: me.multiSelect ? 'SIMPLE' : 'SINGLE'
            },
		    floating: true,
		    width : 300,
            hidden: true,
            focusOnToFront: false,
            autoScroll : true
		});
		//注册事件用于选择用户选择的值
		//注册事件用于选择用户选择的值
		me.mon(picker, {
            itemclick: me.onItemClick,
            scope: me
        });
        
        me.mon(picker.getSelectionModel(), {
            beforeselect: me.onBeforeSelect,
            beforedeselect: me.onBeforeDeselect,
            selectionchange: me.onListSelectionChange,
            scope: me
        });
		this.picker = picker;
		return picker;
	},
	
	onItemClick: function(picker, record){
        /*
         * If we're doing single selection, the selection change events won't fire when
         * clicking on the selected element. Detect it here.
         */
        var me = this,
            selection = me.picker.getSelectionModel().getSelection(),
            valueField = me.valueField;
            
        me._idValue = record.get("id");
        var nameValue = record.get("text");
        var checkValue = record.get("checked");

        if (!me.multiSelect && selection.length) {
            if (record.get(valueField) === selection[0].get(valueField)) {
                // Make sure we also update the display value if it's only partial
                if (!me.onlyLeafSelect || (me.onlyLeafSelect && record.get("leaf"))) {
                	me.displayTplData = [record.data];
                    me.setRawValue(me.getDisplayValue());
    	            var parentComponent = me.ownerCt;
                    var idField = parentComponent.getForm().findField(me.valueName);
    	            idField.setValue(record.get("id"));
                    me.collapse();
                }
            }
        }
    },
    
    onListSelectionChange : function() {
    	
	},
	
    
    /**
     * 需要重写onLoad事件，不需要调用setValue()方法
     * @param store
     * @param records
     * @param success
     */
    onLoad : function(store, records, success) {
    }
   
});