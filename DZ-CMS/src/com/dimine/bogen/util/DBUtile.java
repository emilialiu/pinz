package com.dimine.bogen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtile {
	private String driverClassName; //数据库驱动类名
	private String url; //数据库地址
	private Connection c = null; //数据库连接
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Connection getC() {
		return c;
	}
	public void setC(Connection c) {
		this.c = c;
	}
	/**
	 * 连接数据库服务器
	 */
	public Connection connectDB(String DBType,String sid,String username,String password) {

		System.out.println("=======开始连接数据库========>");
		if("ORACLE".equals(DBType)){
			this.setDriverClassName("oracle.jdbc.driver.OracleDriver");
			this.setUrl("jdbc:oracle:thin:@" + sid);
			try {
				Class.forName(this.getDriverClassName()).newInstance();
			} catch (InstantiationException e) {
				return null;
			} catch (IllegalAccessException e) {
				return null;
			} catch (ClassNotFoundException e) {
				return null;
			}
			// 与url指定的数据源建立连接
			try {
				c = DriverManager.getConnection(this.getUrl(), username, password);
				return c;
			} catch (SQLException e) {
				return null;
			}			
		}
		if("SQLSERVER".equals(DBType)){
			//jdbc.url=jdbc:sqlserver://192.168.1.18:1433;databaseName=pcb1111			
			this.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			this.setUrl("jdbc:sqlserver://" + sid);
			try {
				Class.forName(this.getDriverClassName()).newInstance();
			} catch (InstantiationException e) {
				return null;
			} catch (IllegalAccessException e) {
				return null;
			} catch (ClassNotFoundException e) {
				return null;
			}
			// 与url指定的数据源建立连接
			try {
				c = DriverManager.getConnection(this.getUrl(), username, password);
				return c;
			} catch (SQLException e) {
				return null;
			}			
		}
		if("MYSQL".equals(DBType)){
			//jdbc:mysql://localhost:3306/sample_db?user=root&password=your_password		
			this.setDriverClassName("com.mysql.jdbc.Driver");
			this.setUrl("jdbc:mysql://"+sid+"?user="+username+"&password="+password);
			try {
				Class.forName(this.getDriverClassName()).newInstance();
			} catch (InstantiationException e) {
				return null;
			} catch (IllegalAccessException e) {
				return null;
			} catch (ClassNotFoundException e) {
				return null;
			}
			// 与url指定的数据源建立连接
			try {
				c = DriverManager.getConnection(this.getUrl());
				return c;
			} catch (SQLException e) {
				return null;
			}			
		}
		return null;
	}
}
