package com.semi.product.model.vo;

public class Master {

	private int masterNo;		//MASTER_NO
	private int memNo;			//MEM_NO
	private String coName;		//CO_NAME
	private String coNumber;	//CO_NUMBER
	private String ceo;			//CEO
	private String coKind;		//CO_KIND
	private String strName;		//STR_NAME
	private String masterIntro;	//MASTER_INTRO
	private String status;		//STATUS
	
	public Master() {
		super();
	}

	public Master(int masterNo, int memNo, String coName, String coNumber, String ceo, String coKind, String strName,
			String masterIntro, String status) {
		super();
		this.masterNo = masterNo;
		this.memNo = memNo;
		this.coName = coName;
		this.coNumber = coNumber;
		this.ceo = ceo;
		this.coKind = coKind;
		this.strName = strName;
		this.masterIntro = masterIntro;
		this.status = status;
	}

	public int getMasterNo() {
		return masterNo;
	}

	public void setMasterNo(int masterNo) {
		this.masterNo = masterNo;
	}

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getCoName() {
		return coName;
	}

	public void setCoName(String coName) {
		this.coName = coName;
	}

	public String getCoNumber() {
		return coNumber;
	}

	public void setCoNumber(String coNumber) {
		this.coNumber = coNumber;
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

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public String getMasterIntro() {
		return masterIntro;
	}

	public void setMasterIntro(String masterIntro) {
		this.masterIntro = masterIntro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Master [masterNo=" + masterNo + ", memNo=" + memNo + ", coName=" + coName + ", coNumber=" + coNumber
				+ ", ceo=" + ceo + ", coKind=" + coKind + ", strName=" + strName + ", masterIntro=" + masterIntro
				+ ", status=" + status + "]";
	}
	
	
}
