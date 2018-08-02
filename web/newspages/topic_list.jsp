<%@page import="org.jbit.news.dao.TopicsDao"%>
<%@page import="org.jbit.news.entity.Topic"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>"> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加主题--管理后台</title>
<link href="css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@include file="console_element/top.jsp" %>
<div id="main">
  <%@include file="console_element/left.jsp" %>
  <div id="opt_area">
    <ul class="classlist">     
    	<%
    		request.setCharacterEncoding("UTF-8");
    		List<Topic> list =(ArrayList<Topic>) request.getAttribute("list");
    		
    		for(Topic tempTopic : list){
    	 %>
		<li> &#160;&#160;&#160;&#160; <%=tempTopic.getTname() %> &#160;&#160;&#160;&#160; <a href='../newspages/topic_modify.jsp?opr=up&tname=<%=tempTopic.getTname()%>&tid=<%=tempTopic.getTid()%>'>修改</a> &#160;&#160;&#160;&#160; <a href='TopicsServlet?opr=del&tid=<%=tempTopic.getTid()%>'>删除</a> </li>
    	<%} %>
    </ul>
  </div>
</div>
<div id="footer">
  <%@include file="console_element/bottom.html" %>
</div>
</body>
</html>
