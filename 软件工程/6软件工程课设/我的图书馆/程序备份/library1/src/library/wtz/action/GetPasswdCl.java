package library.wtz.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.wtz.dao.User;
import library.wtz.util.*;
/**
 * ’“ªÿ√‹¬Îµƒøÿ÷∆≤„
 * @author wangtianzhi
 *
 */
public class GetPasswdCl extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String userId=ToolString.getNewString(request.getParameter("user").trim());
		String question=ToolString.getNewString(request.getParameter("question").trim());
		String answer=ToolString.getNewString(request.getParameter("answer").trim());
		String passwd=new User().getPasswd(userId, question, answer);
//		System.out.println(passwd);
		if(passwd==null){
			response.sendRedirect("getPassword.jsp");
		}else
		{
			request.setAttribute("passwd", passwd);
			response.sendRedirect("login.jsp?type="+passwd+"&userId="+userId);
		}
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
