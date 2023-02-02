package com.semi.JY.product.model.vo;

import java.sql.Date;

public class Product {
	private int productNo; //상품번호
	private String categoryName;//카테고리이름
	private int masterNo; //장인번호
	private String productName; //상품이름
	private int productPrice; //상품가격 
	private String productDescription; //상품설명
	private Date productDate; //등록일 
	private int productStock; //상품재고
	private int productHits; //조회수
	private String status; //상품상태
	private int deliveryFee; //배송비
	private String expPeriod; //체험기간
	
	//목록에 띄울 썸네일 주소 받아올 변수
	//private String titleImg;

	public Product() {
		super();
	}

	public Product(int productNo, String categoryName, int masterNo, String productName, int productPrice,
			String productDescription, Date productDate, int productStock, int productHits, String status,
			int deliveryFee, String expPeriod) {
		super();
		this.productNo = productNo;
		this.categoryName = categoryName;
		this.masterNo = masterNo;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productDescription = productDescription;
		this.productDate = productDate;
		this.productStock = productStock;
		this.productHits = productHits;
		this.status = status;
		this.deliveryFee = deliveryFee;
		this.expPeriod = expPeriod;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getMasterNo() {
		return masterNo;
	}

	public void setMasterNo(int masterNo) {
		this.masterNo = masterNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public int getProductStock() {
		return productStock;
	}

	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}

	public int getProductHits() {
		return productHits;
	}

	public void setProductHits(int productHits) {
		this.productHits = productHits;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getExpPeriod() {
		return expPeriod;
	}

	public void setExpPeriod(String expPeriod) {
		this.expPeriod = expPeriod;
	}

	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", categoryName=" + categoryName + ", masterNo=" + masterNo
				+ ", productName=" + productName + ", productPrice=" + productPrice + ", productDescription="
				+ productDescription + ", productDate=" + productDate + ", productStock=" + productStock
				+ ", productHits=" + productHits + ", status=" + status + ", deliveryFee=" + deliveryFee
				+ ", expPeriod=" + expPeriod + "]";
	}



}