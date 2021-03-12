package com.tms.model;

import java.util.Date;

public class Film_arrange implements Comparable<Film_arrange> {

	private Date film_playtime;
	private int film_playroom;
	private String film_name;
	private double film_price;

	public double getFilm_price() {
		return film_price;
	}

	public void setFilm_price(double film_price) {
		this.film_price = film_price;
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

	public String getFilm_name() {
		return film_name;
	}

	public void setFilm_name(String film_name) {
		this.film_name = film_name;
	}

	// java中的sort函数要求实现的comparable接口，该函数默认将元素按照从小到大排序，当想要从大到小时只需将返回1与-1的位置调换即可
	// comparable函数中两元素相等返回0，a.compareTo（b）大于应返回1，小于应返回-1
	@Override
	public int compareTo(Film_arrange o) {
		long temp = this.getFilm_playtime().getTime() - o.getFilm_playtime().getTime();
		return temp >= 0 ? 1 : -1;
	}
}
