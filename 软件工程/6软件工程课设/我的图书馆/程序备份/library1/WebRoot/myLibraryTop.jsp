<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style type="text/css">
<!--

   a{text-decoration:none;}

-->
</style>

<tr>
  <td><img src="images/bookshelf2.jpg" width="822" height="104" /></td>
</tr>
<tr bgcolor="#99FFFF">
  <td height="24" background="images/theme6.jpg">
  <a href="myLibrary.jsp">��ҳ</a> |&nbsp;
  <a href="index.jsp">����</a>|&nbsp;
  <a href="userInfo.jsp">������Ϣ</a>|&nbsp;
  <a href="borrowInfo.jsp">�鿯����</a>|&nbsp;
  <a href="durtyInfo.jsp">Υ����Ϣ</a>|&nbsp;
  <a href="orderInfo.jsp">ԤԼ��Ϣ</a>|&nbsp;
  <a href="historyInfo.jsp">������ʷ</a>|&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="LoginStateCl">�˳�</a>
  </td>
</tr>

