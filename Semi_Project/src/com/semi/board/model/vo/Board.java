package com.semi.board.model.vo;

import java.sql.Date;

public class Board {
	
	private int boardNo;
	private int memNo;
	private String memId;
	private String boardTitle;
	private String boardContent;
	private String boardAnswer;
	private Date boardDate;
	private Date answerDate;
	private String status;
	private String titleImg;
	private String boardCategory;
	private String coNumber;
	private String coName;
	private String ceo;
	private String coKind;
	private String strName;
	private String masterIntro;
	private int productNo;
	
	
	public Board() {
		super();
	}

	public Board(int boardNo, String memId, String boardTitle, String boardContent, String boardAnswer, Date boardDate,
				Date answerDate, String boardCategory, int productNo) {
			super();
			this.boardNo = boardNo;
			this.memId = memId;
			this.boardTitle = boardTitle;
			this.boardContent = boardContent;
			this.boardAnswer = boardAnswer;
			this.boardDate = boardDate;
			this.answerDate = answerDate;
			this.boardCategory = boardCategory;
			this.productNo = productNo;
		}

	
	public Board(int boardNo, String boardCategory, String boardTitle, String memId, Date boardDate, String status) {
		super();
		this.boardNo = boardNo;
		this.boardCategory = boardCategory;
		this.boardTitle = boardTitle;
		this.memId = memId;
		this.boardDate = boardDate;
		this.status = status;
	}

	public Board(int boardNo, String boardCategory, String boardTitle, String boardContent, String memId, Date boardDate) {
		super();
		this.boardNo = boardNo;
		this.boardCategory = boardCategory;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memId = memId;
		this.boardDate = boardDate;
	}

	public Board(int boardNo, String memId, String boardTitle, String boardContent, String boardAnswer, Date boardDate, Date answerDate, String boardCategory, int productNo, String status) {
		super();
		this.boardNo = boardNo;
		this.memId = memId;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardAnswer = boardAnswer;
		this.boardDate = boardDate;
		this.answerDate = answerDate;
		this.boardCategory = boardCategory;
		this.productNo = productNo;
		this.status = status;
	}
	
	public Board(int boardNo, String memId, String boardTitle, String boardContent, String boardAnswer, Date boardDate, Date answerDate, String boardCategory, String status) {
		super();
		this.boardNo = boardNo;
		this.memId = memId;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardAnswer = boardAnswer;
		this.boardDate = boardDate;
		this.answerDate = answerDate;
		this.boardCategory = boardCategory;
		this.status = status;
	}
	
	public Board(int boardNo, String memId, String boardTitle, String boardContent, Date boardDate, String status,
			String titleImg, String boardCategory) {
		super();
		this.boardNo = boardNo;
		this.memId = memId;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.status = status;
		this.titleImg = titleImg;
		this.boardCategory = boardCategory;
	}


	public Board(int boardNo, String boardCategory, String boardTitle, String boardContent, String boardAnswer, String memId, 
				 String strName, Date boardDate,	Date answerDate, String status, int productNo) {
		super();
		this.boardNo = boardNo;
		this.boardCategory = boardCategory;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardAnswer = boardAnswer;
		this.memId = memId;
		this.strName = strName;
		this.boardDate = boardDate;
		this.answerDate = answerDate;
		this.status = status;
		this.productNo = productNo;
	}
	
	public Board(int boardNo, String boardCategory, String boardTitle, String boardContent, String boardAnswer, String memId, 
			String coName, String coNumber, String ceo, String coKind, String strName, String masterIntro, Date boardDate,	Date answerDate, String status) {
		super();
		this.boardNo = boardNo;
		this.boardCategory = boardCategory;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardAnswer = boardAnswer;
		this.memId = memId;
		this.coName = coName;
		this.coNumber = coNumber;
		this.ceo = ceo;
		this.coKind = coKind;
		this.strName = strName;
		this.masterIntro = masterIntro;
		this.boardDate = boardDate;
		this.answerDate = answerDate;
		this.status = status;
	}
	  
	public Board(int boardNo, int memNo, String boardTitle, String boardContent, String boardAnswer, Date boardDate,
			Date answerDate, String boardCategory, String coName, String coNumber, String ceo, String coKind,
			String strName, String masterIntro, String status) {
		super();
		this.boardNo = boardNo;
		this.setMemNo(memNo);
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardAnswer = boardAnswer;
		this.boardDate = boardDate;
		this.answerDate = answerDate;
		this.boardCategory = boardCategory;
		this.coName = coName;
		this.coNumber = coNumber;
		this.ceo = ceo;
		this.coKind = coKind;
		this.strName = strName;
		this.masterIntro = masterIntro;
		this.status = status;
	}

	public Board(int boardNo, String memId, String boardTitle, String boardContent, String boardAnswer, Date boardDate,
			Date answerDate, String status, String titleImg, String boardCategory, String coNum, String coName,
			String ceo, String coKind, String strName, String masterIntro) {
		super();
		this.boardNo = boardNo;
		this.memId = memId;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardAnswer = boardAnswer;
		this.boardDate = boardDate;
		this.answerDate = answerDate;
		this.status = status;
		this.titleImg = titleImg;
		this.boardCategory = boardCategory;
		this.coNumber = coNum;
		this.coName = coName;
		this.ceo = ceo;
		this.coKind = coKind;
		this.strName = strName;
		this.masterIntro = masterIntro;
	}

	
	
	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getMasterIntro() {
		return masterIntro;
	}

	public void setMasterIntro(String masterIntro) {
		this.masterIntro = masterIntro;
	}
	
	
	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getCoNumber() {
		return coNumber;
	}

	public void setCoNumber(String coNumber) {
		this.coNumber = coNumber;
	}

	public String getCoName() {
		return coName;
	}

	public void setCoName(String coName) {
		this.coName = coName;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public String getCoKind() {
		return coKind;
	}

	public void setCoKind(String coKind) {
		this.coKind = coKind;
	}

	public String getBoardAnswer() {
		return boardAnswer;
	}

	public void setBoardAnswer(String boardAnswer) {
		this.boardAnswer = boardAnswer;
	}

	public Date getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public String getBoardCategory() {
		return boardCategory;
	}

	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", memId=" + memId + ", boardTitle=" + boardTitle + ", boardContent="
				+ boardContent + ", boardAnswer=" + boardAnswer + ", boardDate=" + boardDate + ", answerDate="
				+ answerDate + ", status=" + status + ", titleImg=" + titleImg + ", boardCategory=" + boardCategory
				+ ", coNumber=" + coNumber + ", coName=" + coName + ", ceo=" + ceo + ", coKind=" + coKind + ", strName="
				+ strName + ", masterIntro=" + masterIntro + "]";
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}



	
	
	
}