<%@page import="org.jbit.news.entity.Topic"%>
<%@page import="org.jbit.news.entity.News_users"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
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
    <form action="util/do_login.jsp" method="post" >
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
		String uri=request.getRequestURI();   
		  		uri=uri.substring(uri.lastIndexOf("/")+1);
	%>
<%@include file="index-elements/index_sidebar.jsp"%>
 
  <div class="main">
    <div class="class_type"> <img src="images/class_type.gif" alt="新闻中心" /> </div>
    <div class="content">
      <ul class="class_date">
      <%
      	List<Newst> list4 = (List<Newst>)request.getAttribute("list4");
            		List<Topic> list = (List<Topic>)request.getAttribute("list");
                  	int n = 0;
                  	for(Topic t: list){
                  		n++;
                  		if(n%11==1){
                  			out.print("<li id='class_month'>");
                  		}
      %>
       <a href="util/news_control.jsp?opr=listTitle&tid=<%=t.getTid()%>"><b><%=t.getTname()%></b></a>
       <%
       	if(n%11==0){out.print("</li>");}
                     		}
                     		if(n%11!=0){out.print("</li>");}
       %>
        <ul class="classlist">
        <%
        	if(list4==null){
                        	out.print("<h6>出现错误！</h6>");
                        	}else if(list4.size()==0){
                        	out.print("<h6>没有找到相关新闻</h6>");
                        	}else{
                        //	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        	n=0;
                        	for(Newst news: list4){
        %>
         <li>
         	<a href="#"><%=news.getNtitle() %>	</a>
         	<span><%=news.getNcreateDate() %></span>
         	<li>
         		<%
         			n++;
         			if(n%5==0){out.print("<li class='space'></li>");}
         			}
         		 }%>
         	</li>
         </li>
	      	<!-- <li id='class_month'><a href="#"><b> 国外 </b></a> 
	      	<a href="#"><b> 国内 </b></a><a href="#"><b> 时事 </b></a></li>
      </ul>
      <ul class="classlist">        
	      <li><a href="#">标题 </a><span> 时间 </span></li>
	      <li class='space'></li> -->	  
	   	<p align="right"> 当前页数:[1/10]&nbsp;&nbsp; <a href="#">首页</a><a href="#">&nbsp;&nbsp;上一页</a><a href="#">&nbsp;&nbsp;下一页</a> <a href="#">&nbsp;&nbsp;末页</a></p>	
      </ul>
    </div>   
<%@include file="index-elements/index_rightbar.html"%>
  </div>
</div>
  <%@include file="index-elements/index_bottom.html"%>
</body>
</html>
