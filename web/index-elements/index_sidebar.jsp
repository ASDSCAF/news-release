<%@page import="org.jbit.news.entity.Newst"%>
<%@page import="org.jbit.news.dao.NewsDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<div class="sidebar">
    <h1> <img src="images/title_1.gif" alt="国内新闻" /> </h1>
    <div class="side_list">
      <ul>    
      <%
          	List<Newst> list1=(List<Newst>) request.getAttribute("list1");
                    		List<Newst> list2=(List<Newst>) request.getAttribute("list2");
                    		List<Newst> list3=(List<Newst>) request.getAttribute("list3");
                    		if(list1==null && list2==null && list3==null){
                    		//response.sendRedirect("util/topic_control.jsp?opr=listTitle");
                    	request.getRequestDispatcher("TopicsServlet?opr=listTitle&url="+url).forward(request, response);
                    	return; 
                    		}
                         		for(Newst news :list1){
          %>
     		<li><a href="#"><b><%=news.getNtitle()%></b></a></li>
     	<%
     		}
     	%>   	
      </ul>
    </div>
    <h1> <img src="images/title_2.gif" alt="国际新闻" /> </h1>
    <div class="side_list">
      <ul>
     	<%
     		for(Newst news :list2){
     	%>
     		<li><a href="#"><b><%=news.getNtitle()%></b></a></li>
     	<%
     		}
     	%>  
      </ul>
    </div>
    <h1> <img src="images/title_3.gif" alt="娱乐新闻" /> </h1>
    <div class="side_list">
      <ul>     	
     	<%
     	     		for(Newst news :list3){
     	     	%>
     		<li><a href="#"><b><%=news.getNtitle() %></b></a></li>
     	<%
     		}
     	 %>  
      </ul>
    </div>
  </div>