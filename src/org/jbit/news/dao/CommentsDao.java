package org.jbit.news.dao;

import java.sql.SQLException;
import java.util.List;
import org.jbit.news.entity.Comments;

public interface CommentsDao {
	List<Comments> select(int cnid) throws SQLException;
	int insert(Comments cs) throws SQLException;
}
