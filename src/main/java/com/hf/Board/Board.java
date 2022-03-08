package com.hf.Board;

import java.sql.Date;

public class Board {

	private int b_no;
	private String b_title;
	private String b_text;
	private Date b_date;
	private int b_cnt;
	
	
	public Board() {
		// TODO Auto-generated constructor stub
	}


	public Board(int b_no, String b_title, String b_text, Date b_date, int b_cnt) {
		super();
		this.b_no = b_no;
		this.b_title = b_title;
		this.b_text = b_text;
		this.b_date = b_date;
		this.b_cnt = b_cnt;
	}


	public int getB_no() {
		return b_no;
	}


	public void setB_no(int b_no) {
		this.b_no = b_no;
	}


	public String getB_title() {
		return b_title;
	}


	public void setB_title(String b_title) {
		this.b_title = b_title;
	}


	public String getB_text() {
		return b_text;
	}


	public void setB_text(String b_text) {
		this.b_text = b_text;
	}


	public Date getB_date() {
		return b_date;
	}


	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}
	
	public int getB_cnt() {
		return b_cnt;
	}


	public void setB_cnt(int b_cnt) {
		this.b_cnt = b_cnt;
	}
}
