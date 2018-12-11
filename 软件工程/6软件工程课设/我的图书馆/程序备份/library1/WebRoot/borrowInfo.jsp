<%@ page language="java" import="java.util.*,library.wtz.form.*,library.wtz.dao.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user=(String)session.getAttribute("user");
Vector<BorrowInfoBean> vector=new User().getBorrowBook(user);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图书信息管理系统-书刊借阅</title>
    
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
	    <table width="90%" class="STYLE" align="center" cellspacing="10">
	    	<tr>
	    	  <td>书号</td>
	    	  <td>ISBN</td>
	    	  <td>书名</td>
	    	  <td>作者</td>
	    	  <td>馆藏地</td>
	    	  <td>借阅日期</td>
	    	  <td>应还日期</td>
	    	  <td>续借</td>
	    	</tr>
	    <%
	       for(int i=0;i<vector.size();i++){
	       	  BorrowInfoBean borrow=vector.get(i);
	     %>
	     	<tr>
	    	  <td><%=borrow.getId() %></td>
	    	  <td><%=borrow.getIsbn() %></td>
	    	  <td><%=borrow.getName() %></td>
	    	  <td><%=borrow.getAuthor() %></td>
	    	  <td><%=borrow.getLibrary() %></td>
	    	  <td><%=borrow.getBorrowTime() %></td>
	    	  <td><%=borrow.getRestrictTime() %></td>  
	    	  <td>
	    	  <%
	    	    String s_bookId=request.getParameter("bookId");
	    	    if(s_bookId==null){//非续借 
	    	  %>
	    	  <form action="ReborrowCl">
	    	  <input type="hidden" name="bookId" value=<%=borrow.getId() %> />
	    	  <input type="submit" name=<%=borrow.getId() %> value="续借"/>
	    	  </form>
	    	  <% 
	    	    }else{
	    	      int bookId=Integer.parseInt(s_bookId);
	    	      if(bookId==borrow.getId()){
	    	         String type=request.getParameter("type");
	    	         if(type.equals("overtime")){
	    	   %>
	    	               有超期的书
	    	   <%
	    	         }else if(type.equals("reborrowed")){
	    	   %>
	    	   	        已达到最大续借次数
	    	   <%      
	    	         }else{
	    	   %>
	    	             已被预约
	    	   <%
	    	         }
	    	      }else{
	    	  %>
	    	  <form action="ReborrowCl">
	    	  <input type="hidden" name="bookId" value=<%=borrow.getId() %> />
	    	  <input type="submit" name=<%=borrow.getId() %> value="续借"/>
	    	  </form>
	    	  <%
	    	      }
	    	    }
	    	  %>
	    	  </td>
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
