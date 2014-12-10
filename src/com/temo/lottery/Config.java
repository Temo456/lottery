package com.temo.lottery;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	private static Config instance = new Config();
	private static Properties p;

	private Config() {
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("config.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static Config GetInstance() {
		return instance;
	}

	public Properties getConfig() {
		return p;
	}
}
