package library.wtz.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.wtz.dao.User;
import library.wtz.util.ToolString;

public class UpdateUserInfo extends HttpServlet {

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
		
		String type=request.getParameter("type");
		String userId=(String)request.getSession().getAttribute("user");
		if(type.equals("email")){
			//修改邮箱
			String email=request.getParameter("email");
			System.out.println(email);
			new User().updateEmail(userId, email);		
		}
		else if(type.equals("QA")){
			//修改问题和答案
			String question=ToolString.getNewString(request.getParameter("question"));
			String answer=ToolString.getNewString(request.getParameter("answer"));
			System.out.println(question+"  "+answer);
			new User().updateQA(userId, question, answer);
		}else{
			//修改密码
			String passwd=request.getParameter("passwd");
			System.out.println(passwd);
			new User().updatePasswd(userId, passwd);
		}
		
		response.sendRedirect("userInfo.jsp");
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
