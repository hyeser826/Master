package com.semi.manager.orderdetail.model.vo;

import java.sql.Date;

public class OrderDetail {
	
	private int orderDetailNo;//주문상세번호
	private int productNo;//상품번호
	private int orderNo;//주문번호
	private int productCount;//수량
	private int productPrice;//가격
	private String orderStatus;//처리상태
	private Date expDate;//체험일
	private String memFlag;//회원여부
	
	//추가
	private String productName;
	private String filePath;
	private int deliveryFee;
	private String strName;
	private String reciverName;
	private String reciverPhone;
	
	
	public OrderDetail() {
		super();
	}
	public OrderDetail(int orderDetailNo, int productNo, int orderNo, int productCount, int productPrice,
			String orderStatus, Date expDate, String memFlag) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.productNo = productNo;
		this.orderNo = orderNo;
		this.productCount = productCount;
		this.productPrice = productPrice;
		this.orderStatus = orderStatus;
		this.expDate = expDate;
		this.memFlag = memFlag;
	}
	
	public int getOrderDetailNo() {
		return orderDetailNo;
	}
	public void setOrderDetailNo(int orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public String getMemFlag() {
		return memFlag;
	}
	public void setMemFlag(String memFlag) {
		this.memFlag = memFlag;
	}
	
	//추가
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
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
	
	
	
	@Override
	public String toString() {
		return "OrderDetail [orderDetailNo=" + orderDetailNo + ", productNo=" + productNo + ", orderNo=" + orderNo
				+ ", productCount=" + productCount + ", productPrice=" + productPrice + ", orderStatus=" + orderStatus
				+ ", expDate=" + expDate + ", memFlag=" + memFlag + "]";
	}
}
