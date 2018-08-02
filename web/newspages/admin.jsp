<%@ page language="java" import="java.util.*,java.sql.*,org.jbit.news.entity.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>"> 
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
<meta http-equiv="description" content="This is my page"/>
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
    		int i=0;
    		List<Newst> list=(ArrayList<Newst>) request.getAttribute("list");
    		if(list==null){
    			response.sendRedirect("../servlet/adminServlet");
    			return;
    		}
    		for(Newst tempNewst :list ){
    		i++;
     	%>
	     <li><%=tempNewst.getNtitle() %><span>作者：<%=tempNewst.getNauthor() %>&#160;&#160;&#160;&#160;<a href="#">修改</a> &#160;&#160;&#160;&#160; <a href="" >删除</a> </span> </li>
		     <% if(i%5==0){
		     	%><li class='space'> </li><%
		     	}
	      	}
	      %>
    </ul>
  </div>
</div>
<div id="footer">
  <%@include file="console_element/bottom.html" %>
</div>
</body>
</html>
