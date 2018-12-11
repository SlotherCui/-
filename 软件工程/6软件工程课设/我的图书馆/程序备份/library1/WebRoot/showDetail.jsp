<%@ page language="java" import="java.util.*,library.wtz.dao.*,library.wtz.form.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String s_bookId=request.getParameter("bookId");
int bookId=Integer.parseInt(s_bookId);
BookBean book=new GetBookInfo().getBookDetail(bookId);
String state=request.getParameter("state");
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

  </head>
  <body>
    <table width="822" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="STYLE4">
	  <jsp:include page="top.jsp"></jsp:include>
	  <tr>
	    <td >
	      <table align="center" cellpadding="10" cellspacing="10">
	      	<tr>
	      	  <td>书编号:<%=book.getId() %></td>
	          <td>ISBN:<%=book.getIsbn() %></td>
	          <td>书名:<%=book.getName() %></td>
	      	</tr>
	      	<tr>
	      	  <td>作者:<%=book.getAuthor() %></td>
	      	  <td>译者:<%=book.getTranslator() %></td>
	      	  <td>价格:<%=book.getPrice() %></td>
	      	</tr>
	      	<tr>
	      	  <td>馆藏地:<%=book.getLibId() %></td>
	      	  <td>入馆时间:<%=book.getInTime() %></td>
	      	  <td>出版时间:<%=book.getPublishTime() %></td>
	      	</tr>
	      	<tr>
	      	  <td>图书类别:<%=book.getTypeId() %></td>
	      	  <td>出版社:<%=book.getPress() %></td>
	      	  <td>字数:<%=book.getWord() %>千字</td>
	      	</tr>
	      	<tr>
	      	  <td>文献类型:<%=book.getLanguage() %></td>
	      	  <td>状态:<%=book.getState() %></td>
	      	  <td>描述:<%=book.getDescribtion() %></td>
	      	</tr>
	      </table>
	    </td>
	  </tr>
	  <tr>
	  	<td>
	  	  <table align="center" width="80%" border="1">
	  	  	<tr>
	  	  	  <td>书编号</td>
	  	  	  <td>书名</td>
	  	  	  <td>借阅日期</td>
	  	  	  <td>应还日期</td>
	  	  	  <td>预约</td>
	  	  	</tr>
	  	  	<tr>
	  	  	  <td><%=book.getId() %></td>
	  	  	  <td><%=book.getName() %></td>
	  	  	 <%
	  	  	   if(book.getState().equals("在馆")){ 
	  	  	 %>
	  	  	  <td>在馆</td>
	  	  	  <td>在馆</td>
	  	  	  <td>在馆，不能预约</td>
	      	 <% 
	      	 	}else{
	      	 	   BorrowInfoBean borrow=new GetBookInfo().BorrowInfo(bookId);
	      	 %>
	      	    <td><%=borrow.getBorrowTime() %></td>
	  	  	    <td><%=borrow.getRestrictTime() %></td>
	      	 <%
	      	    if(state==null){
	      	 		if(book.getState().equals("被预约")||book.getState().equals("预约到馆")){
	      	 %>
	      	          <td>此书已被预约</td>
	      	 <%	
	      	 		}else{
	      	 %>
	      	    <td>
	      	    <form action="OrderCl">
	      	    <input type="submit" name="order" value="预约"/>
	      	    <input type="hidden" name="bookId" value=<%=bookId %> />
	      	    </form>
	      	    </td>
	      	 <%
	      	 		}
	      	 	}else if(state.equals("overBorrowNum")){
	      	 		//已经借满，不能预约
	      	 %>
	      	 	<td>您已借满，不能预约</td>
	      	 <%
	      	 	}else if(state.equals("overTime")){
	      	 	   //有超期未还的书，不能预约
	      	 %>
	      	    <td>有超期未还的书，不能预约</td>
	      	 <%
	      	 	}else if(state.equals("overOrderNum")){
	      	 	   //已达到最大预约量，不能预约
	      	 %>
	      	 	<td>已达到最大预约量，不能预约</td>
	      	 <%
	      	 	}else{
	      	 	   //预约成功
	      	 %>
	      	 	<td>预约成功</td>
	      	 <%
	      	 	}
	      	 	
	      	 	}
	      	 %>
	  	  	</tr>
	  	  </table>
	  	</td>
	  </tr>
	  <tr>
	  <td>&nbsp;</td>
	  </tr>
	</table>
  </body>
</html>
