package library.wtz.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.wtz.dao.User;

public class LoginCl extends HttpServlet {

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
		String user=request.getParameter("user").trim();
		String passwd=request.getParameter("passwd").trim();
		String validateCode=(String)request.getSession().getAttribute("VerifyCode");
		String inputCode=request.getParameter("validatecode").trim();
		System.out.println("valicateCode:"+validateCode);
		System.out.println("inputCode:"+inputCode);
		System.out.println("user:"+user);
		System.out.println("passwd:"+passwd);
		if(validateCode.equalsIgnoreCase(inputCode)){
			if(new User().login(user, passwd)){
				//登入成功
				HttpSession session=request.getSession(true);
				session.setAttribute("user", user);
//				session.setMaxInactiveInterval(30);
				response.sendRedirect("myLibrary.jsp");
			}else{
				response.sendRedirect("login.jsp?type=user&userId="+user);
//				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else{
			//验证码不正确
			response.sendRedirect("login.jsp?type=valicatecode&userId="+user);
//			request.getRequestDispatcher("login.jsp").forward(request, response);
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
