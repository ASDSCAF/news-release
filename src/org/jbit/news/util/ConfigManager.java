package org.jbit.news.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jbit.news.dao.BaseDao;

public class ConfigManager {
	private static Properties params = null;
	static{
		
		String configFile = "database.properties";
		InputStream is = BaseDao.class.getClassLoader().getResourceAsStream(
				configFile);
		if(is==null){
			throw new RuntimeException("找不到数据库配置文件！");
		}
		params= new Properties();
		try {
			params.load(is);
		} catch (IOException e) {
			throw new RuntimeException("数据库加载错误！");
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String getProperty(String k) {
		// TODO Auto-generated method stub
		return params.getProperty(k);
	}
	
}
