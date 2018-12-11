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
    
    <title>ͼ����Ϣ����ϵͳ</title>
    
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
	      	  <td>����:<%=book.getId() %></td>
	          <td>ISBN:<%=book.getIsbn() %></td>
	          <td>����:<%=book.getName() %></td>
	      	</tr>
	      	<tr>
	      	  <td>����:<%=book.getAuthor() %></td>
	      	  <td>����:<%=book.getTranslator() %></td>
	      	  <td>�۸�:<%=book.getPrice() %></td>
	      	</tr>
	      	<tr>
	      	  <td>�ݲص�:<%=book.getLibId() %></td>
	      	  <td>���ʱ��:<%=book.getInTime() %></td>
	      	  <td>����ʱ��:<%=book.getPublishTime() %></td>
	      	</tr>
	      	<tr>
	      	  <td>ͼ�����:<%=book.getTypeId() %></td>
	      	  <td>������:<%=book.getPress() %></td>
	      	  <td>����:<%=book.getWord() %>ǧ��</td>
	      	</tr>
	      	<tr>
	      	  <td>��������:<%=book.getLanguage() %></td>
	      	  <td>״̬:<%=book.getState() %></td>
	      	  <td>����:<%=book.getDescribtion() %></td>
	      	</tr>
	      </table>
	    </td>
	  </tr>
	  <tr>
	  	<td>
	  	  <table align="center" width="80%" border="1">
	  	  	<tr>
	  	  	  <td>����</td>
	  	  	  <td>����</td>
	  	  	  <td>��������</td>
	  	  	  <td>Ӧ������</td>
	  	  	  <td>ԤԼ</td>
	  	  	</tr>
	  	  	<tr>
	  	  	  <td><%=book.getId() %></td>
	  	  	  <td><%=book.getName() %></td>
	  	  	 <%
	  	  	   if(book.getState().equals("�ڹ�")){ 
	  	  	 %>
	  	  	  <td>�ڹ�</td>
	  	  	  <td>�ڹ�</td>
	  	  	  <td>�ڹݣ�����ԤԼ</td>
	      	 <% 
	      	 	}else{
	      	 	   BorrowInfoBean borrow=new GetBookInfo().BorrowInfo(bookId);
	      	 %>
	      	    <td><%=borrow.getBorrowTime() %></td>
	  	  	    <td><%=borrow.getRestrictTime() %></td>
	      	 <%
	      	    if(state==null){
	      	 		if(book.getState().equals("��ԤԼ")||book.getState().equals("ԤԼ����")){
	      	 %>
	      	          <td>�����ѱ�ԤԼ</td>
	      	 <%	
	      	 		}else{
	      	 %>
	      	    <td>
	      	    <form action="OrderCl">
	      	    <input type="submit" name="order" value="ԤԼ"/>
	      	    <input type="hidden" name="bookId" value=<%=bookId %> />
	      	    </form>
	      	    </td>
	      	 <%
	      	 		}
	      	 	}else if(state.equals("overBorrowNum")){
	      	 		//�Ѿ�����������ԤԼ
	      	 %>
	      	 	<td>���ѽ���������ԤԼ</td>
	      	 <%
	      	 	}else if(state.equals("overTime")){
	      	 	   //�г���δ�����飬����ԤԼ
	      	 %>
	      	    <td>�г���δ�����飬����ԤԼ</td>
	      	 <%
	      	 	}else if(state.equals("overOrderNum")){
	      	 	   //�Ѵﵽ���ԤԼ��������ԤԼ
	      	 %>
	      	 	<td>�Ѵﵽ���ԤԼ��������ԤԼ</td>
	      	 <%
	      	 	}else{
	      	 	   //ԤԼ�ɹ�
	      	 %>
	      	 	<td>ԤԼ�ɹ�</td>
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
