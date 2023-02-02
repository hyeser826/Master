package com.semi.product.model.vo;

import java.sql.Date;

public class Product {
	private int proNo;
	private String categoryName;
	private int mstNo;
	private String proName;
	private int proPrice;
	private String proDescription;
	private Date proDate;
	private int proStock;
	private int proHits;
	private int deliveryFee;
	private String expPeriod;
	private String proStatus;
	
	//목록에 띄울 썸네일 주소 받아올 변수
	private String titleImg;
	
	//상품상세보기에 불러올 장인홈 이름 받을 변수
	private String strName; //MASTER테이블의 STR_NAME 컬럼
	

	public String getStrName() {
		return strName;
	}
	
	public Product(int proNo, String proName, int proPrice) {
		super();
		this.proNo = proNo;
		this.proName = proName;
		this.proPrice = proPrice;
	}
	
	public Product(int proNo, String categoryName, String proName, int proPrice, int proStock, String expPeriod) {
		super();
		this.proNo = proNo;
		this.categoryName = categoryName;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proStock = proStock;
		this.expPeriod = expPeriod;
	}
	
	public Product(int proNo, String categoryName, int mstNo, String proName, int proPrice, Date proDate, int proStock,
			int proHits, String titleImg, String strName) {
		super();
		this.proNo = proNo;
		this.categoryName = categoryName;
		this.mstNo = mstNo;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proDate = proDate;
		this.proStock = proStock;
		this.proHits = proHits;
		this.titleImg = titleImg;
		this.strName = strName;
	}

	public Product(int proNo, String categoryName, int mstNo, String proName, int proPrice, String proDescription,
			Date proDate, int proStock, int proHits, int deliveryFee, String expPeriod, String strName) {
		super();
		this.proNo = proNo;
		this.categoryName = categoryName;
		this.mstNo = mstNo;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proDescription = proDescription;
		this.proDate = proDate;
		this.proStock = proStock;
		this.proHits = proHits;
		this.deliveryFee = deliveryFee;
		this.expPeriod = expPeriod;
		this.strName = strName;
	}

	public Product(int proNo, String categoryName, int mstNo, String proName, int proPrice, String proDescription,
			Date proDate, int proStock, int proHits, int deliveryFee, String expPeriod, String proStatus,
			String titleImg, String strName) {
		super();
		this.proNo = proNo;
		this.categoryName = categoryName;
		this.mstNo = mstNo;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proDescription = proDescription;
		this.proDate = proDate;
		this.proStock = proStock;
		this.proHits = proHits;
		this.deliveryFee = deliveryFee;
		this.expPeriod = expPeriod;
		this.proStatus = proStatus;
		this.titleImg = titleImg;
		this.strName = strName;
	}

	public Product() {
		super();
	}

	public Product(int proNo, String categoryName, int mstNo, String proName, int proPrice, String proDescription,
			Date proDate, int proStock, int proHits, int deliveryFee, String expPeriod, String titleImg,
			String strName) {
		super();
		this.proNo = proNo;
		this.categoryName = categoryName;
		this.mstNo = mstNo;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proDescription = proDescription;
		this.proDate = proDate;
		this.proStock = proStock;
		this.proHits = proHits;
		this.deliveryFee = deliveryFee;
		this.expPeriod = expPeriod;
		this.titleImg = titleImg;
		this.strName = strName;
	}

	
	public Product(int proNo, String categoryName, int mstNo, String proName, int proPrice, Date proDate, int proStock,
			int proHits, String titleImg) {
		super();
		this.proNo = proNo;
		this.categoryName = categoryName;
		this.mstNo = mstNo;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proDate = proDate;
		this.proStock = proStock;
		this.proHits = proHits;
		this.titleImg = titleImg;
	}
	
	public Product(int proNo, String categoryName, int mstNo, String proName, int proPrice, String proDescription,
			int deliveryFee, String expPeriod) {
		super();
		this.proNo = proNo;
		this.categoryName = categoryName;
		this.mstNo = mstNo;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proDescription = proDescription;
		this.deliveryFee = deliveryFee;
		this.expPeriod = expPeriod;
	}

	public Product(int proNo, String categoryName, int mstNo, String proName, int proPrice, String proDescription,
			Date proDate, int proStock, String proStatus, int deliveryFee, String expPeriod) {
		super();
		this.proNo = proNo;
		this.categoryName = categoryName;
		this.mstNo = mstNo;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proDescription = proDescription;
		this.proDate = proDate;
		this.proStock = proStock;
		this.proStatus = proStatus;
		this.deliveryFee = deliveryFee;
		this.expPeriod = expPeriod;
	}

	public Product(int proNo, String categoryName, int mstNo, String proName, int proPrice, String proDescription,
			Date proDate, int proStock, int proHits, String proStatus, int deliveryFee, String expPeriod) {
		super();
		this.proNo = proNo;
		this.categoryName = categoryName;
		this.mstNo = mstNo;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proDescription = proDescription;
		this.proDate = proDate;
		this.proStock = proStock;
		this.proHits = proHits;
		this.proStatus = proStatus;
		this.deliveryFee = deliveryFee;
		this.expPeriod = expPeriod;
	}

	public Product(int proNo, String categoryName, int mstNo, String proName, int proPrice, String proDescription,
			Date proDate, int proStock, int proHits, String proStatus, int deliveryFee, String expPeriod,
			String titleImg) {
		super();
		this.proNo = proNo;
		this.categoryName = categoryName;
		this.mstNo = mstNo;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proDescription = proDescription;
		this.proDate = proDate;
		this.proStock = proStock;
		this.proHits = proHits;
		this.proStatus = proStatus;
		this.deliveryFee = deliveryFee;
		this.expPeriod = expPeriod;
		this.titleImg = titleImg;
	}
	
	

	public int getProNo() {
		return proNo;
	}

	public void setProNo(int proNo) {
		this.proNo = proNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getMstNo() {
		return mstNo;
	}

	public void setMstNo(int mstNo) {
		this.mstNo = mstNo;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getProPrice() {
		return proPrice;
	}

	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}

	public String getProDescription() {
		return proDescription;
	}

	public void setProDescription(String proDescription) {
		this.proDescription = proDescription;
	}

	public Date getProDate() {
		return proDate;
	}

	public void setProDate(Date proDate) {
		this.proDate = proDate;
	}

	public int getProStock() {
		return proStock;
	}

	public void setProStock(int proStock) {
		this.proStock = proStock;
	}

	public int getProHits() {
		return proHits;
	}

	public void setProHits(int proHits) {
		this.proHits = proHits;
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

	public String getProStatus() {
		return proStatus;
	}

	public void setProStatus(String proStatus) {
		this.proStatus = proStatus;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	
	public void setStrName(String strName) {
		this.strName = strName;
	}

	@Override
	public String toString() {
		return "Product [proNo=" + proNo + ", categoryName=" + categoryName + ", mstNo=" + mstNo + ", proName="
				+ proName + ", proPrice=" + proPrice + ", proDescription=" + proDescription + ", proDate=" + proDate
				+ ", proStock=" + proStock + ", proHits=" + proHits + ", deliveryFee=" + deliveryFee + ", expPeriod="
				+ expPeriod + ", proStatus=" + proStatus + ", titleImg=" + titleImg + "]";
	}
	
	
	

}
