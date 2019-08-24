package cn.edu.bit.sms.common.hibernate.dto;

import cn.edu.bit.sms.common.hibernate.constant.OrderEnum;

/**
 * 排序
 * @author zhengchong.wan
 *
 */
public class PropertyOrder {
	
	private String propertyName;
	
	private OrderEnum orderType;
	
	public PropertyOrder(String propertyName) {
		this(propertyName, OrderEnum.ASC);
		
	}

	public PropertyOrder(String propertyName, OrderEnum orderType) {
		super();
		this.propertyName = propertyName;
		this.orderType = orderType;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public OrderEnum getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderEnum orderType) {
		this.orderType = orderType;
	}
	
}
