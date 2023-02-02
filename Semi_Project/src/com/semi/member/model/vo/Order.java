package com.semi.member.model.vo;

import java.sql.Date;

public class Order {
	private int orderNo;
	private int memNo;
	private int payNo;
	private String orderAddress;
	private String receiverName;
	private String receiverPhone;
	private Date orderDate;
	private String memFlag;
	
	public Order() {
		super();
	}
	
	public Order(int orderNo, String receiverName) {
		super();
		this.orderNo = orderNo;
		this.receiverName = receiverName;
	}

	public Order(int orderNo, int memNo, int payNo, String orderAddress, String receiverName, String receiverPhone,
			Date orderDate, String memFlag) {
		super();
		this.orderNo = orderNo;
		this.memNo = memNo;
		this.payNo = payNo;
		this.orderAddress = orderAddress;
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.orderDate = orderDate;
		this.memFlag = memFlag;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNum) {
		this.orderNo = orderNum;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public int getPayNo() {
		return payNo;
	}

	public void setPayNo(int payNo) {
		this.payNo = payNo;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getReciverName() {
		return receiverName;
	}

	public void setReciverName(String reciverName) {
		this.receiverName = reciverName;
	}

	public String getReciverPhone() {
		return receiverPhone;
	}

	public void setReciverPhone(String reciverPhone) {
		this.receiverPhone = reciverPhone;
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
		return "Order [orderNo=" + orderNo + ", memNo=" + memNo + ", payNo=" + payNo + ", orderAddress="
				+ orderAddress + ", reciverName=" + receiverName + ", reciverPhone=" + receiverPhone + ", orderDate="
				+ orderDate + ", memFlag=" + memFlag + "]";
	}
	
	
	
}












