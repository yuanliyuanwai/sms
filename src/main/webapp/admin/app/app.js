Ext.Loader.setConfig({enabled: true});

Ext.Loader.setPath('bit', 'admin/app');

Ext.require([
	'bit.Header',
	'bit.TabPanel',
	'bit.South',
	'bit.Viewport',
	'bit.Menu',
	'bit.Main',
	'bit.MenuModel',
	'bit.MenuTree'	
]);

Ext.application({
    name: 'ADMIN',
    autoCreateViewport: false, 
    appFolder: 'admin/app',
    controllers: [
        'bit.Main'
    ],
    launch: function() {
        Ext.create('bit.Viewport');
    }
});


Ext.selection.Model.override({  
    onStoreLoad:function(store, records, successful, eOpts){  
        var selectLine = this,  
            length = selectLine.selected.getCount( );  
          
        //如果没有选中的记录，则不需要进行任何的操作  
        if(length===0) return;  
          
        //遍历selected并更新其中的记录  
        selectLine.selected.eachKey(function(key,item){  
            var model = store.getById(key);  
              
            //如果获取到了model就更新，否则从selected中移除  
            if(model){  
            	selectLine.selected.add(model);//add时会覆盖掉原来的值  
            }else{  
            	selectLine.selected.removeAtKey(key);  
            }  
        });
          
    }  
});  
