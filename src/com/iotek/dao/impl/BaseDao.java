package com.iotek.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDao {
	private static String driver;
	private static String url;
	private static String name;
	private static String pass;
	protected Connection connection;
	protected PreparedStatement ps;
	protected ResultSet rs;
	protected String sql;
	static {
		try {
			FileInputStream fis = new FileInputStream("db.properties");
			Properties properties = new Properties();
			properties.load(fis);
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			name = properties.getProperty("name");
			pass = properties.getProperty("pass");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 数据库获取连接的方法
	public Connection getConnection() {
		try {
			// 注册驱动
			Class.forName(driver);
			connection = DriverManager.getConnection(url, name, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	// 关资源
	public void closeAll() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
