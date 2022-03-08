package com.hf.free;

import java.util.Date;

public class Moim {
	private String name;
	private String interest;
	private String region;
	private int maxMembers;
	private String introduce;
	private String image;
	private int currentMembers;
	private Date createDate; 
	private String fee;
	
	public Moim() {
		// TODO Auto-generated constructor stub
	}

	
	public Moim(String name, String interest, String region, int maxMembers, String introduce, String image,
			int currentMembers, Date createDate, String fee) {
		super();
		this.name = name;
		this.interest = interest;
		this.region = region;
		this.maxMembers = maxMembers;
		this.introduce = introduce;
		this.image = image;
		this.currentMembers = currentMembers;
		this.createDate = createDate;
		this.fee = fee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getMaxMembers() {
		return maxMembers;
	}

	public void setMaxMembers(int maxMembers) {
		this.maxMembers = maxMembers;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getCurrentMembers() {
		return currentMembers;
	}

	public void setCurrentMembers(int currentMembers) {
		this.currentMembers = currentMembers;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getFee() {
		return fee;
	}


	public void setFee(String fee) {
		this.fee = fee;
	}

	
	
	
}
