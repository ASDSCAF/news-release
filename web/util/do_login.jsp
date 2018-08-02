<%@page import="org.jbit.news.service.impl.NewstServicDaoimpl"%>
<%@page import="org.jbit.news.dao.impl.news_usersDaoimpl"%>
<%@page import="org.jbit.news.dao.News_usersDao"%>
<%@page import="org.jbit.news.entity.News_users"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String name=request.getParameter("uname");
	String pwd=request.getParameter("upwd");
	News_users news=new News_users();
	news.setName(name);
	news.setPwd(pwd);
	News_users news1 = new NewstServicDaoimpl().login(news);
	
	if(news1 != null){
		response.sendRedirect("../newspages/admin.jsp?name="+name);
	}else{
		response.sendRedirect("../index.jsp");
	}
%>