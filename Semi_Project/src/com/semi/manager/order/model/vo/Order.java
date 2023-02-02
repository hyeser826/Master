package com.semi.manager.order.model.vo;

import java.sql.Date;

public class Order {
	private int orderNo;//주문번호
	private int memNo;//회원번호
	private String payName;//결제수단이름
	private String orderAddress;//배송지주소
	private String reciverName;//수령자이름
	private String reciverPhone;//수령자전화번호
	private Date orderDate;//주문일자
	private String memFlag;//회원여부
	
	
	public Order() {
		super();
	}
	public Order(int orderNo, int memNo, String payName, String orderAddress, String reciverName, String reciverPhone,
			Date orderDate, String memFlag) {
		super();
		this.orderNo = orderNo;
		this.memNo = memNo;
		this.payName = payName;
		this.orderAddress = orderAddress;
		this.reciverName = reciverName;
		this.reciverPhone = reciverPhone;
		this.orderDate = orderDate;
		this.memFlag = memFlag;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public String getReciverName() {
		return reciverName;
	}
	public void setReciverName(String reciverName) {
		this.reciverName = reciverName;
	}
	public String getReciverPhone() {
		return reciverPhone;
	}
	public void setReciverPhone(String reciverPhone) {
		this.reciverPhone = reciverPhone;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getMemFlag() {
		return memFlag;
	}
	public void setMemFlag(String memFlag) {
		this.memFlag = memFlag;
	}
	
	
	
	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", memNo=" + memNo + ", payName=" + payName + ", orderAddress="
				+ orderAddress + ", reciverName=" + reciverName + ", reciverPhone=" + reciverPhone + ", orderDate="
				+ orderDate + ", memFlag=" + memFlag + "]";
	}
}
