package com.tms.model;

import java.util.Date;

public class Film_info implements Comparable<Film_info> {

	private String film_name;
	private Date shelt_time;
	private Date unshelt_time;
	private String film_info;
	private String starring;
	private String film_type;
	private int duration;
	private double film_score;
	private String film_director;

	public String getFilm_name() {
		return film_name;
	}

	public void setFilm_name(String film_name) {
		this.film_name = film_name;
	}

	public Date getShelt_time() {
		return shelt_time;
	}

	public void setShelt_time(Date shelt_time) {
		this.shelt_time = shelt_time;
	}

	public Date getUnshelt_time() {
		return unshelt_time;
	}

	public void setUnshelt_time(Date unshelt_time) {
		this.unshelt_time = unshelt_time;
	}

	public String getFilm_info() {
		return film_info;
	}

	public void setFilm_info(String film_info) {
		this.film_info = film_info;
	}

	public String getStarring() {
		return starring;
	}

	public void setStarring(String starring) {
		this.starring = starring;
	}

	public String getFilm_type() {
		return film_type;
	}

	public void setFilm_type(String film_type) {
		this.film_type = film_type;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getFilm_score() {
		return film_score;
	}

	public void setFilm_score(double film_score) {
		this.film_score = film_score;
	}

	public String getFilm_director() {
		return film_director;
	}

	public void setFilm_director(String film_director) {
		this.film_director = film_director;
	}

	// java中的sort函数要求实现的comparable接口，该函数默认将元素按照从小到大排序，当想要从大到小时只需将返回1与-1的位置调换即可
	// comparable函数中两元素相等返回0，a.compareTo（b）大于应返回1，小于应返回-1
	@Override
	public int compareTo(Film_info o) {
		double temp = this.getFilm_score() - o.getFilm_score();
		return temp >= 0 ? -1 : 1;
	}
}
