<%@page import="org.jbit.news.util.Page"%>
<%@page import="org.jbit.news.dao.impl.NewstDaoimpl"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="org.jbit.news.dao.impl.TopicsDaoimpl"%>
<%@page import="org.jbit.news.dao.TopicsDao"%>
<%@page import="org.jbit.news.entity.Topic"%>
<%@page import="org.jbit.news.entity.Newst"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻中国</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript">

</script>
</head>

<body>

<div id="header">
  <div id="top_login">
    <form action="DologinServlet" method="post" >
      <label> 登录名 </label>
      <input type="text" name="uname" id="uname" value="" class="login_input" />
      <label> 密&#160;&#160;码 </label>
      <input type="password" name="upwd" id="upwd" value="" class="login_input" />
      <input type="submit" class="login_sub" value="登录" />
      <label id="error"> </label>
      <img src="images/friend_logo.gif" alt="Google" id="friend_logo" />
    </form>
  </div>
  <div id="nav">
    <div id="logo"> <img src="images/logo.jpg" alt="新闻中国" /> </div>
    <div id="a_b01"> <img src="images/a_b01.gif" alt="" /> </div>
    <!--mainnav end-->
  </div>
</div>
<div id="container">
<%
	String url= request.getRequestURI();
	url=url.substring(url.lastIndexOf("/")+1);
%>

<%@include file="index-elements/index_sidebar.jsp"%>
 
  <div class="main">
    <div class="class_type"> <img src="images/class_type.gif" alt="新闻中心" /> </div>
    <div class="content">
      <ul class="class_date">
 		<%
 			request.setCharacterEncoding("UTF-8");
 			List<Topic> list=(List<Topic>)request.getAttribute("list");
 			List<Newst> list4=(List<Newst>)request.getAttribute("list4");
 			System.out.println(list.size());
 			int n = 0;
 			for(Topic topic: list){
 				n++;
 				if(n % 11==1){out.println("<li id='class_month'>");}
 		%>
 		 	<a href="TopicsServlet?opr=listTitle&tid=<%=topic.getTid()%>&url="<%=url %>>
 		 	<b><%=topic.getTname()%></b></a>
 		 	<%
 		 		if(n%11==0){out.println("</li>");}
 		 		}
 		 		if(n%11!=0){out.println("</li>");}
 		 	%>
      </ul>
      <ul class="classlist"> 
      <%
       	if(list4==null){
       		out.println("<h6>出现错误！请与管理员联系</h6>");
       	}else if(list4.size()==0){
       		out.println("<h6>抱歉，没有找到相关的新闻</h6>");
       	}else{
       		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       		n=0;
       		for(Newst newst:list4){
       %>
      	<li>
      		<a href="news_read.jsp?nid=<%=newst.getNid()%>"><%=newst.getNtitle() %></a>
      		<span><%=newst.getNcreateDate() %></span>
      	</li>	
      	<%
				n++;
	      		if(n%5==0){
	      			out.println("<li class='space'></li>");
	      		}
      		}
      	}%> 
	      <!--<li><a href="#">标题 </a><span> 时间 </span></li>
	      <li class='space'></li>-->	
	      <%
	      	Page pageObj=(Page) request.getAttribute("pageObj");
	      	if(pageObj==null){
	      		request.getRequestDispatcher("TopicsServlet?opr=listTitle").forward(request, response);
	      	}
	        list4=(List<Newst>) request.getAttribute("list4");
	      	int totalPages=pageObj.getTotalPageCount();
	      	int pageIndex=pageObj.getCurrPageNo();
	       %>  
	   	<p align="right"> 当前页数:[<%=pageIndex%>/<%=totalPages %>]&nbsp;&nbsp;
	   	<%
	   		if(pageIndex>1){
	   	 %>
			<a href="TopicsServlet?opr=listTitle&pageIndex=1&url=<%=url%>">首页</a>
			<a href="TopicsServlet?opr=listTitle&pageIndex=<%=pageIndex-1%>&url=<%=url %>">&nbsp;&nbsp;上一页</a>
			<%}
			if(pageIndex<totalPages){
			 %>
			<a href="TopicsServlet?opr=listTitle&pageIndex=<%=pageIndex+1%>&url=<%=url %>">&nbsp;&nbsp;下一页</a>
			<a href="TopicsServlet?opr=listTitle&pageIndex=<%=totalPages%>&url=<%=url%>">&nbsp;&nbsp;末页</a>
		<%} %>
		</p>	
      </ul>
    </div>   
<%@include file="index-elements/index_rightbar.html"%>
  </div>
</div>
  <%@include file="index-elements/index_bottom.html"%>
</body>
</html>
