<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user=(String)request.getSession().getAttribute("user");
if(user==null)
   response.sendRedirect("login.jsp");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图书信息管理系统-我的图书馆</title>
    
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
	
    a{text-decoration:none;}

	-->
	</style>
  </head>

	<body>
		<table width="822" border="0" align="center" cellpadding="0"
			cellspacing="0" bgcolor="#FFFFFF">
			<jsp:include page="myLibraryTop.jsp"></jsp:include>
			<tr>
				<td>
					<center>
						<h3><br>
							欢&nbsp;迎&nbsp;使&nbsp;用&nbsp;我&nbsp;的&nbsp;图&nbsp;书&nbsp;馆
						</h3>
					</center>
					<ol>
						<li>
							个人信息使用指南<br>
							<font size=2>查询个人信息，并可更改个人邮箱、忘记密码时用来找回密码的问题和答案、密码。</font>
						</li>
						
						<li>
							书刊借阅使用指南<br>
							<font size=2>查看借阅未还的书籍，并可续借所借图书。</font>
						</li>
						<li>
							违章信息使用指南<br>
							<font size=2>查看违章历史记录</font>
						</li>
						<li>
							预约信息使用指南<br>
							<font size=2>查看个人预约图书信息，预约的图书是否到馆，取消取消预约等功能。</font>
						</li>
						<li>
							借阅历史使用指南<br>
							<font size=2>查看个人借阅历史。</font>
						</li>
						<li>
							退出我的图书馆<br>
							<font size=2>退出我的图书馆，返回到图书检索界面。</font>
						</li>
					</ol>
				</td>
			</tr>
		</table>
	</body>
</html>
