package org.jbit.news.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.jbit.news.dao.BaseDao;
import org.jbit.news.dao.CommentsDao;
import org.jbit.news.entity.Comments;
import org.jbit.news.util.DataBaseUtil;

public class CommentsDaoimpl extends BaseDao implements CommentsDao{

	public CommentsDaoimpl(Connection conn) {
		super(conn);
	}

	@Override
	public List<Comments> select(int cnid) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		List<Comments> list= new ArrayList<Comments>();
		String sql="select * FROM comments WHERE cnid=?";
		try {
			rs=this.executerQuery(sql, cnid);
			while (rs.next()) {
				Comments com = new Comments();
				com.setCid(rs.getInt("cid"));
				com.setCnid(rs.getInt("cnid"));
				com.setCcontent(rs.getString("ccontent"));
				com.setCdate(rs.getString("cdate"));
				com.setCip(rs.getString("cip"));
				com.setCauthor(rs.getString("cauthor"));
				list.add((Comments) com);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}finally{
			DataBaseUtil.closeAll(null, null, rs);
		}
		return list;
	}

	@Override
	public int insert(Comments cs) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
