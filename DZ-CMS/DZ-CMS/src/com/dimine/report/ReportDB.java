package com.dimine.report;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.dimine.sys.data.ResourceManager;
import com.dimine.sys.util.SpringUtil;

public class ReportDB {
	private static DataSource dataSource = null;

	public static Connection getConnection() throws Exception {
		Connection conn = null;
		try {
			if (dataSource != null) {
				conn = dataSource.getConnection();
			} else {
				dataSource = (DataSource) SpringUtil.getBean(ResourceManager.getInstance().getServletContext(), "dataSource");
				conn = dataSource.getConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}

		return conn;
	}

	public static void release(Connection conn) {
		try {
			if ((conn != null) && (!conn.isClosed()))
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}