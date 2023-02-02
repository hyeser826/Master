package com.semi.refund.model.vo;

public class Refund {
	
	private int refundNo;//환불번호
	private int orderDetailNo;//주문상세번호
	private String refundReason;//환불사유
	private String refundFlag;//환불가능여부
	
	
	public Refund() {
		super();
	}
	public Refund(int refundNo, int orderDetailNo, String refundReason, String refundFlag) {
		super();
		this.refundNo = refundNo;
		this.orderDetailNo = orderDetailNo;
		this.refundReason = refundReason;
		this.refundFlag = refundFlag;
	}
	
	
	public int getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(int refundNo) {
		this.refundNo = refundNo;
	}
	public int getOrderDetailNo() {
		return orderDetailNo;
	}
	public void setOrderDetailNo(int orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public String getRefundFlag() {
		return refundFlag;
	}
	public void setRefundFlag(String refundFlag) {
		this.refundFlag = refundFlag;
	}
	
	
	@Override
	public String toString() {
		return "Refund [refundNo=" + refundNo + ", orderDetailNo=" + orderDetailNo + ", refundReason=" + refundReason
				+ ", refundFlag=" + refundFlag + "]";
	}
}
