package com.semi.order.model.vo;

import java.sql.Date;

public class OrderDetail {
	
	private int orderDetailNo;		// ORDER_DETAIL_NO 주문상세번호	
	private int productNo;			// PRODUCT_NO 상품번호		
	private int orderNo;			// ORDER_NO 주문번호		
	private int productCount;		// PRODUCT_COUNT 수량	
	private int productPrice;		// PRODUCT_PRICE 가격	
	private String orderStatus;		// ORDER_STATUS 처리상태		
	private Date expDate;			// E_DATE 체험일	
	private String memFlag;			// MEM_FLAG회원여부
	
	private String titleImg;		// TITLEIMG 대표이미지
	private String productName;		// PRODUCT_NAME 상품명
	private int masterNo;			// MASTER_NO 장인번호 - 장인별로 뿌려줘야 하니까.
	private String deliveryInvoiceNo;	// DELIVERY_INVOICE_NO 송장번호
	private String deliveryParcel;		// DELIVERY_PARCEL 택배사
	private Date deliveryShipDate;		// DELIVERY_SHIP_DATE 발송일
	private String strName;				// STR_NAME 상점명
	
	public OrderDetail() {
		super();
	}
	
	public OrderDetail(String productName, int productPrice, int productCount, 
			String orderStatus, String titleImg){
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productCount = productCount;
		this.orderStatus = orderStatus;
		this.titleImg = titleImg;
	}
	
	public OrderDetail(String productName, int productPrice, int productCount, 
			String orderStatus, Date expDate, String titleImg){
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.productCount = productCount;
		this.orderStatus = orderStatus;
		this.expDate = expDate;
		this.titleImg = titleImg;
	}

	public OrderDetail(int orderDetailNo, int productNo, int orderNo, int productCount, int productPrice,
			String orderStatus, Date expDate, String titleImg, String productName, int masterNo) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.productNo = productNo;
		this.orderNo = orderNo;
		this.productCount = productCount;
		this.productPrice = productPrice;
		this.orderStatus = orderStatus;
		this.expDate = expDate;
		this.titleImg = titleImg;
		this.productName = productName;
		this.masterNo = masterNo;
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
	
	public OrderDetail(int orderDetailNo, int productNo, int orderNo, int productCount, int productPrice,
			String orderStatus, Date expDate, String titleImg, String productName, int masterNo,
			String deliveryInvoiceNo, String deliveryParcel, Date deliveryShipDate) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.productNo = productNo;
		this.orderNo = orderNo;
		this.productCount = productCount;
		this.productPrice = productPrice;
		this.orderStatus = orderStatus;
		this.expDate = expDate;
		this.titleImg = titleImg;
		this.productName = productName;
		this.masterNo = masterNo;
		this.deliveryInvoiceNo = deliveryInvoiceNo;
		this.deliveryParcel = deliveryParcel;
		this.deliveryShipDate = deliveryShipDate;
	}

	public OrderDetail(int productCount, int productPrice, String orderStatus, String titleImg, String productName) {
		super();
		this.productCount = productCount;
		this.productPrice = productPrice;
		this.orderStatus = orderStatus;
		this.titleImg = titleImg;
		this.productName = productName;
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

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getMasterNo() {
		return masterNo;
	}

	public void setMasterNo(int masterNo) {
		this.masterNo = masterNo;
	}
	
	public String getDeliveryInvoiceNo() {
		return deliveryInvoiceNo;
	}

	public void setDeliveryInvoiceNo(String deliveryInvoiceNo) {
		this.deliveryInvoiceNo = deliveryInvoiceNo;
	}

	public String getDeliveryParcel() {
		return deliveryParcel;
	}

	public void setDeliveryParcel(String deliveryParcel) {
		this.deliveryParcel = deliveryParcel;
	}

	public Date getDeliveryShipDate() {
		return deliveryShipDate;
	}

	public void setDeliveryShipDate(Date deliveryShipDate) {
		this.deliveryShipDate = deliveryShipDate;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	@Override
	public String toString() {
		return "OrderDetail [orderDetailNo=" + orderDetailNo + ", productNo=" + productNo + ", orderNo=" + orderNo
				+ ", productCount=" + productCount + ", productPrice=" + productPrice + ", orderStatus=" + orderStatus
				+ ", expDate=" + expDate + ", memFlag=" + memFlag + ", titleImg=" + titleImg + ", productName="
				+ productName + ", masterNo=" + masterNo + ", deliveryInvoiceNo=" + deliveryInvoiceNo
				+ ", deliveryParcel=" + deliveryParcel + ", deliveryShipDate=" + deliveryShipDate + "]";
	}

}
