package org.jbit.news.dao;

import java.sql.SQLException;
import java.util.List;

import org.jbit.news.entity.News_users;

public interface News_usersDao {
	News_users findNews(News_users news) throws SQLException;
	List <News_users> findnews(String name);
}
