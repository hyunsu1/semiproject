package com.hf.hc;

public class Account {
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_gender;
	private String m_birth;
	private String m_email;
	private String m_phone;
	private String m_area;
	private String m_qa1;
	private String m_qa2;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(String m_id, String m_pw, String m_name, String m_gender, String m_birth, String m_email,
			String m_phone, String m_area, String m_qa1, String m_qa2) {
		super();
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_name = m_name;
		this.m_gender = m_gender;
		this.m_birth = m_birth;
		this.m_email = m_email;
		this.m_phone = m_phone;
		this.m_area = m_area;
		this.m_qa1 = m_qa1;
		this.m_qa2 = m_qa2;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_pw() {
		return m_pw;
	}

	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_gender() {
		return m_gender;
	}

	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}

	public String getM_birth() {
		return m_birth;
	}

	public void setM_birth(String m_birth) {
		this.m_birth = m_birth;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_phone() {
		return m_phone;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	public String getM_area() {
		return m_area;
	}

	public void setM_area(String m_area) {
		this.m_area = m_area;
	}

	public String getM_qa1() {
		return m_qa1;
	}

	public void setM_qa1(String m_qa1) {
		this.m_qa1 = m_qa1;
	}

	public String getM_qa2() {
		return m_qa2;
	}

	public void setM_qa2(String m_qa2) {
		this.m_qa2 = m_qa2;
	}
	
}
