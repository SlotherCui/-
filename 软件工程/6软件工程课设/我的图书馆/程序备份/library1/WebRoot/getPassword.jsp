<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图书信息管理系统-找回密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
    <!--
	.STYLE1 {
		color: #0000FF;
		font-size: 24px;
	}
	.STYLE2 {
		color: #FF0000; 
		font-size: 14px; 
	}
	.STYLE3 {
		color: #0000FF; 
		font-size: 14px; 
	}
	-->
	</style>
  </head>
  
  <body>
    <table width="822" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="STYLE4">
	  <jsp:include page="top.jsp"></jsp:include>
	  <tr>
	    <td>
	    <div align="center">
	      <br /><span class="STYLE1">找回密码</span><br />		
	      <form id="getPassword" name="getPassword" method="post" action="GetPasswdCl">
		      用户号：<input type="text" name="user" size="25"/><br /><br />
			 问&nbsp;&nbsp;题：<input type="text" name="question" size="25"/><br /><br />
	           答&nbsp;&nbsp;案： <input name="answer" type="text" size="25" /><br><br>         
		      <input type="submit" name="Submit" value="找回密码" />
	          <input type="reset" name="reset" value="重&nbsp;&nbsp;置" /><br><br><br><br>
	    </form>
	    </div>
	    </td>
	  </tr>
	  
	</table>
  </body>
</html>
