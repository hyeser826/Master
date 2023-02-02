package com.semi.order.model.vo;

import java.sql.Date;

public class Delivery {
	private int orderDetailNo;				//	ORDER_DETAIL_NO	NUMBER	No		1	주문번호
	private String deliveryCategory;	//	DELIVERY_CATEGORY	VARCHAR2(20 BYTE)	Yes		2	배송방법
	private String deliveryInvoiceNo;	//	DELIVERY_INVOICE_NO	VARCHAR2(200 BYTE)	Yes		3	송장번호
	private Date deliveryShipDate;		//	DELIVERY_SHIP_DATE	DATE	Yes	SYSDATE 	5	발송일
	private String deliveryParcel;		//	DELIVERY_PARCEL	VARCHAR2(150 BYTE)	Yes		6	택배사
	private String status;				//	STATUS	VARCHAR2(1 BYTE)	Yes	'N' 	7	처리상태
	
	public Delivery() {
		super();
	}

	public Delivery(int orderDetailNo, String deliveryCategory, String deliveryInvoiceNo, Date deliveryShipDate,
			String deliveryParcel, String status) {
		super();
		this.orderDetailNo = orderDetailNo;
		this.deliveryCategory = deliveryCategory;
		this.deliveryInvoiceNo = deliveryInvoiceNo;
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

	public String getDeliveryInvoiceNo() {
		return deliveryInvoiceNo;
	}

	public void setDeliveryInvoiceNo(String deliveryInvoiceNo) {
		this.deliveryInvoiceNo = deliveryInvoiceNo;
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
		return "Delivery [orderDetailNo=" + orderDetailNo + ", deliveryCategory=" + deliveryCategory + ", deliveryInvoiceNo="
				+ deliveryInvoiceNo + ", deliveryShipDate=" + deliveryShipDate + ", deliveryParcel=" + deliveryParcel
				+ ", status=" + status + "]";
	}
	
}
