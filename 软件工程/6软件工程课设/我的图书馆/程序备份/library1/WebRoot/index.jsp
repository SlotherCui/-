<%@ page language="java" import="java.util.*,library.wtz.form.*,library.wtz.dao.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图书信息管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	<!--
	.STYLE2 {
		font-size: 18px;
		color: #0000FF;
	}
	.STYLE3 {font-size: 14px}
	a{text-decoration:none;}
	-->
	</style>
  </head>
  
  <body>
    <table width="822" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
	  <jsp:include page="top.jsp"></jsp:include>
	  <tr>
	    <td height="417" ><table width="90%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td>
	         <form id="form1" name="form1" method="post" action="SearchCl" class="STYLE3">
	            <span class="STYLE2">馆藏书目检索</span><br>
	            <input name="textfield" type="text" size="35" />
	            <input type="submit" name="Submit" value="检索" /><br><br>
	              文献类型:
	              <input name="language" type="radio" value="所有书刊" checked="checked" />所有书刊
	              <input type="radio" name="language" value="中文图书" />中文图书
	              <input type="radio" name="language" value="西文图书" />西文图书<br><br>
	              检索类型：
	              <select name="method">
	              <option selected="selected">书名</option>
	              <option>ISBN</option>
	              <option>作者</option>
	              <option>出版社</option>
	              </select><br><br>
	              检索模式：
	              <input name="model" type="radio" value="模糊匹配" checked="checked" />模糊匹配            
	              <input type="radio" name="model" value="完全匹配" />完全匹配<br><br>
	              每页显示记录数：<input name="pageSize" type="text" size="6" />	<br><br>
	              结果排序方式以：<select name="ordermethod">
				            <option selected="selected" value="book_name">书名</option>
				            <option value="book_publish_time">出版日期</option>
				            <option value="book_isbn">ISBN</option>
	                        </select>
	              <input name="order" type="radio" value="asc" checked="checked" />升序方式
	              <input type="radio" name="order" value="desc" />降序方式排列<br><br>
	              馆藏地
	              <select name="library">
	              <option selected="selected" value="">所有馆藏地</option>
	              <%
	                Vector<LibraryBean> vector=new GetBookInfo().getAllLibrary();
	                for(int i=0;i<vector.size();i++){
	              %>
	                  <option value=<%=vector.get(i).getId() %>><%=vector.get(i).getName() %></option>
	              <%
	                }
	              %>
	              
	            </select>           
	        </form></td></tr></table></td>
	      </tr>
	     
	      
	     
	</table>
  </body>
</html>
