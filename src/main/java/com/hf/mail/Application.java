package com.hf.mail;

public class Application {
	private int num;
	private String moimName;
	private String sendId;
	private String receiveId;
	private String introduce;
	
	public Application() {
		// TODO Auto-generated constructor stub
	}

	

	public Application(int num, String moimName, String sendId, String receiveId, String introduce) {
		super();
		this.num = num;
		this.moimName = moimName;
		this.sendId = sendId;
		this.receiveId = receiveId;
		this.introduce = introduce;
	}

	public String getMoimName() {
		return moimName;
	}

	public void setMoimName(String moimName) {
		this.moimName = moimName;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	
}
