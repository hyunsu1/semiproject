package com.hf.Photo;

import java.sql.Date;

public class PhotoBoardTO {
	private int p_no;
	private String p_title;
	private String p_text;
	private String p_thumb;
	private String p_file;
	private Date p_date;
	
	public PhotoBoardTO() {
		// TODO Auto-generated constructor stub
	}

	public PhotoBoardTO(int p_no, String p_title, String p_text, String p_thumb, String p_file, Date p_date) {
		super();
		this.p_no = p_no;
		this.p_title = p_title;
		this.p_text = p_text;
		this.p_thumb = p_thumb;
		this.p_file = p_file;
		this.p_date = p_date;
	}

	public int getP_no() {
		return p_no;
	}

	public void setP_no(int p_no) {
		this.p_no = p_no;
	}

	public String getP_title() {
		return p_title;
	}

	public void setP_title(String p_title) {
		this.p_title = p_title;
	}

	public String getP_text() {
		return p_text;
	}

	public void setP_text(String p_text) {
		this.p_text = p_text;
	}

	public String getP_thumb() {
		return p_thumb;
	}

	public void setP_thumb(String p_thumb) {
		this.p_thumb = p_thumb;
	}
	
	public String getP_file() {
		return p_file;
	}

	public void setP_file(String p_file) {
		this.p_file = p_file;
	}

	public Date getP_date() {
		return p_date;
	}

	public void setP_date(Date p_date) {
		this.p_date = p_date;
	}

	
	
	
}
