<%@ page language="java" import="java.util.*,library.wtz.form.*,library.wtz.dao.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	            <span class="STYLE2">�ݲ���Ŀ����</span><br>
	            <input name="textfield" type="text" size="35" />
	            <input type="submit" name="Submit" value="����" /><br><br>
	              ��������:
	              <input name="language" type="radio" value="�����鿯" checked="checked" />�����鿯
	              <input type="radio" name="language" value="����ͼ��" />����ͼ��
	              <input type="radio" name="language" value="����ͼ��" />����ͼ��<br><br>
	              �������ͣ�
	              <select name="method">
	              <option selected="selected">����</option>
	              <option>ISBN</option>
	              <option>����</option>
	              <option>������</option>
	              </select><br><br>
	              ����ģʽ��
	              <input name="model" type="radio" value="ģ��ƥ��" checked="checked" />ģ��ƥ��            
	              <input type="radio" name="model" value="��ȫƥ��" />��ȫƥ��<br><br>
	              ÿҳ��ʾ��¼����<input name="pageSize" type="text" size="6" />	<br><br>
	              �������ʽ�ԣ�<select name="ordermethod">
				            <option selected="selected" value="book_name">����</option>
				            <option value="book_publish_time">��������</option>
				            <option value="book_isbn">ISBN</option>
	                        </select>
	              <input name="order" type="radio" value="asc" checked="checked" />����ʽ
	              <input type="radio" name="order" value="desc" />����ʽ����<br><br>
	              �ݲص�
	              <select name="library">
	              <option selected="selected" value="">���йݲص�</option>
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
