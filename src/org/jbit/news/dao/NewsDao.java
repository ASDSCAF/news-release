package org.jbit.news.dao;

import java.sql.SQLException;
import java.util.List;
import org.jbit.news.entity.Newst;

public interface NewsDao {
	List<Newst> infoNewst() throws SQLException;
	
	int getNewsConutByTID(int tid)throws SQLException;
	
	List<Newst> getLatatestNewsByTID(int tid,int limit)throws SQLException;
	
	List<Newst> getAllnews()throws SQLException;
	
	List<Newst> getAllnewsByTID(int tid)throws SQLException;
	
	List<Newst> getNewstID(int id,int count)throws SQLException;
	
	List<Newst> select(String sql,int tid) throws SQLException;
	List<Newst> select(int id,int count) throws SQLException;
	
	public int getTotalCount()throws SQLException;
	
	//public int getTotalCount()throws SQLException;
	public List<Newst> getPageNewsList(int pageNo,int pageSize) throws SQLException;
	//上传新闻
	public int findShangChuan(Newst newst)throws SQLException;
}
