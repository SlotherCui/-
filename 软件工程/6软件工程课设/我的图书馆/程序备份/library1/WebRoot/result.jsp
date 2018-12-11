<%@ page language="java" import="java.util.*,library.wtz.form.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
HttpSession sess=request.getSession(true);
int pageCount=(Integer)sess.getAttribute("pageCount");
int count=(Integer)sess.getAttribute("count");
String method=(String)sess.getAttribute("method");
String key=(String)sess.getAttribute("key");
//String s_pageNow=(String)request.getAttribute("pageNow");
int pageNow=(Integer)request.getAttribute("pageNow");

//if(s_pageNow!=null)
//	pageNow=Integer.parseInt(s_pageNow);
Vector<BookBean> vec=(Vector<BookBean>)request.getAttribute("result");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>图书信息管理系统</title>
    
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
	.STYLE4 {font-size: 14px}
    a{text-decoration:none;}

	-->
	</style>
  </head>
  
  <body>
    <table width="822" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF" class="STYLE4">
	  <jsp:include page="top.jsp"></jsp:include>
	  <tr>
	    <td><span >&nbsp;&nbsp;&nbsp; 检索条件：<%=method %>=<%=key %> &nbsp; 检索结果数：<%=count %>&nbsp;&nbsp;&nbsp; 
	        <label>
	        <input type="submit" name="Submit" value="只显示可借阅图书" />
	        </label>
	    </span></td>
	  </tr>
	  <tr>
	    <td height="56"><table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="10" class="STYLE4">
	      
	     <% 
	       for(int j=0;j<vec.size();j++){
	         BookBean book=vec.get(j);
	     %>
	         <tr>
	            <td><a href="showDetail.jsp?bookId=<%=book.getId() %>"><%=book.getName() %></a></td>
	            <td><a href="showDetail.jsp?bookId=<%=book.getId() %>"><%=book.getAuthor() %></a></td>
	            <td><a href="showDetail.jsp?bookId=<%=book.getId() %>"><%=book.getPress() %></a></td>
	            <td><a href="showDetail.jsp?bookId=<%=book.getId() %>"><%=book.getLanguage() %></a></td>
	            <td><a href="showDetail.jsp?bookId=<%=book.getId() %>"><%=book.getState() %></a></td>
	         </tr>
	     <%
	       }
	       
	     %>
	    </table>      
	    </td>
	  </tr>
	  <tr>
	    <td><center><span >&nbsp;&nbsp;&nbsp;<a href="SearchCl1?pageNow=1">首页</a>&nbsp;&nbsp; 
	    <%if(pageNow!=1){ %>
	    <a href="SearchCl1?pageNow=<%=pageNow-1 %>">上一页</a>
	    <%} 
	     for(int i=1;i<=pageCount;i++){	        
	    %>
	     <a href="SearchCl1?pageNow=<%=i %>">[<%=i %>]</a>
	    <%
	    }
	    %>
	    &nbsp;&nbsp; 
	    <%if(pageNow!=pageCount){ %>
	    <a href="SearchCl1?pageNow=<%=pageNow+1 %>">下一页</a>
	    <%} %>
	    &nbsp;&nbsp; <a href="SearchCl1?pageNow=<%=pageCount %>">尾页</a> </span></center></td>
	  </tr>
	</table>
  </body>
</html>
