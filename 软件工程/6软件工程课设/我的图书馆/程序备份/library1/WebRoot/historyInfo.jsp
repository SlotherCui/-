<%@ page language="java" import="java.util.*,library.wtz.dao.*,library.wtz.form.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userId=(String)session.getAttribute("user");
Vector<BorrowHistoryBean> vector=new User().getBorrowHistory(userId);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ͼ����Ϣ����ϵͳ-������ʷ</title>
    
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
	.STYLE {
		font-size: 14px; 
	}
	-->
	</style>

  </head>
  
  <body>
    <table width="822" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
	  <jsp:include page="myLibraryTop.jsp"></jsp:include>
	  <tr>
	    <td><div style="overflow:auto; height=400">
	    <table width="90%" class="STYLE" border="0" align="center" cellspacing="10">
	    <tr>
	      <td>����</td>
	      <td>����</td>
	      <td>����</td>
	      <td>�ݲص�</td>
	      <td>��������</td>
	      <td>�黹����</td>
	    </tr>
	    <%
	       for(int i=0;i<vector.size();i++){
	          BorrowHistoryBean history=vector.get(i);
	    %>
	    	  <tr>
	    	  	<td><%=history.getBookId() %></td>
	    	  	<td><%=history.getBookName() %></td>
	    	  	<td><%=history.getAuthor() %></td>
	    	  	<td><%=history.getLibrary() %></td>
	    	  	<td><%=history.getBorrowTime() %></td>
	    	  	<td><%=history.getReturnTime() %></td>
	    	  </tr>
	    <%
	       } 
	    %>
	    </table>
	    </div></td>
	  </tr>
	</table>
  </body>
</html>
