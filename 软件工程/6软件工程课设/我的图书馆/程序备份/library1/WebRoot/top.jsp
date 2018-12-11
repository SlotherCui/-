<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user=(String)request.getSession().getAttribute("user");
String url;
String button;
if(user==null){
   url="login.jsp";
   button="登入";
}else{
   url="myLibrary.jsp";
   button="退出";
}
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
    <td height="24" background="images/theme6.jpg"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr background="images/theme6.jpg">
				<td width="13%">
					<a href="index.jsp">高级检索</a>
				</td>
				<td width="12%">
					<a href=<%=url%>>我的图书馆</a>
				</td>
				<td width="75%" align="right">
					<a href="LoginStateCl"><%=button%></a>&nbsp;
				</td>
			</tr>
		</table></td>
  </tr>
