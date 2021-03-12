package com.tms.model;

public class Purchase_record {

	private int id;
	private String user_number;
	private String film_name;
	private String film_scence;
	private String purchase_time;
	private String seat_number;
	private String ticket_code;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_number() {
		return user_number;
	}

	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}

	public String getFilm_name() {
		return film_name;
	}

	public void setFilm_name(String film_name) {
		this.film_name = film_name;
	}

	public String getFilm_scence() {
		return film_scence;
	}

	public void setFilm_scence(String film_scence) {
		this.film_scence = film_scence;
	}

	public String getPurchase_time() {
		return purchase_time;
	}

	public void setPurchase_time(String purchase_time) {
		this.purchase_time = purchase_time;
	}

	public String getSeat_number() {
		return seat_number;
	}

	public void setSeat_number(String seat_number) {
		this.seat_number = seat_number;
	}

	public String getTicket_code() {
		return ticket_code;
	}

	public void setTicket_code(String ticket_code) {
		this.ticket_code = ticket_code;
	}
}
