package com.tms.model;

import java.sql.Date;

public class Cinema_seat {

	private String film_name;
	private Date film_playtime;
	private int film_playroom;
	private String seat;

	public String getFilm_name() {
		return film_name;
	}

	public void setFilm_name(String film_name) {
		this.film_name = film_name;
	}

	public Date getFilm_playtime() {
		return film_playtime;
	}

	public void setFilm_playtime(Date film_playtime) {
		this.film_playtime = film_playtime;
	}

	public int getFilm_playroom() {
		return film_playroom;
	}

	public void setFilm_playroom(int film_playroom) {
		this.film_playroom = film_playroom;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

}
