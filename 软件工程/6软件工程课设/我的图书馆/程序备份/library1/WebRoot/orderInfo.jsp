<%@ page language="java" import="java.util.*,library.wtz.form.*,library.wtz.dao.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userId=(String)session.getAttribute("user");
Vector<OrderInfoBean> order=new User().getOrderInfo(userId);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ͼ����Ϣ����ϵͳ-ԤԼ��Ϣ</title>
    
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
	      <td>�ݲص�</td>
	      <td>Ӧ������</td>
	      <td>�黹����</td>
	      <td>�鿯״̬</td>
	      <td>ȡ��ԤԼ</td>
	    </tr>
	    <% 
	      for(int j=0;j<order.size();j++){
	         OrderInfoBean orderInfo=order.get(j);
	    %>
	         <tr>
	         	<td><%=orderInfo.getBookId() %></td>
	         	<td><%=orderInfo.getBookName() %></td>
	         	<td><%=orderInfo.getLibrary() %></td>
	         	<td><%=orderInfo.getRestrictedTime() %></td>
	         	<td><%=orderInfo.getReturnTime() %></td>
	         	<td><%=orderInfo.getState() %></td>
	         	<td>
			      <form action="CancelOrderCl">
			      <input type="submit" name=<%=orderInfo.getBookId() %> value="ȡ��ԤԼ"/>
			      <input type="hidden" name="bookId" value=<%=orderInfo.getBookId() %> />
			      <input type="hidden" name="bookState" value=<%=orderInfo.getState() %> />
			      </form>
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
