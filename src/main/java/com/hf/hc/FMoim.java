package com.hf.hc;

import java.sql.Date;

public class FMoim {
	private String f_name;
	private String f_interest;
	private String f_region;
	private int f_maxMembers;
	private String f_introduce;
	private String f_image;
	private int f_currentMembers;
	private Date f_createDate;
	private String f_fee;
	
	public FMoim() {
		// TODO Auto-generated constructor stub
	}

	public FMoim(String f_name, String f_interest, String f_region, int f_maxMembers, String f_introduce,
			String f_image, int f_currentMembers, Date f_createDate, String f_fee) {
		super();
		this.f_name = f_name;
		this.f_interest = f_interest;
		this.f_region = f_region;
		this.f_maxMembers = f_maxMembers;
		this.f_introduce = f_introduce;
		this.f_image = f_image;
		this.f_currentMembers = f_currentMembers;
		this.f_createDate = f_createDate;
		this.f_fee = f_fee;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getF_interest() {
		return f_interest;
	}

	public void setF_interest(String f_interest) {
		this.f_interest = f_interest;
	}

	public String getF_region() {
		return f_region;
	}

	public void setF_region(String f_region) {
		this.f_region = f_region;
	}

	public int getF_maxMembers() {
		return f_maxMembers;
	}

	public void setF_maxMembers(int f_maxMembers) {
		this.f_maxMembers = f_maxMembers;
	}

	public String getF_introduce() {
		return f_introduce;
	}

	public void setF_introduce(String f_introduce) {
		this.f_introduce = f_introduce;
	}

	public String getF_image() {
		return f_image;
	}

	public void setF_image(String f_image) {
		this.f_image = f_image;
	}

	public int getF_currentMembers() {
		return f_currentMembers;
	}

	public void setF_currentMembers(int f_currentMembers) {
		this.f_currentMembers = f_currentMembers;
	}

	public Date getF_createDate() {
		return f_createDate;
	}

	public void setF_createDate(Date f_createDate) {
		this.f_createDate = f_createDate;
	}

	public String getF_fee() {
		return f_fee;
	}

	public void setF_fee(String f_fee) {
		this.f_fee = f_fee;
	}
	
}
