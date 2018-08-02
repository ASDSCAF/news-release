<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@include file="console_element/top.jsp" %>
<script type="text/javascript">

</script>

<div id="main">
  <%@include file="console_element/left.jsp" %>
  <div id="opt_area">
    <h1 id="opt_type"> 编辑新闻： </h1>
    <form action="#" method="post" enctype="multipart/form-data" onsubmit="return check()">
      <p>
        <label> 主题 </label>
        <select name="ntid">
        	<option value='' selected="selected">主题</option>        	
        </select>
        <input type="hidden" name="nid" value="" />
      </p>
      <p>
        <label> 标题 </label>
        <input name="ntitle" id="ntitle" type="text" class="opt_input" value=""/>
      </p>
      <p>
        <label> 作者 </label>
        <input name="nauthor" id="nauthor" type="text" class="opt_input" value=""/>
      </p>
      <p>
        <label> 摘要 </label>
        <textarea name="nsummary" id="nsummary" cols="40" rows="3">摘要</textarea>
      </p>
      <p>
        <label> 内容 </label>
        <textarea name="ncontent" id="ncontent" cols="70" rows="10">内容</textarea>
      </p>
      <p>
        <label> 上传图片 </label>
        <input name="file" id="file" type="file" class="opt_input" />
      </p>
      <input type="submit" value="提交" class="opt_sub" />
      <input type="reset" value="重置" class="opt_sub" />
    </form>
    <h1 id="opt_type">
		修改新闻评论：
	</h1>
      <table width="80%" align="left">
      <tr>
      	<td colspan="6"> 暂无评论！ </td>
      </tr>
         <tr>
           <td colspan="6"><hr />
           </td>
         </tr>
       	<tr>
	          <td> 留言人： </td>
	          <td>无名</td>
	          <td> IP： </td>
	          <td>127.0.0.1</td>
	          <td> 留言时间： </td>
	          <td>2015-1-1</td>
	          <td><a href="">删除</a></td>
	        </tr>
	        <tr>
	          <td colspan="6">内容</td>
	        </tr>
	        <tr>
	          <td colspan="6"><hr />
	          </td>
	        </tr>
      </table>
  </div>
</div>
<%@include file="console_element/bottom.html" %>