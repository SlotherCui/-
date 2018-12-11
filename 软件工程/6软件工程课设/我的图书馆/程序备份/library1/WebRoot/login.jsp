<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userId=request.getParameter("userId");

if(userId==null){
   userId="&nbsp;";
}

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图书信息管理系统-登入界面</title>
    
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
    <script type="text/javascript">
	
	/**
	*更换验证码
	*/
	
	function changeCode(){
		document.getElementById("imgcode").src="BuildImageServlet?nocache="+new Date().getTime();
	}
	
</script>
  </head>
  
  <body>
    <table width="822" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="STYLE4">
	  <jsp:include page="top.jsp"></jsp:include>
	  <tr>
	    <td>
	    
		  <div align="center">
		    <br /><span class="STYLE1">登入我的图书馆</span><br />
		    <%
		       String type=request.getParameter("type");
		       if(type!=null){
		         if(type.equals("user")){
		            //用户号或密码错误
		     %>
		              <span class="STYLE2">用户名或密码错误</span><br>
		     <%
		         }else if(type.equals("valicatecode")){
		            //验证码错误
		      %>
		              <span class="STYLE2">验证码错误</span><br>
		      <%
		         }else{
		            //找回密码
		            	           
		      %>
		            <span class="STYLE2">密码:<%=type %></span>
		            
		      <%
		         }
		       }
		    %>		    
		    <form id="login" name="login" method="post" action="LoginCl">
		      用户号：<input type="text" name="user" size="25" value=<%=userId %> /><br /><br />
			  密&nbsp;&nbsp;码：<input type="password" name="passwd" size="25"/><br /><br />
	          验证码： <input name="validatecode" type="text" size="6" />
	          <img title="看不清，点击更换验证码" style="cursor:pointer" id="imgcode" alt="" src="BuildImageServlet" onclick="changeCode()" ><br /><br />
		      <input type="submit" name="Submit" value="登&nbsp;入" />
	          <input type="reset" name="reset" value="重&nbsp;置" />
	          <a href="getPassword.jsp" class="STYLE3">&nbsp;&nbsp;忘记密码?</a>
	        </form>
		    <p>&nbsp;</p>
		  </div></td>
	  </tr>
	</table>
  </body>
</html>
