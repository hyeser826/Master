package com.semi.JY.review.model.vo;

import java.sql.Date;

public class Review {
private Date pqDate; //작성날짜;
private String pName; // 상품명
private String pqContent; //리뷰내용
public Review() {
	super();
}
public Review(Date pqDate, String pName, String pqContent) {
	super();
	this.pqDate = pqDate;
	this.pName = pName;
	this.pqContent = pqContent;
}
public Date getPqDate() {
	return pqDate;
}
public void setPqDate(Date pqDate) {
	this.pqDate = pqDate;
}
public String getpName() {
	return pName;
}
public void setpName(String pName) {
	this.pName = pName;
}
public String getPqContent() {
	return pqContent;
}
public void setPqContent(String pqContent) {
	this.pqContent = pqContent;
}



}
