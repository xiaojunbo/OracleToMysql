package com.res.db.share.service.util.mysql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	private static Properties p = new Properties();

	static {
		try {
			File f = new File("F:/tool/db.properties");
			InputStream in = new FileInputStream(f);
			p.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection1() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("装载驱动包出现异常!请查正！");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(p.getProperty("db1"), p.getProperty("user1"), p.getProperty("pwd1"));
		} catch (SQLException e) {
			System.out.println("链接数据库发生异常!");
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getConnection2() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("装载驱动包出现异常!请查正！");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(p.getProperty("db2"), p.getProperty("user2"), p.getProperty("pwd2"));
		} catch (SQLException e) {
			System.out.println("链接数据库发生异常!");
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] ss) {
		System.out.println(p.getProperty("db1") + p.getProperty("user1") + p.getProperty("pwd1"));
		System.out.println(p.getProperty("db2") + p.getProperty("user2") + p.getProperty("pwd1"));

		getConnection2();
		getConnection1();
	}
}
