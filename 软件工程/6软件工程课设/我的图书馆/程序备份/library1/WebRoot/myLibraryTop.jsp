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
  <a href="myLibrary.jsp">首页</a> |&nbsp;
  <a href="index.jsp">搜索</a>|&nbsp;
  <a href="userInfo.jsp">个人信息</a>|&nbsp;
  <a href="borrowInfo.jsp">书刊借阅</a>|&nbsp;
  <a href="durtyInfo.jsp">违章信息</a>|&nbsp;
  <a href="orderInfo.jsp">预约信息</a>|&nbsp;
  <a href="historyInfo.jsp">借阅历史</a>|&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="LoginStateCl">退出</a>
  </td>
</tr>

