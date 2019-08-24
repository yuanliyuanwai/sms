Ext.define('bit.TabPanel',{ 
    extend: 'Ext.tab.Panel', 
    requires: ['Ext.ux.TabScrollerMenu'],
    
    initComponent : function(){ 
        Ext.apply(this,{ 
            id: 'content-panel', 
            region: 'center',  
            defaults: { 
               autoScroll: false,
               bodyPadding: 0 
            }, 
            activeTab: 0, 
            border: false, 
            autoScroll : false,
           //plain: true, 
            items: [{ 
              id: 'HomePage', 
              title: '首页', 
              iconCls:'home', 
              layout: 'fit'
            }],
            plugins: [{
                ptype: 'tabscrollermenu',
                maxText  : 15,
                pageSize : 5
            }]
        }); 
        this.callParent(arguments); 
    } ,
    /**
	 * 激活TAB页时改变内部容器的大小
	 */
	onActiveTabSize : function() {
		// 获取当前活动的tab页的body元素的宽度 (不含任何框架元素)
		var w = Ext.getCmp('content-panel').getActiveTab().getInnerWidth();
		// 获取当前活动的tab页的body元素的高度 (不含任何框架元素)
		var h = Ext.getCmp('content-panel').getActiveTab().getInnerHeight();
		// 获取当活动的tab页的id
		var Atab = Ext.getCmp('content-panel').getActiveTab().id;

		// 获取组件
		var submain = Ext.getCmp(Atab);

		if (submain) {
			submain.setWidth(w);
			submain.setHeight(h);
		}
	},
	openTab : function(className,title,item, obj) {//传入 数据项ID
		if (className) {
			var panel = Ext.getCmp(className);
			if (!panel) {
				var _obj= obj?obj:{};
				_obj.id= className;
				_obj.title= title;
				_obj.ditem= item;
				_obj.border= false;
				_obj.closable= true;
				_obj.listeners= {scope : this}
				panel = Ext.create(className,_obj);
//				panel =Ext.create(className,{
//					id : className, // tab的唯一id
//					title : title, // tab的标题
//					ditem:item,
					//iconCls : "", // 图片
					//layout : 'fit', // 填充布局,它不会让load进来的东西改变大小
//					border : false, // 无边框
//					closable : true, // 有关闭选项卡按钮
//					listeners : {
						// 侦听tab页被激活里触发的动作
						//activate : this.onActiveTabSize,
//						scope : this
//					}
//				});
				var p = this.add(panel);
				this.setActiveTab(p);
			} else {
				
				this.setActiveTab(panel);
			}
		}

	},
	
	/**
	 * 没有就打开，有就关闭之后重新打开
	 * @param className
	 * @param title
	 * @param item
	 * @param obj
	 */
	reOpenTab : function(className,title,item, obj) {//传入 数据项ID
		if (className) {
			var panel = Ext.getCmp(className);
			if (panel) {
				this.remove(panel);
			}
			var _obj= obj?obj:{};
			_obj.id= className;
			_obj.title= title;
			_obj.ditem= item;
			_obj.border= false;
			_obj.closable= true;
			_obj.listeners= {scope : this}
			panel = Ext.create(className,_obj);
			var p = this.add(panel);
			this.setActiveTab(p);
		}
	}
    
}) 
