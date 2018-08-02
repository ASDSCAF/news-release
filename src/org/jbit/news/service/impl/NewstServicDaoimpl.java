package org.jbit.news.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jbit.news.dao.NewsDao;
import org.jbit.news.dao.impl.CommentsDaoimpl;
import org.jbit.news.dao.impl.NewstDaoimpl;
import org.jbit.news.dao.impl.TopicsDaoimpl;
import org.jbit.news.dao.impl.news_usersDaoimpl;
import org.jbit.news.entity.Comments;
import org.jbit.news.entity.News_users;
import org.jbit.news.entity.Newst;
import org.jbit.news.entity.Topic;
import org.jbit.news.service.NewstServicDao;
import org.jbit.news.util.DataBaseUtil;
import org.jbit.news.util.Page;

public class NewstServicDaoimpl implements NewstServicDao {
	public Connection conn =null;
	@Override
	public List<Newst> select(String sql, int tid) throws SQLException {
		Connection conn = null;
		try {
			conn = DataBaseUtil.getConnection();
			return new NewstDaoimpl(conn).select(sql,tid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	@Override
	public Topic selectTop(String tname) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn=DataBaseUtil.getConnection();
			return new TopicsDaoimpl(conn).findTopicByName(tname);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	@Override
	public List<Topic> selectTop() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn =DataBaseUtil.getConnection();
			return new TopicsDaoimpl(conn).getAllTopics();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	@Override
	public int deleteTop(int tid) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		int res=0;
		try {
			conn=DataBaseUtil.getConnection();
			conn.setAutoCommit(false);
			res=new TopicsDaoimpl(conn).deleteTopic(tid);
			conn.commit();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
		return res;
	}

	@Override
	public int addTop(String tname) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn =null;
		try {
			conn=DataBaseUtil.getConnection();
			return new TopicsDaoimpl(conn).addTopic(tname);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	@Override
	public int updateTop(Topic topic) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn =null;
		try {
			conn=DataBaseUtil.getConnection();
			return new TopicsDaoimpl(conn).updateTopic(topic);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	@Override
	public News_users login(News_users nu) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn=DataBaseUtil.getConnection();
			return new news_usersDaoimpl(conn).findNews(nu);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	@Override
	public List<Newst> getLatestNewByTID(int id, int count) throws SQLException {
		// TODO Auto-generated method stub
		try {
			conn =DataBaseUtil.getConnection();
			return new NewstDaoimpl(conn).select(id, count);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	@Override
	public int getNewsCountByTID(int tid) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn =null;
		try {
			conn=DataBaseUtil.getConnection();
			return new NewstDaoimpl(conn).getNewsConutByTID(tid);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	@Override
	public List<Comments> select(int cnid) throws SQLException {
		// TODO Auto-generated method stub
		try {
			conn=DataBaseUtil.getConnection();
			return new CommentsDaoimpl(conn).select(cnid);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	@Override
	public int insert(Comments cs) throws SQLException {
		// TODO Auto-generated method stub
		try {
			conn=DataBaseUtil.getConnection();
			return new CommentsDaoimpl(conn).insert(cs);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	@Override
	public List<Newst> infoNewst() throws SQLException {
		// TODO Auto-generated method stub
		try {
			conn=DataBaseUtil.getConnection();
			return new NewstDaoimpl(conn).infoNewst();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	@Override
	public List<Newst> getAllnews() throws SQLException {
		// TODO Auto-generated method stub
		try {
			conn=DataBaseUtil.getConnection();
			return new NewstDaoimpl(conn).getAllnews();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	@Override
	public List<Newst> getAllnewsByTID(int tid) throws SQLException {
		// TODO Auto-generated method stub
		try {
			conn=DataBaseUtil.getConnection();
			return new NewstDaoimpl(conn).getAllnewsByTID(tid);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	@Override
	public void findPageNews(Page pageObj) throws SQLException {
		// TODO Auto-generated method stub
		try {
			conn = DataBaseUtil.getConnection();
			NewsDao newsDao = new NewstDaoimpl(conn);
			int totalCount =newsDao.getTotalCount();
			pageObj.setTotalCount(totalCount);
			if(totalCount>0){
				if(pageObj.getCurrPageNo()>pageObj.getTotalPageCount()){
					pageObj.setCurrPageNo(pageObj.getTotalPageCount());
				}
				
				//对末页进行控制
				List<Newst> newstList= newsDao.getPageNewsList(pageObj.getCurrPageNo(), pageObj.getPageSize());
				pageObj.setNewsList(newstList);
			}else {
				pageObj.setCurrPageNo(0);
				pageObj.setNewsList(new ArrayList<Newst>());
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	
	@Override
	public int findINNSDE(Newst newst) throws SQLException {
		// TODO Auto-generated method stub
		
		try {
			conn=DataBaseUtil.getConnection();
			return new NewstDaoimpl(conn).findShangChuan(newst);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
	}

	
}
