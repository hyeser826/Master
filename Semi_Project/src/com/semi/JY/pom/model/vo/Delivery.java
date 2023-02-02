package com.semi.JY.pom.model.vo;

import java.sql.Date;

public class Delivery {  
private int orderDetailNo;//	주문상세번호
private String deliveryCategory;//	배송방법
private String deliveryInvoice;//	송장번호
private String deliveryShipping;//배송추적
private Date deliveryShipDate;//발송일	
private String deliveryParcel;//	택배사
private String status;
public Delivery() {
	super();
}
public Delivery(int orderDetailNo, String deliveryCategory, String deliveryInvoice, String deliveryShipping,
		Date deliveryShipDate, String deliveryParcel, String status) {
	super();
	this.orderDetailNo = orderDetailNo;
	this.deliveryCategory = deliveryCategory;
	this.deliveryInvoice = deliveryInvoice;
	this.deliveryShipping = deliveryShipping;
	this.deliveryShipDate = deliveryShipDate;
	this.deliveryParcel = deliveryParcel;
	this.status = status;
}
public int getOrderDetailNo() {
	return orderDetailNo;
}
public void setOrderDetailNo(int orderDetailNo) {
	this.orderDetailNo = orderDetailNo;
}
public String getDeliveryCategory() {
	return deliveryCategory;
}
public void setDeliveryCategory(String deliveryCategory) {
	this.deliveryCategory = deliveryCategory;
}
public String getDeliveryInvoice() {
	return deliveryInvoice;
}
public void setDeliveryInvoice(String deliveryInvoice) {
	this.deliveryInvoice = deliveryInvoice;
}
public String getDeliveryShipping() {
	return deliveryShipping;
}
public void setDeliveryShipping(String deliveryShipping) {
	this.deliveryShipping = deliveryShipping;
}
public Date getDeliveryShipDate() {
	return deliveryShipDate;
}
public void setDeliveryShipDate(Date deliveryShipDate) {
	this.deliveryShipDate = deliveryShipDate;
}
public String getDeliveryParcel() {
	return deliveryParcel;
}
public void setDeliveryParcel(String deliveryParcel) {
	this.deliveryParcel = deliveryParcel;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
@Override
public String toString() {
	return "Delivery [orderDetailNo=" + orderDetailNo + ", deliveryCategory=" + deliveryCategory + ", deliveryInvoice="
			+ deliveryInvoice + ", deliveryShipping=" + deliveryShipping + ", deliveryShipDate=" + deliveryShipDate
			+ ", deliveryParcel=" + deliveryParcel + ", status=" + status + "]";
}





}
