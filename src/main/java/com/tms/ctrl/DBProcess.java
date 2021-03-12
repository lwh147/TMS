package com.tms.ctrl;

import com.tms.model.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class DBProcess {

	public HttpServletRequest request;
	public Connection con;
	public Statement stat;

	public DBProcess(HttpServletRequest request) {
		this.request = request;
		ServletContext stx = request.getSession().getServletContext();
		con = (Connection) stx.getAttribute("DBCon");

	}

	// 执行查询语句
	private ResultSet getRS(String sql) throws SQLException {
		stat = con.createStatement();
		ResultSet res = stat.executeQuery(sql);
		return res;
	}

	// 关闭结果集和Statement（使用pstatement时需单独关闭结果集和pstatement，调用该方法会出错）
	private void closeRS(ResultSet rs) throws SQLException {
		stat.close();
		rs.close();
	}

	// 执行更新语句
	private void exePrepare_update(String sql, ArrayList<String> params) throws SQLException {
		PreparedStatement pstat = (PreparedStatement) con.prepareStatement(sql);
		int i = 1;
		for (String param : params) {
			pstat.setString(i++, param);
		}
		pstat.execute();
		pstat.close();
	}

	protected int exeUpdate(String sql, Object[] params) throws SQLException, ClassNotFoundException {
		PreparedStatement pstat = con.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			pstat.setObject(i + 1, params[i]);
		}
		pstat.executeUpdate();
		return 0;
	}

	// 执行sql语句，返回值
	private ResultSet exePrepare(String sql, ArrayList<String> params) throws SQLException {
		PreparedStatement pstat = (PreparedStatement) con.prepareStatement(sql);
		int i = 1;
		for (String param : params) {
			pstat.setString(i++, param);
		}

		ResultSet rs = pstat.executeQuery();

		return rs;
	}

	// 显示指定电影场次
	public ArrayList<Film_arrange> ShowFilm_arrange() throws SQLException {
		String sql = "select * from film_arrange where film_name=";
		String film_name = request.getParameter("film_name");
		sql += "\'" + film_name + "\'";
		ResultSet rs = getRS(sql);
		ArrayList<Film_arrange> film_arrange = new ArrayList<Film_arrange>();
		while (rs.next()) {
			film_arrange.add(toFilm_arrange(rs));
		}
		Date date = new Date();
		ArrayList<Film_arrange> film_arrangeNow = new ArrayList<Film_arrange>();
		for (int i = 0; i < film_arrange.size(); i++) {
			if (film_arrange.get(i).getFilm_playtime().getTime() > date.getTime()) {
				film_arrangeNow.add(film_arrange.get(i));
			}
		}
		closeRS(rs);
		Collections.sort(film_arrangeNow);// 按照时间排序
		return film_arrangeNow;
	}

	// 查询指定电影指定场次座位信息
	public ArrayList<String> ShowSeat() throws SQLException {
		String sql = "select * from cinema_seat where film_name = ? and film_playtime = ? and film_playroom = ?";

		ArrayList<String> params = new ArrayList<String>();
		params.add(request.getParameter("film_name"));
		params.add(request.getParameter("film_playtime"));
		params.add(request.getParameter("film_playroom"));
		ResultSet rs = exePrepare(sql, params);
		ArrayList<String> seatsStr = new ArrayList<String>();
		while (rs.next()) {
			seatsStr.add(toCinema_seat(rs).getSeat());
		}
		rs.close();
		return seatsStr;
	}

	// 查询指定电影指定场次票价
	public double SearchFilm_Price() throws SQLException {
		String sql = "select * from film_arrange where film_playtime = ? and film_playroom = ? and film_name = ?";
		ArrayList<String> params = new ArrayList<String>();
		params.add(request.getParameter("film_playtime"));
		params.add(request.getParameter("film_playroom"));
		params.add(request.getParameter("film_name"));
		ResultSet rs = exePrepare(sql, params);
		if (rs.next()) {
			double price = toFilm_arrange(rs).getFilm_price();
			rs.close();
			return price;
		} else {
			rs.close();
			return 0;
		}
	}

	// 购票
	public void Purchase(String seats) throws SQLException {
		String sql = "insert into cinema_seat values (?,?,?,?)";

		ArrayList<String> params = new ArrayList<String>();
		params.add(request.getParameter("film_name"));
		params.add(request.getParameter("film_playtime"));
		params.add(request.getParameter("film_playroom"));
		params.add(seats);

		exePrepare_update(sql, params);

		String recordSQL = "insert into purchase_record values (NULL, ?,?,?,?,?,?)";

		ArrayList<String> paramstoRecord = new ArrayList<String>();
		paramstoRecord.add(request.getParameter("user_number"));
		paramstoRecord.add(request.getParameter("film_name"));
		String sence = request.getParameter("film_playtime") + "/" + request.getParameter("film_playroom");
		paramstoRecord.add(sence);
		paramstoRecord.add(request.getParameter("purchase_time"));
		paramstoRecord.add(seats);
		Random rand = new Random();
		int random = rand.nextInt(1000) + 1000;
		String ticketCode = String.valueOf(random);
		paramstoRecord.add(ticketCode);

		exePrepare_update(recordSQL, paramstoRecord);

	}

	// 查询电影
	public Film_info SearchFilm() throws SQLException {
		String sql = "select * from film_info where film_name=";
		String film_name = request.getParameter("film_name");
		sql += "\'" + film_name + "\'";
		ResultSet rs = getRS(sql);
		if (rs.next()) {
			Film_info fifo = toFilm_info(rs);
			closeRS(rs);
			return fifo;

		} else {
			closeRS(rs);
			return null;
		}

	}

	// 关键字搜索
	public ArrayList<Film_info> SearchByKeyWords() throws SQLException {
		String sql = "select * from film_info where film_name like ?";
		ArrayList<String> params = new ArrayList<String>();
		if (request.getParameter("film_name").equals("") || request.getParameter("film_name") == null) {
			return null;
		}
		params.add("%" + request.getParameter("film_name") + "%");
		ResultSet rs = exePrepare(sql, params);
		ArrayList<Film_info> film_info = new ArrayList<Film_info>();
		while (rs.next()) {
			film_info.add(toFilm_info(rs));
		}
		return film_info;
	}

	// 展示热映电影
	public ArrayList<Film_info> getFilmListHot() throws SQLException {
		String sql = "select * from film_info";
		ResultSet rs = getRS(sql);
		ArrayList<Film_info> films = new ArrayList<Film_info>();
		while (rs.next()) {
			films.add(toFilm_info(rs));
		}
		Date date = new Date();
		ArrayList<Film_info> films_hot = new ArrayList<Film_info>();
		for (int i = 0; i < films.size(); i++) {
			if (films.get(i).getUnshelt_time().getTime() > date.getTime()
					&& films.get(i).getShelt_time().getTime() < date.getTime()) {
				films_hot.add(films.get(i));
			}
		}
		closeRS(rs);
		return films_hot;
	}

	// 展示即将上线的电影
	public ArrayList<Film_info> getFilmListReady() throws SQLException {
		String sql = "select * from film_info";
		ResultSet rs = getRS(sql);
		ArrayList<Film_info> films = new ArrayList<Film_info>();
		while (rs.next()) {
			films.add(toFilm_info(rs));
		}
		Date date = new Date();
		ArrayList<Film_info> films_ready = new ArrayList<Film_info>();
		for (int i = 0; i < films.size(); i++) {
			if (films.get(i).getShelt_time().getTime() > date.getTime()) {
				films_ready.add(films.get(i));
			}
		}
		closeRS(rs);
		return films_ready;
	}

	// 根据用户名得到订单信息
	public ArrayList<Purchase_record> getOrderBynumber() throws SQLException {
		String sql = "select * from purchase_record where user_number=";
		String user_number = request.getParameter("user_number");
		sql += "\'" + user_number + "\'";
		ResultSet rs = getRS(sql);
		ArrayList<Purchase_record> pcrdList = new ArrayList<Purchase_record>();
		while (rs.next()) {
			pcrdList.add(toPurchase_record(rs));
		}
		closeRS(rs);
		return pcrdList;
	}

	// 检查账号是否重复
	public String getRUser_number() throws SQLException {
		String sql = "select * from user_info where user_number=";
		String user_number = request.getParameter("user_number");
		sql += "\'" + user_number + "\'";
		String number;
		ResultSet rs = getRS(sql);
		if (rs.next()) {
			User user = toUser(rs);
			number = user.getUser_number();
			closeRS(rs);
			return number;
		} else {
			closeRS(rs);
			return null;
		}
	}

	// 核对电话号码是否已被注册
	public String getPhone_number() throws SQLException {
		String sql = "select phone_number from user_info where phone_number=";
		String phone_number = request.getParameter("phone_number");
		sql += "\'" + phone_number + "\'";
		String phone;
		ResultSet rs = getRS(sql);
		if (rs.next()) {
			closeRS(rs);
			phone = "1";
			return phone;
		} else {
			closeRS(rs);
			return null;
		}
	}

	// 用户注册
	public void Register_user() throws SQLException {
		String insertSQL = "insert into user_info values (?,?,?,?,?,?)";
		ArrayList<String> params = new ArrayList<String>();
		params.add(request.getParameter("user_number"));
		params.add(request.getParameter("user_password"));
		params.add(request.getParameter("phone_number"));
		params.add("default");
		params.add("男");
		params.add("1999-01-01");
		exePrepare_update(insertSQL, params);
	}

	// 用户登录
	public String Login() throws SQLException {
		String sql = "select * from user_info where user_number=";
		String user_number = request.getParameter("user_number");
		sql += "\'" + user_number + "\'";
		String passwd;
		ResultSet rs = getRS(sql);
		if (rs.next()) {

			User user = toUser(rs);
			passwd = user.getUser_password();
			closeRS(rs);
			return passwd;
		} else {
			closeRS(rs);
			return null;
		}

	}

	// 通过电话号码返回用户账号
	public String getuser_byphone(String phone) throws SQLException {
		String sql = "select * from user_info where phone_number=?";

		ArrayList<String> params = new ArrayList<String>();
		params.add(phone);
		ResultSet rs = exePrepare(sql, params);
		if (rs.next()) {
			String pwd = toUser(rs).getUser_number();
			rs.close();
			return pwd;
		}
		return null;
	}

	// 通过用户账号返回返回用户电话
	public String getphone_byuser(String user_number) throws SQLException {
		String sql = "select * from user_info where user_number=?";

		ArrayList<String> params = new ArrayList<String>();
		params.add(user_number);
		ResultSet rs = exePrepare(sql, params);
		if (rs.next()) {
			String un = toUser(rs).getPhone_number();
			rs.close();
			return un;
		}
		return null;
	}

	// 重置密码
	public void setuser_bypassword(String password, String user_number) throws ClassNotFoundException, SQLException {
		String sql = "update user_info set user_password = ? where user_number = ?";
		ArrayList<String> params = new ArrayList<String>();
		params.add(password);
		params.add(user_number);
		Object[] ob = params.toArray();
		exeUpdate(sql, ob);
	}

	// 修改用户信息
	public void setUser_info() throws SQLException {
		String sql = "update user_info set user_name = ?, user_sex = ?, user_birthday = ? where user_number = ?";
		ArrayList<String> params = new ArrayList<String>();
		params.add(request.getParameter("user_name"));
		params.add(request.getParameter("user_sex"));
		params.add(request.getParameter("user_birthday"));
		params.add(request.getParameter("user_number"));
		exePrepare_update(sql, params);
	}

	// 查询指定用户名的用户信息
	public User showUser_info() throws SQLException {

		String sql = "select * from user_info where user_number=";
		String number = request.getParameter("user_number");
		sql += "\'" + number + "\'";
		ResultSet rs = getRS(sql);
		if (rs.next()) {
			User user = toUser(rs);
			closeRS(rs);
			return user;
		} else {
			closeRS(rs);
			return null;
		}
	}

	// 电影榜单
	public ArrayList<Film_info> ShowFilm_Rank() throws SQLException {
		String sql = "select * from film_info ";
		ResultSet rs = getRS(sql);
		ArrayList<Film_info> film_info = new ArrayList<Film_info>();
		int i = 0;
		while (rs.next()) {
			if (i == 10)
				break;
			film_info.add(toFilm_info(rs));
			i++;
		}

		Collections.sort(film_info);
		return film_info;
	}

	// ----管理员用到的数据库操作如下----//

	// 管理员登录
	public String admin_login() throws SQLException {
		String sql = "select * from admin where admin_name=";
		String admin_number = request.getParameter("admin_name");
		sql += "\'" + admin_number + "\'";
		String passwd;
		ResultSet rs = getRS(sql);
		if (rs.next()) {

			Admin aduser = toAdmin(rs);
			passwd = aduser.getAdmin_password();
			closeRS(rs);
			return passwd;
		} else {
			closeRS(rs);
			return null;
		}
	}

	// 查询所有电影排片信息
	public ArrayList<Film_arrange> arrange_list() throws SQLException {
		String sql = "select * from film_arrange";
		ResultSet rs = getRS(sql);
		ArrayList<Film_arrange> list = new ArrayList<Film_arrange>();
		while (rs.next()) {
			list.add(toFilm_arrange(rs));
		}
		closeRS(rs);
		return list;
	}

	// 添加电影排片信息
	public void add_arrange() throws SQLException {
		String sql = "insert into film_arrange values(?,?,?,?)";
		ArrayList<String> params = new ArrayList<String>();
		params.add(request.getParameter("film_playtime"));
		params.add(request.getParameter("film_playroom"));
		params.add(request.getParameter("film_name"));
		params.add(request.getParameter("film_price"));
		exePrepare_update(sql, params);
	}

	// 判断指定电影名的电影是否存在
	public boolean film_exist() throws SQLException {
		String sql = "select * from film_info where film_name=?";
		ArrayList<String> params = new ArrayList<String>();
		params.add(request.getParameter("film_name"));
		ResultSet rs = exePrepare(sql, params);

		if (rs.next()) {
			return true;
		} else {
			rs.close();
			return false;
		}

	}

	// 添加影片
	public void add_film() throws ClassNotFoundException, SQLException, FileNotFoundException {
		String sql = "insert into film_info values(?,?,?,?,?,?,?,?,?)";
		ArrayList<String> params = new ArrayList<String>();
		params.add(request.getParameter("film_name"));
		params.add(request.getParameter("shelt_time"));
		params.add(request.getParameter("unshelt_time"));
		params.add(request.getParameter("film_info"));
		params.add(request.getParameter("starring"));
		params.add(request.getParameter("film_type"));
		params.add(request.getParameter("duration"));
		params.add(request.getParameter("film_score"));
		params.add(request.getParameter("film_director"));

		exePrepare_update(sql, params);
	}

	// 查询用户
	public User getuser_bynumber() throws ClassNotFoundException, SQLException {
		String sql = "select * from user_info where user_number=";
		String user_number = request.getParameter("user_number");
		sql += "\'" + user_number + "\'";
		ResultSet rs = getRS(sql);

		if (rs.next()) {
			User user = toUser(rs);
			closeRS(rs);
			return user;
		} else {
			closeRS(rs);
			return null;
		}

	}

	// 删除用户
	public void del_User(String user_number) throws ClassNotFoundException, SQLException {
		String sql = "delete from user_info where user_number=?";
		String[] p = new String[1];
		p[0] = user_number;
		exeUpdate(sql, p);
	}

	// 返回影片列表
	public ArrayList<Film_info> film_list() throws SQLException {
		String sql = "select * from film_info";
		ResultSet rs = getRS(sql);
		ArrayList<Film_info> list = new ArrayList<Film_info>();
		while (rs.next()) {
			list.add(toFilm_info(rs));
		}
		closeRS(rs);
		return list;
	}

	// 返回用户信息列表
	public ArrayList<User> user_list() throws SQLException {
		String sql = "select * from user_info";
		ResultSet rs = getRS(sql);
		ArrayList<User> list = new ArrayList<User>();
		while (rs.next()) {
			list.add(toUser(rs));
		}
		closeRS(rs);
		return list;
	}

	// 将结果集转换为User对象
	private User toUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUser_number(rs.getString(1));
		user.setUser_password(rs.getString(2));
		user.setPhone_number(rs.getString(3));

		user.setUser_name(rs.getString(4));
		user.setUser_sex(rs.getString(5));
		user.setUser_birthday(rs.getDate(6));

		return user;
	}

	// 将结果集转换为Film_Info对象
	private Film_info toFilm_info(ResultSet rs) throws SQLException {
		Film_info film = new Film_info();

		film.setFilm_name(rs.getString(1));
		film.setShelt_time(rs.getDate(2));
		film.setUnshelt_time(rs.getDate(3));
		film.setFilm_info(rs.getString(4));
		film.setStarring(rs.getString(5));
		film.setFilm_type(rs.getString(6));
		film.setDuration(rs.getInt(7));
		film.setFilm_score(rs.getDouble(8));
		film.setFilm_director(rs.getString(9));

		return film;
	}

	// 将结果集转换为Film_arrange对象
	private Film_arrange toFilm_arrange(ResultSet rs) throws SQLException {
		Film_arrange film_arr = new Film_arrange();

		film_arr.setFilm_playtime(rs.getTimestamp(1));
		film_arr.setFilm_playroom(rs.getInt(2));
		film_arr.setFilm_name(rs.getString(3));
		film_arr.setFilm_price(rs.getDouble(4));
		return film_arr;
	}

	// 将结果集转换为Cinema_seat对象
	private Cinema_seat toCinema_seat(ResultSet rs) throws SQLException {
		Cinema_seat ces = new Cinema_seat();

		ces.setFilm_playtime(rs.getDate(2));
		ces.setFilm_playroom(rs.getInt(3));
		ces.setFilm_name(rs.getString(1));
		ces.setSeat(rs.getString(4));

		return ces;
	}

	// 将结果集转换为Purchase_record对象
	private Purchase_record toPurchase_record(ResultSet rs) throws SQLException {
		Purchase_record pcrd = new Purchase_record();
		pcrd.setId(rs.getInt(1));
		pcrd.setUser_number(rs.getString(2));
		pcrd.setFilm_name(rs.getString(3));
		pcrd.setFilm_scence(rs.getString(4));
		pcrd.setPurchase_time(rs.getString(5));
		pcrd.setSeat_number(rs.getString(6));
		pcrd.setTicket_code(rs.getString(7));

		return pcrd;
	}

	// 将结果集转换为Admin对象
	private Admin toAdmin(ResultSet rs) throws SQLException {
		Admin admin = new Admin();

		admin.setAdmin_name(rs.getString(1));
		admin.setAdmin_password(rs.getString(2));

		return admin;
	}

}
