package com.hf.mail;

public class Mail {
	int num;
	String sendId;
	String receiveId;
	String title;
	String content;
	
	public Mail() {
		// TODO Auto-generated constructor stub
	}
	
	public Mail(int num, String sendId, String receiveId, String title, String content) {
		super();
		this.num = num;
		this.sendId = sendId;
		this.receiveId = receiveId;
		this.title = title;
		this.content = content;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
