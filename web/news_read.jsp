<%@page import="org.jbit.news.entity.Comments"%>
<%@page import="org.jbit.news.service.impl.NewstServicDaoimpl"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/read.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

</script>
</head>
<div id="container">
<%
	String url= request.getRequestURI();
	url=url.substring(url.lastIndexOf("/")+1);
%>
<%
	request.setCharacterEncoding("UTF-8");
	String nid = request.getParameter("nid");
	List<Newst> list = new NewstServicDaoimpl().select(" WHERE nid = ?", Integer.parseInt(nid));
	Newst newst=list.get(0);
%>
  <%@include file="index-elements/index_sidebar.jsp"%>
  <div class="main">
    <div class="class_type"> <img src="images/class_type.gif" alt="新闻中心" /> </div>
    <div class="content">
      <ul class="classlist">
        <table width="80%" align="center">
          <tr width="100%">
            <td colspan="2" align="center"><%=newst.getNtitle() %></td>
          </tr>
          <tr>
            <td colspan="2"><hr />
            </td>
          </tr>
          <tr>
            <td align="center">作者：<%=newst.getNauthor() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             </a></td>
            <td align="left">发布时间：<%=newst.getNcreateDate() %></td>
          </tr>          
          <tr>
            <td colspan="2" align="center"></td>
          </tr>
          <tr>
            <td colspan="2"><%=newst.getNcontent() %></td>
          </tr>
          <tr>
            <td colspan="2"><hr />
            </td>
          </tr>
        </table>
      </ul>
      <ul class="classlist">
        <table width="80%" align="center">
        	<%
         	List<Comments> comlist = new NewstServicDaoimpl().select(newst.getNid());
        	if(comlist.size()==0){
        	
        	 %>
        	<tr>
        	<td colspan="6"> 暂无评论！ </td>
        	</tr>
	          <tr>
	            <td colspan="6"><hr />
	            </td>
	          </tr>  
	          <%
	          	}else {
	          		for(Comments ce : comlist){
	           %>           	
        		<tr>
				    <td> 留言人： </td>
				    <td><%=ce.getCauthor() %></td>
					<td> IP： </td>
					<td><%=ce.getCip() %></td>
					<td> 留言时间： </td>
					<td><%=ce.getCdate() %></td>
				</tr>
				<tr>
					<td colspan="6">留言内容</td>
				</tr>
				<tr>
					<td colspan="6"><hr />
					</td>
				</tr>
				<%
					}
				}
				 %>
        </table>
      </ul>
      <ul class="classlist">
        <form action="../util/topic_control.jsp?cnid=<%=newst.getNid() %>&opr=newsread" method="post" >
          <table width="80%" align="center">
            <tr>
              <td> 评 论 </td>
            </tr>
            <tr>
              <td> 用户名： </td>
              <td>             
	              <input id="cauthor" name="cauthor" value="什么也没有哦！" readonly="readonly" style="border:0px;"/> 
                IP：
                <input name="cip" id="cip" value="<%=request.getRemoteAddr() %>" readonly="readonly" style="border:0px;"/>
              </td>
            </tr>
            <tr>
              <td colspan="2"><textarea name="ccontent" id="ccontent" cols="70" rows="10"></textarea>
              </td>
            </tr>
            <td><input name="submit" value="发  表" type="submit"/>
              </td>
          </table>
        </form>
      </ul>
    </div>
  </div>
</div>
<%@include file="index-elements/index_bottom.html"%>
</html>