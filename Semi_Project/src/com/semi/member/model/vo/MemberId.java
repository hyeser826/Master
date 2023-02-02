package com.semi.member.model.vo;

public class MemberId {
	private String memId;

	public MemberId() {
		super();
	}
	
	public MemberId(String memId) {
		super();
		this.memId = memId;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	@Override
	public String toString() {
		return "MemberId [memId=" + memId + "]";
	}

	
	
}
