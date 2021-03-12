package com.tms.ctrl;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.tms.model.Film_arrange;
import com.tms.model.Film_info;
import com.tms.model.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class Admin_Controller extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private ArrayList<Film_arrange> arrangelist;
	private User user;
	private String relativeSavePath = "/com.tms.view/src/film_cover";
	// 用于接收前端ajax传送过来的文件,需要提供该属性的get/set方法
	private File film_cover;

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	DBProcess db = new DBProcess(request);

	public Admin_Controller() {
		// 设置格式为text/json
		response.setContentType("text/json");
		// *设置字符集为'UTF-8'
		response.setCharacterEncoding("UTF-8");
	}

	// 管理员登录
	public void login() throws Exception {
		String password = request.getParameter("admin_password");
		String passwd = db.admin_login();
		if (passwd.equals(password)) {
			String jsonStr = JSON.toJSONString("1");
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();
		} else {
			String jsonStr = JSON.toJSONString("0");
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();
		}

	}

	// 返回排片信息
	public void all_arrange() throws SQLException, IOException {
		ArrayList<Film_arrange> list = new ArrayList<Film_arrange>();
		list = db.arrange_list();

		arrangelist = list;
		String jsonStr = JSON.toJSONString(arrangelist);
		PrintWriter printWtriter = response.getWriter();
		printWtriter.print(jsonStr);
		printWtriter.close();
	}

	// 添加排片信息
	public void add_arrange() throws ParseException, ClassNotFoundException, SQLException, IOException {
		db.add_arrange();
		String jsonStr = JSON.toJSONString("1");
		PrintWriter printWtriter = response.getWriter();
		printWtriter.print(jsonStr);
		printWtriter.close();
	}

	// 添加电影
	public void add_film() throws ParseException, ClassNotFoundException, SQLException, IOException {
		if (!db.film_exist()) {
			if (film_cover != null) {
				String film_name = request.getParameter("film_name");
				// 该路径为已打包发布的web项目的路径
				// G:\Projects_MyEclipse\.metadata\.me_tcat85\webapps\TMS\com.tms.view\src\film_cover\film_name.jpg
				String absoluteSavePath = request.getServletContext()
						.getRealPath(getRelativeSavePath() + "/" + film_name + ".jpg");
				File file = new File(absoluteSavePath);
				if (!file.exists()) {
					file.createNewFile();
				}
				FileInputStream input = new FileInputStream(film_cover);
				FileOutputStream output = new FileOutputStream(file);
				byte[] buf = new byte[4096];
				int length = 0;
				while ((length = input.read(buf)) != -1) {
					output.write(buf, 0, length);
				}
				input.close();
				output.flush();
				output.close();
			}
			db.add_film();
			String jsonStr = JSON.toJSONString("1");
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();
		} else {
			String jsonStr = JSON.toJSONString("0");
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();

		}
	}

	// 查询指定用户信息
	public void get_bynumber() throws ClassNotFoundException, SQLException, IOException {

		User user = db.getuser_bynumber();
		if (user != null) {

			String jsonStr = JSON.toJSONString(user);
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();

		} else {
			String jsonStr = JSON.toJSONString("0");
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();

		}

	}

	// 删除用户
	public void del_user() throws ClassNotFoundException, SQLException {
		String user_number = request.getParameter("user_number");
		db.del_User(user_number);

	}

	// 查询所有电影
	public void film_list() throws SQLException, IOException {
		ArrayList<Film_info> list = new ArrayList<Film_info>();
		list = db.film_list();
		String jsonStr;
		PrintWriter printWtriter = response.getWriter();
		if (list == null) {
			jsonStr = JSON.toJSONString("0");
			printWtriter.print(jsonStr);
			printWtriter.close();

		} else {
			jsonStr = JSON.toJSONString(list);
			printWtriter.print(jsonStr);
			printWtriter.close();

		}
	}

	// 查询所有用户
	public void user_list() throws SQLException, IOException {
		ArrayList<User> list = new ArrayList<User>();
		list = db.user_list();
		String jsonStr;
		PrintWriter printWtriter = response.getWriter();
		if (list == null) {
			jsonStr = JSON.toJSONString("0");
			printWtriter.print(jsonStr);
			printWtriter.close();
		} else {
			jsonStr = JSON.toJSONString(list);
			printWtriter.print(jsonStr);
			printWtriter.close();
		}
	}

	// getter、setter
	public ArrayList<Film_arrange> getArrangelist() {
		return arrangelist;
	}

	public void setArrangelist(ArrayList<Film_arrange> arrangelist) {
		this.arrangelist = arrangelist;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRelativeSavePath() {
		return relativeSavePath;
	}

	public void setRelativeSavePath(String relativeSavePath) {
		this.relativeSavePath = relativeSavePath;
	}

	public File getFilm_cover() {
		return film_cover;
	}

	public void setFilm_cover(File film_cover) {
		this.film_cover = film_cover;
	}

}
