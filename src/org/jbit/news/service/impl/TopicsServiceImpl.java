package org.jbit.news.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.jbit.news.dao.NewsDao;
import org.jbit.news.dao.TopicsDao;
import org.jbit.news.dao.impl.NewstDaoimpl;
import org.jbit.news.dao.impl.TopicsDaoimpl;
import org.jbit.news.entity.Topic;
import org.jbit.news.service.TopicsService;
import org.jbit.news.util.DataBaseUtil;

public class TopicsServiceImpl implements TopicsService{

	@Override
	public List<Topic> findAllTopics() throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try {
			conn=DataBaseUtil.getConnection();
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
	public int updateTopic(Topic topic) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Topic findTopicByName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addTopic(String name) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteTopic(int tid) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn =null;
		int result = 0;
		try {
			conn=DataBaseUtil.getConnection();
			conn.setAutoCommit(false);
			NewsDao newsdao= new NewstDaoimpl(conn);
			TopicsDao topicsdao= new TopicsDaoimpl(conn);
			if(newsdao.getNewsConutByTID(tid)==0){
				result=topicsdao.deleteTopic(tid);
				
			}else{
				result=-1;
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO: handle exception 
			e.printStackTrace();
			if (conn!=null) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				throw e;
			}
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
		
		return result;
	}
	
	
}
