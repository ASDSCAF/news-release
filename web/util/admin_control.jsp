<%@page import="org.jbit.news.service.NewstServicDao"%>
<%@page import="org.jbit.news.service.impl.NewstServicDaoimpl"%>
<%@page import="org.jbit.news.dao.impl.NewstDaoimpl"%>
<%@page import="org.jbit.news.dao.NewsDao"%>
<%@page import="org.jbit.news.entity.Newst"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'admin_control.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
    	NewstServicDao nedao= new NewstServicDaoimpl();
     	List<Newst> list= nedao.infoNewst();
   		request.setAttribute("list", list);
   		//out.print(list.size()); //60
   		request.getRequestDispatcher("../newspages/admin.jsp").forward(request, response);
    %>
  </body>
</html>
