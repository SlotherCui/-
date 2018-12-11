<%@ page language="java" import="java.util.*,library.wtz.dao.*,library.wtz.form.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user=(String)session.getAttribute("user");
UserBean userbean=new User().getUserInfo(user);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图书信息管理系统-个人信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript">
    	function changeEmail(){
    	   //验证邮箱格式
    	   var newemail=document.getElementById("email").value;
    	   var exp=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    	   if(newemail!=""&&!exp.test(newemail)){
    	   	  alert("邮箱格式不正确");
    	   	  document.getElementById("email").focus();
    	   }else{
    	   	  document.forms.update.action="UpdateUserInfo?type=email";
    	      document.forms.update.submit();
    	   } 
    	}
    	function changeQA(){
    	   document.forms.update.action="UpdateUserInfo?type=QA";
    	   document.forms.update.submit();
    	}
    	function changePasswd(){
    	   var passwd=document.getElementById("passwd").value;
    	   var passwd1=document.getElementById("passwd1").value;
    	   
    	   if(passwd==null||passwd==""){
    	       alert("密码不能为空，请输入密码");
    	       document.getElementById("passwd").focus();
    	   }
    	   else if(isNaN(passwd)){
    	       alert("密码只能为数字");
    	       document.getElementById("passwd").focus();
    	   }
    	   else if(passwd!=passwd1){
    	       alert("两次输入密码不一致");
    	       document.getElementById("passwd1").focus();
    	   }else{
	    	   document.forms.update.action="UpdateUserInfo?type=passwd";
	    	   document.forms.update.submit();
    	   }
    	}
    </script>
  </head>
  
  <body>
    <table width="822" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
	  <jsp:include page="myLibraryTop.jsp"></jsp:include>
	  
	  <tr>
	  	<td><table align="center" cellpadding="10" cellspacing="10">
	  	<tr>
	  		<td>用户号:<%=userbean.getUser_id() %></td>
	  		<td>姓名:<%=userbean.getUser_name() %></td>
	  		<td>出生日期:<%=userbean.getUser_birthday() %></td>
	  	</tr>
	  	<tr>
	  		<td>性别:<%=userbean.getUser_sex() %></td>
	  		<td>问题:<%=userbean.getUser_question() %></td>
	  		<td>答案:<%=userbean.getUser_answer() %></td>
	  	</tr>
	  	<tr>
	  	    <td>职位:<%=userbean.getUser_purview_id() %></td>
	  	    <td>院系:<%=userbean.getUser_dept() %></td>
	  	    <td>E_mail:<%=userbean.getUser_email() %></td>
	  	</tr>
	  	</table>
	  	</td>  
	  </tr>
	  <tr>
	  	<td>
	  	<form id="update" action="" method="post">
	  	<table align="center" cellpadding="10" cellspacing="0">
	  	    
		  	<tr>
		  		<td>
		  			E_mail:
		  		</td>
		  		<td>
		  			<input type="text" name="email" id="email" size="30" value=<%=userbean.getUser_email() %> />
		  		</td>
			    <td>
			    	<button onclick="changeEmail()">修改E_mail</button>
			    </td>
			</tr>
			<tr>
				<td>
					问题:
				</td>
				<td>
					<input type="text" name="question" size="30" value=<%=userbean.getUser_question() %> />
				</td>		    
			</tr>
			<tr>
				<td>
					答案:
				</td>
				<td>
					<input type="text" name="answer" size="30" value=<%=userbean.getUser_answer() %> />
				</td>
			    <td>
			    	<button onclick="changeQA()">修改问题答案</button>
			    </td>			    
			</tr>
			<tr>
				<td>
					新密码:
				</td>
				<td>
					<input type="password" name="passwd" id="passwd" size="32" />
				</td>			
			</tr>
			<tr>
				<td>
					确认新密码:
				</td>
				<td>
					<input type="password" name="passwd1" id="passwd1" size="32" />
				</td>	
				<td>
					<button onclick="changePasswd()">修改密码</button>
				</td>		
			</tr>
		  </table>
		  </form>
	  	</td>
	    
	  </tr>
	  <tr>
	  <td><br><br><br>
	  </td>
	  </tr>
	</table>
  </body>
</html>
