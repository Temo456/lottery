package com.temo.lottery.sqlite;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import com.temo.lottery.Config;

public class SQLiteConn {

	public SQLiteConn() {
		super();
	}

	/**
	 * 与SQLite嵌入式数据库建立连接
	 * 
	 * @return Connection
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		Connection connection = null;
		try {
			InputStream inputStream = this.getClass().getClassLoader()
					.getResourceAsStream("config.properties");
			Properties p = new Properties();
			try {
				p.load(inputStream);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			Class.forName("org.sqlite.JDBC", true, this.getClass()
					.getClassLoader());
			connection = DriverManager.getConnection("jdbc:sqlite:"
					+ Config.GetInstance().getConfig().getProperty("dbfile"));
		} catch (Exception e) {
			throw new Exception("" + e.getLocalizedMessage(), new Throwable(
					"可能由于数据库文件受到非法修改或删除。"));
		}
		return connection;
	}
}
