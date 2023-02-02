package com.semi.product.model.vo;

public class Attachment {
	private int attachmentNo;	//	ATTACHMENT_NO	NUMBER
	private int proNo;			//	PRODUCT_NO	NUMBER
	private int boardNo;		//	BOARD_NO	NUMBER
	private String fileName;	//	FILE_NAME	VARCHAR2(1000 BYTE)
	private String sysName;		//	SYS_NAME	VARCHAR2(1000 BYTE)
	private int fileLevel;		//	FILE_LEVEL	NUMBER
	private String filePath;	//	FILE_PATH	VARCHAR2(1000 BYTE)
	
	public Attachment() {
		super();
	}

	public Attachment(int attachmentNo, String fileName, String sysName, String filePath) {
		super();
		this.attachmentNo = attachmentNo;
		this.fileName = fileName;
		this.sysName = sysName;
		this.filePath = filePath;
	}

	public Attachment(int attachmentNo, int boardNo, String fileName, String sysName, int fileLevel, String filePath) {
		super();
		this.attachmentNo = attachmentNo;
		this.boardNo = boardNo;
		this.fileName = fileName;
		this.sysName = sysName;
		this.fileLevel = fileLevel;
		this.filePath = filePath;
	}
	
	public Attachment(int attachmentNo, int proNo, String fileName, String sysName, String filePath) {
		super();
		this.attachmentNo = attachmentNo;
		this.setProNo(proNo);
		this.fileName = fileName;
		this.sysName = sysName;
		this.filePath = filePath;
	}

	public int getAttachmentNo() {
		return attachmentNo;
	}

	public void setAttachmentNo(int attachmentNo) {
		this.attachmentNo = attachmentNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public int getFileLevel() {
		return fileLevel;
	}

	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getProNo() {
		return proNo;
	}

	public void setProNo(int proNo) {
		this.proNo = proNo;
	}

	@Override
	public String toString() {
		return "Attachment [attachmentNo=" + attachmentNo + ", boardNo=" + boardNo + ", fileName=" + fileName
				+ ", sysName=" + sysName + ", fileLevel=" + fileLevel + ", filePath=" + filePath + "]";
	}

}
