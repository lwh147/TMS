package com.tms.ctrl;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.tms.model.Film_arrange;
import com.tms.model.Film_info;
import com.tms.model.Purchase_record;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class User_Controller extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	DBProcess db = new DBProcess(request);

	public String user_password;
	public String user_numberR;
	public Film_info film_info;
	private String relativeSavePath = "/com.tms.view/src/user_avatar";
	// 用于接收前端ajax传送过来的文件,需要提供该属性的get/set方法
	private File user_avatar;
	public ArrayList<Purchase_record> prcd;
	public ArrayList<String> seats;
	public ArrayList<Film_arrange> film_arranges;
	public ArrayList<Film_info> hotfilms;
	public ArrayList<Film_info> readyfilms;

	public User_Controller() {
		// 设置格式为text/json
		response.setContentType("text/json");
		// *设置字符集为'UTF-8'
		response.setCharacterEncoding("UTF-8");
	}

	// 进入首页显示热映电影
	public void show_hotfilms() throws SQLException, IOException {
		hotfilms = db.getFilmListHot();
		String jsonStr = JSON.toJSONString(hotfilms);
		PrintWriter printWtriter = response.getWriter();
		printWtriter.print(jsonStr);
		printWtriter.close();
	}

	// 进入首页显示即将上映电影
	public void show_readyfilms() throws SQLException, IOException {
		readyfilms = db.getFilmListReady();
		String jsonStr = JSON.toJSONString(readyfilms);
		PrintWriter printWtriter = response.getWriter();
		printWtriter.print(jsonStr);
		printWtriter.close();
	}

	// 注册
	public void register() throws SQLException, IOException {
		if (db.getRUser_number() == null) {
			if (db.getPhone_number() == null) {
				db.Register_user();
				String jsonStr = JSON.toJSONString("1");
				PrintWriter printWtriter = response.getWriter();
				printWtriter.print(jsonStr);
				printWtriter.close();
			} else {// 已用该电话号码注册账号
				String jsonStr = JSON.toJSONString("2");
				PrintWriter printWtriter = response.getWriter();
				printWtriter.print(jsonStr);
				printWtriter.close();
			}
		} else {
			String jsonStr = JSON.toJSONString("0");
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();
		}
	}

	// 登录
	public void Login() throws SQLException, IOException {

		if (db.getRUser_number() == null) {
			String jsonStr = JSON.toJSONString("0");
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();
		} else {// 账号不存在
			String Passwd = db.Login();
			if (user_password.equals(Passwd)) {// 登录成功
				session.setAttribute("user_number", db.getRUser_number());
				String jsonStr = JSON.toJSONString("1");
				PrintWriter printWtriter = response.getWriter();
				printWtriter.print(jsonStr);
				printWtriter.close();
			} else {// 密码错误
				String jsonStr = JSON.toJSONString("2");
				PrintWriter printWtriter = response.getWriter();
				printWtriter.print(jsonStr);
				printWtriter.close();
			}
		}
	}

	// 检查登录状态
	public void isLogin() throws IOException {
		String number;
		if (session == null) {
			String jsonStr = JSON.toJSONString("0");
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();
		} else if (session.getAttribute("user_number") != null) {
			number = (String) session.getAttribute("user_number");
			String jsonStr = JSON.toJSONString(number);
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

	// 退出登录
	public void LogOut() throws IOException {
		session.setAttribute("user_number", null);
		String jsonStr = JSON.toJSONString("1");
		PrintWriter printWtriter = response.getWriter();
		printWtriter.print(jsonStr);
		printWtriter.close();
	}

	// 返回票价
	public void SearchFilm_Price() throws SQLException, IOException {
		double price = db.SearchFilm_Price();
		if (price != 0) {
			String jsonStr = JSON.toJSONString(price);
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();

		}

	}

	// 用户查看订单
	public void MyOrder() throws SQLException, IOException {
		prcd = db.getOrderBynumber();
		String jsonStr = JSON.toJSONString(prcd);
		PrintWriter printWtriter = response.getWriter();
		printWtriter.print(jsonStr);
		printWtriter.close();

	}

	// 管理员搜索电影
	public void SearchFilm() throws SQLException, IOException {
		film_info = db.SearchFilm();
		if (film_info == null) {

			String jsonStr = JSON.toJSONString("0");
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();
			// 搜索失败，继续搜索
		} else {

			String jsonStr = JSON.toJSONString(film_info);
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();
			// 搜索成功，显示电影信息
		}

	}

	// 用户关键字搜索
	public void SearchFilmByKeyWords() throws SQLException, IOException {
		ArrayList<Film_info> sfilms = new ArrayList<Film_info>();
		sfilms = db.SearchByKeyWords();
		String jsonStr = JSON.toJSONString(sfilms);
		PrintWriter printWtriter = response.getWriter();
		printWtriter.print(jsonStr);
		printWtriter.close();
	}

	// 选择电影场次
	public void ShowFilm_arrange() throws SQLException, IOException {
		film_arranges = db.ShowFilm_arrange();
		String jsonStr = JSON.toJSONString(film_arranges);
		PrintWriter printWtriter = response.getWriter();
		printWtriter.print(jsonStr);
		printWtriter.close();
		// 显示电影排片
	}

	// 选择座位
	public void ShowFilm_Seats() throws SQLException, IOException {
		seats = db.ShowSeat();
		String jsonStr = JSON.toJSONString(seats);
		PrintWriter printWtriter = response.getWriter();
		printWtriter.print(jsonStr);
		printWtriter.close();
	}

	// 购买
	public void Purchase() throws SQLException, IOException {
		// 获取字符串数组转换来的json字符串
		String jsonstr = request.getParameter("seat_number");
		// 将json字符串转换为java对象
		List<String> seats = JSON.parseArray(jsonstr, String.class);
		// 转换字符
		for (int i = 0; i < seats.size(); i++) {
			db.Purchase(seats.get(i));
		}
		String jsonStr = JSON.toJSONString("1");
		PrintWriter printWtriter = response.getWriter();
		printWtriter.print(jsonStr);
		printWtriter.close();
	}

	// 修改密码
	public void change_password() throws SQLException, ClassNotFoundException, IOException {
		String phone = request.getParameter("phone_number");
		String newpassword = request.getParameter("user_password");
		String number = db.getuser_byphone(phone);
		if (number == null || !number.equals(request.getParameter("user_number"))) {
			String jsonStr = JSON.toJSONString("0");
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();
		} else {
			db.setuser_bypassword(newpassword, number);
			String jsonStr = JSON.toJSONString("1");
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();
		}

	}

	// 修改用户信息
	public void change_userinfo() throws SQLException, IOException {
		if (user_avatar != null) {
			String user_number = request.getParameter("user_number");
			// 该路径为已打包发布的web项目的路径
			// G:\Projects_MyEclipse\.metadata\.me_tcat85\webapps\TMS\com.tms.view\
			String absoluteSavePath = request.getServletContext()
					.getRealPath(relativeSavePath + "/" + user_number + ".png");
			File file = new File(absoluteSavePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileInputStream input = new FileInputStream(user_avatar);
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
		db.setUser_info();
		String jsonStr = JSON.toJSONString("1");
		PrintWriter printWtriter = response.getWriter();
		printWtriter.print(jsonStr);
		printWtriter.close();
	}

	// 通过用户账号返回返回用户电话
	public void getphone_byuser() throws SQLException, IOException {
		String phone_number = db.getphone_byuser(request.getParameter("user_number"));
		if (phone_number == null) {
			String jsonStr = JSON.toJSONString("0");
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();
		} else {
			String jsonStr = JSON.toJSONString(phone_number);
			PrintWriter printWtriter = response.getWriter();
			printWtriter.print(jsonStr);
			printWtriter.close();
		}
	}

	// 展示电影rank
	public void ShowFilm_Rank() throws SQLException, IOException {
		ArrayList<Film_info> film_rank = new ArrayList<Film_info>();
		film_rank = db.ShowFilm_Rank();
		String jsonStr = JSON.toJSONString(film_rank);
		PrintWriter printWtriter = response.getWriter();
		printWtriter.print(jsonStr);
		printWtriter.close();
	}

	// get\set
	public File getUser_avatar() {
		return user_avatar;
	}

	public void setUser_avatar(File user_avatar) {
		this.user_avatar = user_avatar;
	}
}
