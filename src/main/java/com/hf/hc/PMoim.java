package com.hf.hc;

import java.sql.Date;

public class PMoim {
	private String p_name;
	private String p_interest;
	private String p_region;
	private int p_maxMembers;
	private String p_introduce;
	private String p_image;
	private int p_currentMembers;
	private Date p_createDate;
	private String p_fee;
	
	public PMoim() {
		// TODO Auto-generated constructor stub
	}

	public PMoim(String p_name, String p_interest, String p_region, int p_maxMembers, String p_introduce,
			String p_image, int p_currentMembers, Date p_createDate, String p_fee) {
		super();
		this.p_name = p_name;
		this.p_interest = p_interest;
		this.p_region = p_region;
		this.p_maxMembers = p_maxMembers;
		this.p_introduce = p_introduce;
		this.p_image = p_image;
		this.p_currentMembers = p_currentMembers;
		this.p_createDate = p_createDate;
		this.p_fee = p_fee;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_interest() {
		return p_interest;
	}

	public void setP_interest(String p_interest) {
		this.p_interest = p_interest;
	}

	public String getP_region() {
		return p_region;
	}

	public void setP_region(String p_region) {
		this.p_region = p_region;
	}

	public int getP_maxMembers() {
		return p_maxMembers;
	}

	public void setP_maxMembers(int p_maxMembers) {
		this.p_maxMembers = p_maxMembers;
	}

	public String getP_introduce() {
		return p_introduce;
	}

	public void setP_introduce(String p_introduce) {
		this.p_introduce = p_introduce;
	}

	public String getP_image() {
		return p_image;
	}

	public void setP_image(String p_image) {
		this.p_image = p_image;
	}

	public int getP_currentMembers() {
		return p_currentMembers;
	}

	public void setP_currentMembers(int p_currentMembers) {
		this.p_currentMembers = p_currentMembers;
	}

	public Date getP_createDate() {
		return p_createDate;
	}

	public void setP_createDate(Date p_createDate) {
		this.p_createDate = p_createDate;
	}

	public String getP_fee() {
		return p_fee;
	}

	public void setP_fee(String p_fee) {
		this.p_fee = p_fee;
	}

}
