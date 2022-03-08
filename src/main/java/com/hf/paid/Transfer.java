package com.hf.paid;

import java.util.Date;

public class Transfer {
	private String sendId;
	private String receiveId;
	private String fee;
	private Date payDate;
	
	public Transfer() {
		// TODO Auto-generated constructor stub
	}

	public Transfer(String sendId, String receiveId, String fee, Date payDate) {
		super();
		this.sendId = sendId;
		this.receiveId = receiveId;
		this.fee = fee;
		this.payDate = payDate;
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

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	
}


