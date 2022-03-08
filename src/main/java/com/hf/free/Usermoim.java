package com.hf.free;

public class Usermoim {
	private String moimName;
	private String userId;
	private String position;
	
	public Usermoim() {
		// TODO Auto-generated constructor stub
	}

	public Usermoim(String moimName, String userId, String position) {
		super();
		this.moimName = moimName;
		this.userId = userId;
		this.position = position;
	}

	public String getMoimName() {
		return moimName;
	}

	public void setMoimName(String moimName) {
		this.moimName = moimName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
	
}
