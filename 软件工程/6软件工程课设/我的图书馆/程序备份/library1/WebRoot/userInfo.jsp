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
    
    <title>ͼ����Ϣ����ϵͳ-������Ϣ</title>
    
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
    	   //��֤�����ʽ
    	   var newemail=document.getElementById("email").value;
    	   var exp=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    	   if(newemail!=""&&!exp.test(newemail)){
    	   	  alert("�����ʽ����ȷ");
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
    	       alert("���벻��Ϊ�գ�����������");
    	       document.getElementById("passwd").focus();
    	   }
    	   else if(isNaN(passwd)){
    	       alert("����ֻ��Ϊ����");
    	       document.getElementById("passwd").focus();
    	   }
    	   else if(passwd!=passwd1){
    	       alert("�����������벻һ��");
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
	  		<td>�û���:<%=userbean.getUser_id() %></td>
	  		<td>����:<%=userbean.getUser_name() %></td>
	  		<td>��������:<%=userbean.getUser_birthday() %></td>
	  	</tr>
	  	<tr>
	  		<td>�Ա�:<%=userbean.getUser_sex() %></td>
	  		<td>����:<%=userbean.getUser_question() %></td>
	  		<td>��:<%=userbean.getUser_answer() %></td>
	  	</tr>
	  	<tr>
	  	    <td>ְλ:<%=userbean.getUser_purview_id() %></td>
	  	    <td>Ժϵ:<%=userbean.getUser_dept() %></td>
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
			    	<button onclick="changeEmail()">�޸�E_mail</button>
			    </td>
			</tr>
			<tr>
				<td>
					����:
				</td>
				<td>
					<input type="text" name="question" size="30" value=<%=userbean.getUser_question() %> />
				</td>		    
			</tr>
			<tr>
				<td>
					��:
				</td>
				<td>
					<input type="text" name="answer" size="30" value=<%=userbean.getUser_answer() %> />
				</td>
			    <td>
			    	<button onclick="changeQA()">�޸������</button>
			    </td>			    
			</tr>
			<tr>
				<td>
					������:
				</td>
				<td>
					<input type="password" name="passwd" id="passwd" size="32" />
				</td>			
			</tr>
			<tr>
				<td>
					ȷ��������:
				</td>
				<td>
					<input type="password" name="passwd1" id="passwd1" size="32" />
				</td>	
				<td>
					<button onclick="changePasswd()">�޸�����</button>
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
