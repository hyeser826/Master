package com.semi.manager.review.model.vo;

import java.sql.Date;

public class Review {
	
	private int productBoardNo;//문의번호(글번호)
	private int productNo;//상품번호
	private int memNo;//회원번호
	private String productBoardContent;//내용
	private Date productBoardDate;//작성날짜
	private String status;//게시글상태
	//추가
	private String productName;//상품이름
	
	
	public Review() {
		super();
	}
	public Review(int productBoardNo, int productNo, int memNo, String productBoardContent, Date productBoardDate,
			String status) {
		super();
		this.productBoardNo = productBoardNo;
		this.productNo = productNo;
		this.memNo = memNo;
		this.productBoardContent = productBoardContent;
		this.productBoardDate = productBoardDate;
		this.status = status;
	}



	public int getProductBoardNo() {
		return productBoardNo;
	}
	public void setProductBoardNo(int productBoardNo) {
		this.productBoardNo = productBoardNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getProductBoardContent() {
		return productBoardContent;
	}
	public void setProductBoardContent(String productBoardContent) {
		this.productBoardContent = productBoardContent;
	}
	public Date getProductBoardDate() {
		return productBoardDate;
	}
	public void setProductBoardDate(Date productBoardDate) {
		this.productBoardDate = productBoardDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//추가
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
	@Override
	public String toString() {
		return "Review [productBoardNo=" + productBoardNo + ", productNo=" + productNo + ", memNo=" + memNo
				+ ", productBoardContent=" + productBoardContent + ", productBoardDate=" + productBoardDate
				+ ", status=" + status + "]";
	}
	
	
}
