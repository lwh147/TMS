package com.tms.model;

import java.sql.Date;

public class User {

	private String user_name;
	private String user_password;
	private String phone_number;
	private String user_number;
	private String user_sex;
	private Date user_birthday;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getUser_number() {
		return user_number;
	}

	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}

	public String getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}

	public Date getUser_birthday() {
		return user_birthday;
	}

	public void setUser_birthday(Date user_birthday) {
		this.user_birthday = user_birthday;
	}

}
