package com.semi.member.model.vo;

public class MemberPw {
	private String memPw;

	public MemberPw(String memPw) {
		super();
		this.memPw = memPw;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	@Override
	public String toString() {
		return "MemberPw [memPw=" + memPw + "]";
	}
	
}
