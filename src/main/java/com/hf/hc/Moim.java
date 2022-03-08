package com.hf.hc;

public class Moim {
	private String u_moimName;
	private String u_userId;
	private String u_position;
	
	public Moim() {
		// TODO Auto-generated constructor stub
	}

	public Moim(String u_moimName, String u_userId, String u_position) {
		super();
		this.u_moimName = u_moimName;
		this.u_userId = u_userId;
		this.u_position = u_position;
	}

	public String getU_moimName() {
		return u_moimName;
	}

	public void setU_moimName(String u_moimName) {
		this.u_moimName = u_moimName;
	}

	public String getU_userId() {
		return u_userId;
	}

	public void setU_userId(String u_userId) {
		this.u_userId = u_userId;
	}

	public String getU_position() {
		return u_position;
	}

	public void setU_position(String u_position) {
		this.u_position = u_position;
	}
	
}
