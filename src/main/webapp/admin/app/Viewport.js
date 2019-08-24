Ext.define('bit.Viewport',{ 
    extend: 'Ext.Viewport', 
    layout:'fit',
    hideBorders: true, 
    requires : [ 
        'bit.Header', 
        'bit.Menu', 
        'bit.TabPanel', 
        'bit.South' 
    ], 
    initComponent : function(){ 
        var me = this; 
        Ext.apply(me, { 
            items: [{ 
                id:'desk', 
                layout: 'border', 
                items: [ 
                    Ext.create('bit.Header'), 
                    Ext.create('bit.Menu'), 
                    Ext.create('bit.TabPanel'), 
                    Ext.create('bit.South') 
                ],
                listeners : {
        			afterrender : function() {
        				Ext.getBody().mask('正在加载导航菜单....');        				
        				var menuPanel = Ext.getCmp("MainMenu");
        				Ext.Ajax.request({
        			        url: "system/menu/findByParentId",   
        			        method: "get",
        			        success: function(arg) {
        			        	Ext.getBody().unmask();
        			        	if(arg){
        			        		var json = Ext.JSON.decode(arg.responseText);
        			        		if(json && json.data){
        			        			for(var i=0; i<json.data.length; i++){        			        				
        			        				menuPanel.add(Ext.create("bit.MenuTree", {
        			        					autoScroll : true,
        			        					rootVisible : false,
        			        					viewConfig : {
        			        						loadingText : "正在加载..."
        			        					},	
        				        				title : json.data[i].text,
        				        				iconCls : json.data[i].icon,
        				        				store: Ext.create("Ext.data.TreeStore", {
        				        						// 根节点的参数是parentId
        				        						defaultRootId: json.data[i].id,
        				        			            nodeParam : 'parentId',
        				        			            model:'bit.MenuModel',
        				        			            autoLoad: true,        				        			            
        				        						proxy : {
        				        							type : "ajax",
        				        							url : "system/menu/findByParentId?systemCode=upms&clientTypeCode=PC",
        				        							reader:	{
        				        								type: "json",
        				        								root: "data"
        				        							}
        				        						},
        				        						folderSort: true,
        				        		                sorters: [{
        				        		                    property: 'sort',
        				        		                    direction: 'ASC'
        				        		                }]
        				        					})
        				        			}));
        			        				
        			        			}
        			        		}
        			        	}

        			        },
        			        failure: function(response) {
        			        	Ext.getBody().unmask();
        			            Ext.Msg.alert("操作提示", "导航菜单请求出错！");
        			       }
        				});
        				menuPanel.doLayout();
        			}
        		}
            }] 
        }); 
        me.callParent(arguments); 
    } 
}) 
