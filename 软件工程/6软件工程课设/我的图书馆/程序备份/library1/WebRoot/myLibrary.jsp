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
    
    <title>ͼ����Ϣ����ϵͳ-�ҵ�ͼ���</title>
    
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
							��&nbsp;ӭ&nbsp;ʹ&nbsp;��&nbsp;��&nbsp;��&nbsp;ͼ&nbsp;��&nbsp;��
						</h3>
					</center>
					<ol>
						<li>
							������Ϣʹ��ָ��<br>
							<font size=2>��ѯ������Ϣ�����ɸ��ĸ������䡢��������ʱ�����һ����������ʹ𰸡����롣</font>
						</li>
						
						<li>
							�鿯����ʹ��ָ��<br>
							<font size=2>�鿴����δ�����鼮��������������ͼ�顣</font>
						</li>
						<li>
							Υ����Ϣʹ��ָ��<br>
							<font size=2>�鿴Υ����ʷ��¼</font>
						</li>
						<li>
							ԤԼ��Ϣʹ��ָ��<br>
							<font size=2>�鿴����ԤԼͼ����Ϣ��ԤԼ��ͼ���Ƿ񵽹ݣ�ȡ��ȡ��ԤԼ�ȹ��ܡ�</font>
						</li>
						<li>
							������ʷʹ��ָ��<br>
							<font size=2>�鿴���˽�����ʷ��</font>
						</li>
						<li>
							�˳��ҵ�ͼ���<br>
							<font size=2>�˳��ҵ�ͼ��ݣ����ص�ͼ��������档</font>
						</li>
					</ol>
				</td>
			</tr>
		</table>
	</body>
</html>
