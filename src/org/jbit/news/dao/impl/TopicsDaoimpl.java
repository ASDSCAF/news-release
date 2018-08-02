package org.jbit.news.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jbit.news.dao.BaseDao;
import org.jbit.news.dao.TopicsDao;
import org.jbit.news.entity.Topic;
import org.jbit.news.util.DataBaseUtil;
//import org.jbit.news.util.DataBaseUtil;

public class TopicsDaoimpl extends BaseDao implements TopicsDao{

	public TopicsDaoimpl(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	//获得所有主题
	@Override
	public List<Topic> getAllTopics() throws SQLException{
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		Topic topic=null;
		List<Topic> ls= new ArrayList<Topic>();
		try {
//			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT * FROM topic");
			while (rs.next()) {
				topic=new Topic();
				topic.setTid(rs.getInt("tid"));
				topic.setTname(rs.getString("tname"));
				ls.add(topic);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DataBaseUtil.closeAll(conn, stmt, rs);
		}
		return ls;
	}
	//更新主题
	@Override
	public int updateTopic(Topic topic) throws SQLException{
		// TODO Auto-generated method stub
		String sql ="UPDATE `topic` SET `tname` = ? WHERE `tid`= ? ";
		int result=0;
		try {
			result=executerUpdate(sql, topic.getTname(),topic.getTid());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DataBaseUtil.closeAll(null, null, null);
		}
		return result;
	}
	//根据名字查找主题
	@Override
	public Topic findTopicByName(String name) throws SQLException{
		// TODO Auto-generated method stub
		 ResultSet rs =null;
		 Topic t=null;
		 String sql="SELECT * FROM topic WHERE tname=?";
		 try {
			rs=this.executerQuery(sql, name);
			while (rs.next()) {
				t=new Topic();
				t.setTid(rs.getInt("tid"));
				t.setTname(rs.getString("tname"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(null, null, rs);
		}
		return t;
	}
	//添加主题
	@Override
	public int addTopic(String name) throws SQLException{
		// TODO Auto-generated method stub
		String sql ="INSERT INTO `TOPIC` (`tname`) VALUES(?);";
		int res=0;
		try {
			res=executerUpdate(sql, name);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DataBaseUtil.closeAll(null, null, null);
		}
		return res;
	}
	//通过tid删除主题
	@Override
	public int deleteTopic(int tid) throws SQLException{
		// TODO Auto-generated method stub
		String sql="DELETE FROM `TOPIC` WHERE `tid`=?";
		 int result=0;
		 try {
			result =executerUpdate(sql, tid);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			DataBaseUtil.closeAll(null, null, null);
		}
		return result;
	}
	
}
