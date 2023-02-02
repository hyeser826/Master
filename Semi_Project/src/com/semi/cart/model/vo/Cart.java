package com.semi.cart.model.vo;

import java.sql.Date;

public class Cart {

	private int cartNo; // CART_NO 장바구니 번호
	private String strNm; // STR_NAME 장인홈상점이름
	private String categoryNm; //CATEGORY_NAME md인지 ac인지 구분지어 카트담기
	private int productNo; // PRODUCT_NO 상품번호
	private String productNm; // PRODUCT_NAME 상품이름
	private int productPrice; // PRODUCT_PRICE 상품가격
	private int amount; // AMOUNT 상품별 수량
	private int mstNo; //장인번호

	private int totalPrice; // TOTAL_PRICE 수량X상품가격
	private int deliveryFee; // DELIVERY_FEE 배송비
	private Date eventDate; // E_DATE 체험일
	private String ssnId; // SSN_ID 세션ID
	private String memId; // MEM_ID 회원 예약ID
	private String memFlag; // MEM_FLAG 회원여부
	private Date cartDate; // CART_DATE 장바구니 생성일
	private String status; // STATUS 장바구니 상태
	
	
	
	public int getMstNo() {
		return mstNo;
	}



	public void setMstNo(int mstNo) {
		this.mstNo = mstNo;
	}



	public Cart(int cartNo, String strNm, String categoryNm, int productNo, String productNm, int productPrice,
			int amount, int mstNo, int totalPrice, int deliveryFee, Date eventDate, String ssnId, String memId,
			String memFlag, Date cartDate, String status) {
		super();
		this.cartNo = cartNo;
		this.strNm = strNm;
		this.categoryNm = categoryNm;
		this.productNo = productNo;
		this.productNm = productNm;
		this.productPrice = productPrice;
		this.amount = amount;
		this.mstNo = mstNo;
		this.totalPrice = totalPrice;
		this.deliveryFee = deliveryFee;
		this.eventDate = eventDate;
		this.ssnId = ssnId;
		this.memId = memId;
		this.memFlag = memFlag;
		this.cartDate = cartDate;
		this.status = status;
	}



	public Cart() {
		super();
	}
	
	

	public Cart(int cartNo, String strNm, String categoryNm, int productNo, String productNm, int productPrice, int amount, int totalPrice,
			int deliveryFee, Date eventDate, String ssnId, String memId, String memFlag, Date cartDate, String status) {
		super();
		this.cartNo = cartNo;
		this.strNm = strNm;
		this.categoryNm = categoryNm;
		this.productNo = productNo;
		this.productNm = productNm;
		this.productPrice = productPrice;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.deliveryFee = deliveryFee;
		this.eventDate = eventDate;
		this.ssnId = ssnId;
		this.memId = memId;
		this.memFlag = memFlag;
		this.cartDate = cartDate;
		this.status = status;	
	}

	public Cart(int cartNo, String strNm, String categoryNm, int productNo, String productNm, int productPrice, int amount, 
			int totalPrice, int deliveryFee, Date eventDate, String memId, String memFlag, Date cartDate, String status) {
		super();
		this.cartNo = cartNo;
		this.strNm = strNm;
		this.categoryNm = categoryNm;
		this.productNo = productNo;
		this.productNm = productNm;
		this.productPrice = productPrice;
		this.amount = amount;
		this.totalPrice = totalPrice;
		this.deliveryFee = deliveryFee;
		this.eventDate = eventDate;
		this.memId = memId;
		this.memFlag = memFlag;
		this.cartDate = cartDate;
		this.status = status;
	}

	
	
	public String getCategoryNm() {
		return categoryNm;
	}

	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}

	public int getCartNo() {
		return cartNo;
	}

	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}

	public String getStrNm() {
		return strNm;
	}

	public void setStrNm(String strNm) {
		this.strNm = strNm;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductNm() {
		return productNm;
	}

	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getSsnId() {
		return ssnId;
	}

	public void setSsnId(String ssnId) {
		this.ssnId = ssnId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemFlag() {
		return memFlag;
	}

	public void setMemFlag(String memFlag) {
		this.memFlag = memFlag;
	}

	public Date getCartDate() {
		return cartDate;
	}

	public void setCartDate(Date cartDate) {
		this.cartDate = cartDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", strNm=" + strNm + ", categoryNm=" + categoryNm + ", productNo=" + productNo
				+ ", productNm=" + productNm + ", productPrice=" + productPrice + ", amount=" + amount + ", mstNo="
				+ mstNo + ", totalPrice=" + totalPrice + ", deliveryFee=" + deliveryFee + ", eventDate=" + eventDate
				+ ", ssnId=" + ssnId + ", memId=" + memId + ", memFlag=" + memFlag + ", cartDate=" + cartDate
				+ ", status=" + status + "]";
	}

	

	
	
}