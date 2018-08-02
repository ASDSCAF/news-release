package org.jbit.news.dao.impl;

import java.sql.*;
import java.util.*;

import org.jbit.news.dao.*;
import org.jbit.news.entity.Newst;
import org.jbit.news.util.DataBaseUtil;

public class NewstDaoimpl extends BaseDao implements NewsDao {

	public NewstDaoimpl(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Newst> infoNewst() throws SQLException{
		// TODO Auto-generated method stub
		Statement stmt=null;
		ResultSet rs=null;
		
		List<Newst> ls=new ArrayList<Newst>();
		try {
//			conn=getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT * from news");
			while (rs.next()) {
				Newst newst = new Newst();
				newst.setNid(rs.getInt("nid"));
				newst.setNtid(rs.getInt("ntid"));
				newst.setNtitle(rs.getString("ntitle"));
				newst.setNauthor(rs.getString("nauthor"));
				newst.setNcreateDate(rs.getString("ncreateDate"));
				newst.setNpicPath(rs.getString("npicPath"));
				newst.setNcontent(rs.getString("ncontent"));
				newst.setNmodifyDate(rs.getString("nmodifyDate"));
				newst.setNsummary(rs.getString("nsummary"));
				ls.add(newst);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DataBaseUtil.closeAll(conn, stmt, rs);
		}
		
		return ls;
	}

	@Override
	public int getNewsConutByTID(int tid) throws SQLException{
		// TODO Auto-generated method stub
		ResultSet rs=null;
		String sql ="SELECT COUNT(`ntid`) FROM `news` WHERE `ntid`=?";
		int count=-1;
		try {
			rs=this.executerQuery(sql, tid);
			rs.next();
			count=rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally{
			DataBaseUtil.closeAll(null, null, rs);
		}
		return count;
	}

	@Override
	public List<Newst> getLatatestNewsByTID(int tid,int limit) throws SQLException{
		// TODO Auto-generated method stub
		List<Newst> list= new ArrayList<Newst>();
		ResultSet rs=null;
		String sql = "SELECT `nid`,`ntid`,`ntitle` FROM `NEWS` WHERE `ntid` = ? ORDER BY `ncreateDate` DESC LIMIT ? ";
		try {
			rs=this.executerQuery(sql, tid,limit);
			Newst newst=null;
			while (rs.next()) {
				newst = new Newst();
				newst.setNid(rs.getInt("nid"));
				newst.setNtid(rs.getInt("ntid"));
				newst.setNtitle(rs.getString("ntitle"));
				list.add(newst);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DataBaseUtil.closeAll(null, null, rs);
		}
		return list;
	}
	
	//获取所有新闻

	@Override
	public List<Newst> getAllnews() throws SQLException{
		// TODO Auto-generated method stub
		List<Newst> list= new ArrayList<Newst>();
		ResultSet rs=null;
		String sql="SELECT `nid`,`ntid`,`ntitle`,`nauthor`,`ncreateDate`,`nsummary`,`tname` " +
				"FROM news,topic WHERE news.`ntid`=topic.`tid` ORDER BY ncreateDate DESC";
		try {
			rs=this.executerQuery(sql);
			Newst newst=null;
			while (rs.next()) {
				newst= new Newst();
				newst.setNid(rs.getInt("nid"));
				newst.setNtid(rs.getInt("ntid"));
				newst.setNtitle(rs.getString("ntitle"));
				newst.setNauthor(rs.getString("nauthor"));
				newst.setNcreateDate(rs.getString("ncreateDate"));
				newst.setNsummary(rs.getString("nsummary"));
				newst.setTname(rs.getString("tname"));
				list.add(newst);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DataBaseUtil.closeAll(null, null, rs);
		}
		return list;
	}
	//获取某主题下的所有新闻
	@Override
	public List<Newst> getAllnewsByTID(int tid) throws SQLException{
		// TODO Auto-generated method stub
		List<Newst> list= new ArrayList<Newst>();
		ResultSet rs =null;
		String sql ="SELECT `nid`,`ntid`,`ntitle`,`nauthor`,`ncreateDate`,`nsummary`,`tname` " +
				"FROM news,topic WHERE news.`ntid`=topic.`tid` AND news.`ntid`=? ORDER BY ncreateDate DESC";
		try {
			rs=this.executerQuery(sql, tid);
			Newst newst=null;
			while (rs.next()) {
				newst= new Newst();
				newst.setNid(rs.getInt("nid"));
				newst.setNtid(rs.getInt("ntid"));
				newst.setNtitle(rs.getString("ntitle"));
				newst.setNauthor(rs.getString("nauthor"));
				newst.setNcreateDate(rs.getString("ncreateDate"));
				newst.setNsummary(rs.getString("nsummary"));
				newst.setTname(rs.getString("tname"));
				list.add(newst);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DataBaseUtil.closeAll(null, null, rs);
		}
		return list;
	}

	@Override
	public List<Newst> getNewstID(int id,int count) throws SQLException{
		// TODO Auto-generated method stub
		List<Newst> list= new ArrayList<Newst>();
		ResultSet rs =null;
		String sql = "SELECT * FROM `News` WHERE ntid=? limit ?";
		rs=this.executerQuery(sql, id,count);
		try {
			while (rs.next()) {
				Newst news= new Newst();
				news.setNid(rs.getInt("nid"));
				news.setNtid(rs.getInt("ntid"));
				news.setNtitle(rs.getString("ntitle"));
				news.setNauthor(rs.getString("nauthor"));
				news.setNcreateDate(rs.getString("ncreateDate"));
				news.setNpicPath(rs.getString("npicPath"));
				news.setNcontent(rs.getString("ncontent"));
				news.setNmodifyDate(rs.getString("nmodifyDate"));
				news.setNsummary(rs.getString("nsummary"));
				list.add(news);
			
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			DataBaseUtil.closeAll(null, null, rs);
		}
		
		return list;
	}

	@Override
	public List<Newst> select(String sql, int tid) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		List<Newst> list = new ArrayList<Newst>();
		String sql1 = "select * from news" + sql;
		try {
			if (tid == 0) {
				rs = this.executerQuery(sql1);
			} else {
				rs = this.executerQuery(sql1, tid);
			}
			while (rs.next()) {
				Newst n = new Newst();
				n.setNid(rs.getInt("nid"));
				n.setNtid(rs.getInt("ntid"));
				n.setNtitle(rs.getString("ntitle"));
				n.setNauthor(rs.getString("nauthor"));
				n.setNcreateDate(rs.getString("ncreateDate"));
				n.setNpicPath(rs.getString("npicPath"));
				n.setNcontent(rs.getString("ncontent"));
				n.setNmodifyDate(rs.getString("nmodifyDate"));
				n.setNsummary(rs.getString("nsummary"));
				list.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBaseUtil.closeAll(null, null, rs);
		}
		return list;
	}

	@Override
	public List<Newst> select(int id, int count) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		List<Newst> list = new ArrayList<Newst>();
		String sql = "select * from news where ntid=? limit ?";
		try {
			rs = this.executerQuery(sql, id,count);
			while (rs.next()) {
				Newst n = new Newst();
				n.setNid(rs.getInt("nid"));
				n.setNtid(rs.getInt("ntid"));
				n.setNtitle(rs.getString("ntitle"));
				n.setNauthor(rs.getString("nauthor"));
				n.setNcreateDate(rs.getString("ncreateDate"));
				n.setNpicPath(rs.getString("npicPath"));
				n.setNcontent(rs.getString("ncontent"));
				n.setNmodifyDate(rs.getString("nmodifyDate"));
				n.setNsummary(rs.getString("nsummary"));
				list.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DataBaseUtil.closeAll(null, null, rs);
		}
		return list;
	}

	@Override
	public int getTotalCount() throws SQLException {
		// TODO Auto-generated method stub
		int count=-1;
		String sql="SELECT COUNT('nid') FROM `news`";
		ResultSet rs=null;
		try {
			rs=executerQuery(sql);
			if (rs.next()) {
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return count;
	}

	@Override
	public List<Newst> getPageNewsList(int pageNo, int pageSize)
			throws SQLException {
		// TODO Auto-generated method stub
		List<Newst> newsList= new ArrayList<Newst>();
		String sql=" SELECT `nid`,`ntid`,`ntitle`,`nauthor`,`ncreateDate`,nsummary,tname FROM news,topic WHERE news.ntid=topic.tid ORDER BY ncreateDate DESC LIMIT ?,? ";
		ResultSet rs=null;
		try {
			rs=executerQuery(sql,(pageNo-1)*pageSize,pageSize);
			while (rs.next()) {
				Newst ns=new Newst();
				ns.setNid(rs.getInt("nid"));
				ns.setNtid(rs.getInt("ntid"));
				ns.setNtitle(rs.getString("ntitle"));
				ns.setNauthor(rs.getString("nauthor"));
				ns.setNcreateDate(rs.getString("ncreateDate"));
				ns.setNsummary(rs.getString("nsummary"));
				ns.setTname(rs.getString("tname"));
				newsList.add(ns);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
		return newsList;
	}

	@Override
	public int findShangChuan(Newst newst) throws SQLException {
		// TODO Auto-generated method stub
		String sql="INSERT INTO `news` VALUES (null,?,?,?,?,?,?,?,?)";
		int i=-1;
		try {
			i=executerUpdate(sql,newst.getNtid(),newst.getNtitle(),newst.getNauthor(),newst.getNcreateDate(),newst.getNpicPath(),newst.getNcontent(),newst.getNmodifyDate(),newst.getNsummary());
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(conn, null, null);
		}
		return i;
	}
	

}
