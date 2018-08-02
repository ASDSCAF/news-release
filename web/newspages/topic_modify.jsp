<%@page import="org.jbit.news.dao.impl.TopicsDaoimpl"%>
<%@page import="org.jbit.news.dao.TopicsDao"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@include file="console_element/top.jsp" %>

<script type="text/javascript">

</script>

<div id="main">
  <%@include file="console_element/left.jsp" %>
  <div id="opt_area">
    <h1 id="opt_type"> 修改主题： </h1>
   		 <%
        	request.setCharacterEncoding("UTF-8");
        	String tname=request.getParameter("tname");
        	//tname = new String(tname.getBytes("ISO-8859-1"),"UTF-8");
        	int tid=Integer.parseInt(request.getParameter("tid"));
        	
         %>
    <form action="../TopicsServlet?opr=up&tid=<%=tid%>" method="post">
      <p>
        <label> 主题名称 </label>
        <input name="tname" id="tname" type="text" class="opt_input" value="<%=tname%>"/>
        <input name="tid" type="hidden" value=""/>
      </p>
      <input name="action" type="hidden" value="addtopic"/>
      <input type="submit" value="提交" class="opt_sub" />
      <input type="reset" value="重置" class="opt_sub" />
    </form>
  </div>
</div>
<%@include file="console_element/bottom.html" %>
