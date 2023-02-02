package com.semi.notice.model.vo;

import java.sql.Date;

public class Notice {

	private int noticeNo;//	NOTICE_NO
	private int memNo;//	MEM_NO
	private String noticeTitle;//	NOTICE_TITLE
	private Date noticeDate;//	NOTICE_DATE
	private int noticeHits;//	NOTICE_HITS
	private String noticeContent;//	NOTICE_CONTENT
	private String noticeCategory;//	NOTICE_CATEGORY
	private String status;
	
	public Notice() {
		super();
	}

	
	public Notice(int noticeNo, String noticeTitle, String noticeContent, int memNo, int noticeHits, Date noticeDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.memNo = memNo;
		this.noticeHits = noticeHits;
		this.noticeDate = noticeDate;
	}



	public Notice(int noticeNo, String noticeTitle, String noticeContent, int noticeHits, Date noticeDate,
			String status) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeHits = noticeHits;
		this.noticeDate = noticeDate;
		this.status = status;
	}



	public Notice(int noticeNo, int memNo, String noticeTitle, Date noticeDate, int noticeHits, String noticeContent,
			String noticeCategory, String status) {
		super();
		this.noticeNo = noticeNo;
		this.memNo = memNo;
		this.noticeTitle = noticeTitle;
		this.noticeDate = noticeDate;
		this.noticeHits = noticeHits;
		this.noticeContent = noticeContent;
		this.noticeCategory = noticeCategory;
		this.status = status;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public int getNoticeHits() {
		return noticeHits;
	}

	public void setNoticeHits(int noticeHits) {
		this.noticeHits = noticeHits;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeCategory() {
		return noticeCategory;
	}

	public void setNoticeCategory(String noticeCategory) {
		this.noticeCategory = noticeCategory;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", memNo=" + memNo + ", noticeTitle=" + noticeTitle + ", noticeDate="
				+ noticeDate + ", noticeHits=" + noticeHits + ", noticeContent=" + noticeContent + ", noticeCategory="
				+ noticeCategory + ", status=" + status + "]";
	}
	
	

}
