package org.jbit.news.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jbit.news.dao.BaseDao;
import org.jbit.news.dao.News_usersDao;
import org.jbit.news.entity.News_users;
import org.jbit.news.util.DataBaseUtil;


public class news_usersDaoimpl extends BaseDao implements News_usersDao{
	public news_usersDaoimpl(Connection conn) {
		super(conn);
	}
	
	@Override
	public News_users findNews(News_users news) throws SQLException{
		// TODO Auto-generated method stub
		News_users ns=null;
		ResultSet rst=null;
		try {
//			conn=getConnection();
			String sql="SELECT * FROM news_users WHERE uname=? AND upwd=?";
			rst=executerQuery(sql,news.getName(),news.getPwd());
			if (rst.next()) {
				ns=new News_users();
				ns.setId(rst.getInt("uid"));
				ns.setName(rst.getString("uname"));
				ns.setPwd(rst.getString("upwd"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DataBaseUtil.closeAll(null, null, rst);
		}
		return ns;
	}

	@Override
	public List<News_users> findnews(String name) {
		// TODO Auto-generated method stub
		
		return null;
	}
}
