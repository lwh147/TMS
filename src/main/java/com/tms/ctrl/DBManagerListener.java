package com.tms.ctrl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class DBManagerListener implements ServletContextListener {

	public DBManagerListener() {

	}

	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
		Object con = ctx.getAttribute("DBCon");
		if (con != null) {
			Connection conn = (Connection) con;
			try {
				if (!conn.isClosed()) {
					System.out.println("数据库关闭");
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void contextInitialized(ServletContextEvent sce) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tms?characterEncoding=UTF-8&useSSL=false",
					"root", "123456");
			ServletContext ctx = sce.getServletContext();
			ctx.setAttribute("DBCon", con);
			System.out.println("数据库连接成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接失败");
			e.printStackTrace();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
	}

}
