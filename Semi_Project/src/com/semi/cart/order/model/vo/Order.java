package com.semi.cart.order.model.vo;

import java.sql.Date;

public class Order {
	
	private int orderNo; 			// ORDER_NO 주문번호
	private int memNo; 				// MEM_NO 회원번호
	private String payName; 		// PAY_NAME 결제수단이름
	private String orderAddress; 	// ORDER_ADDRESS 배송지주소
	private String reciverName; 	// RECIVER_NAME 수령자이름
	private String reciverPhone; 	// RECIVER_PHONE 수령자전화번호
	private Date orderDate; 		// ORDER_DATE 주문일자
	private String menFlag; 		// MEM_FLAG 회원여부
	
	private String memName;		//MEM_NAME 주문자 이름
	
	public Order() {
		super();
	}
	   
	public Order(int orderNo, int memNo, String payName, String orderAddress, String reciverName, String reciverPhone,
			Date orderDate) {
		super();
		this.orderNo = orderNo;
		this.memNo = memNo;
		this.payName = payName;
		this.orderAddress = orderAddress;
		this.reciverName = reciverName;
		this.reciverPhone = reciverPhone;
		this.orderDate = orderDate;
	}
   
	public Order(int orderNo, int memNo, String memName, String payName, String orderAddress, String reciverName, String reciverPhone,
			Date orderDate) {
		super();
		this.orderNo = orderNo;
		this.memNo = memNo;
		this.memName = memName;
		this.payName = payName;
		this.orderAddress = orderAddress;
		this.reciverName = reciverName;
		this.reciverPhone = reciverPhone;
		this.orderDate = orderDate;
	}
	   
	public Order(int orderNo, int memNo, String payName, String orderAddress, String reciverName, String reciverPhone,
			Date orderDate, String menFlag, String memName) {
		super();
		this.orderNo = orderNo;
		this.memNo = memNo;
		this.payName = payName;
		this.orderAddress = orderAddress;
		this.reciverName = reciverName;
		this.reciverPhone = reciverPhone;
		this.orderDate = orderDate;
		this.menFlag = menFlag;
		this.memName = memName;
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
	   
	public String getMenFlag() {
		return menFlag;
	}
	   
	public void setMenFlag(String menFlag) {
		this.menFlag = menFlag;
	}
	   
	public String getMemName() {
		return memName;
	}
	   
	public void setMemName(String memName) {
		this.memName = memName;
	}
	   
	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", memNo=" + memNo + ", payName=" + payName + ", orderAddress="
				+ orderAddress + ", reciverName=" + reciverName + ", reciverPhone=" + reciverPhone + ", orderDate="
				+ orderDate + ", menFlag=" + menFlag + ", memName=" + memName + "]";
	}
	   
}
