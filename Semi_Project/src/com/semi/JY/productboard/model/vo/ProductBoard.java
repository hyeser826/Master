package com.semi.JY.productboard.model.vo;

import java.sql.Date;

public class ProductBoard {
private int productBoardNo;//	문의번호
private int productNo;//	상품번호	
private int memNo;//회원번호	
private String productBoardContent;//문의내용	
private Date productBoardDate;//작성날짜	
private String Status;//게시글상태

//private String titleImg;

public ProductBoard() {
	super();
}

public ProductBoard(int productBoardNo, int productNo, int memNo, String productBoardContent, Date productBoardDate,
		String status) {
	super();
	this.productBoardNo = productBoardNo;
	this.productNo = productNo;
	this.memNo = memNo;
	this.productBoardContent = productBoardContent;
	this.productBoardDate = productBoardDate;
	this.Status = status;
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
	return Status;
}

public void setStatus(String status) {
	Status = status;
}

@Override
public String toString() {
	return "ProductBoard [productBoardNo=" + productBoardNo + ", productNo=" + productNo + ", memNo=" + memNo
			+ ", productBoardContent=" + productBoardContent + ", productBoardDate=" + productBoardDate + ", Status="
			+ Status + "]";
}











}
