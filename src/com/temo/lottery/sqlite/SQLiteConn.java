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
	 * ��SQLiteǶ��ʽ���ݿ⽨������
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
					"�����������ݿ��ļ��ܵ��Ƿ��޸Ļ�ɾ����"));
		}
		return connection;
	}
}
