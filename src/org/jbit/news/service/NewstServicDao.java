package org.jbit.news.service;

import java.sql.SQLException;
import java.util.List;
import org.jbit.news.entity.Comments;
import org.jbit.news.entity.News_users;
import org.jbit.news.entity.Newst;
import org.jbit.news.entity.Topic;
import org.jbit.news.util.Page;

public interface NewstServicDao {
	Topic selectTop(String tname)throws SQLException;
	
	List<Newst> select(String sql,int tid)throws SQLException; 
	
	List<Topic> selectTop() throws SQLException;

	int deleteTop(int tid) throws SQLException;
	int addTop(String tname) throws SQLException;
	int updateTop(Topic topic) throws SQLException;

	//ssssss
	
	News_users login(News_users nu) throws SQLException;
	
	List<Newst> getLatestNewByTID(int id,int count) throws SQLException;
	int getNewsCountByTID(int tid) throws SQLException;
	
	List<Comments> select(int cnid) throws SQLException;
	int insert(Comments cs) throws SQLException;
	//
	List<Newst> infoNewst() throws SQLException;
	//
	List<Newst> getAllnews()throws SQLException;
	List<Newst> getAllnewsByTID(int tid)throws SQLException;
	//分夜获得新闻
	public void findPageNews(Page pageObj) throws SQLException;
	//上传新闻
	public int findINNSDE(Newst newst) throws SQLException;
}
