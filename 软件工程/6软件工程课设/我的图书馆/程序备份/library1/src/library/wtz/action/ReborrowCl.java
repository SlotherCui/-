package library.wtz.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.wtz.dao.User;

public class ReborrowCl extends HttpServlet {

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
		int bookId=Integer.parseInt(request.getParameter("bookId"));
		String userId=(String)request.getSession().getAttribute("user");
		System.out.println("bookId:"+bookId);
		User user=new User();
		if(user.isOvertime(userId)){
			//有超期未还的书，不能续借
			response.sendRedirect("borrowInfo.jsp?type=overtime&bookId="+bookId);
		}
		else if(user.isReborrowed(bookId)){
			//已经续借过，不能再续借
			System.out.println("已经续借过，不能再续借");
			response.sendRedirect("borrowInfo.jsp?type=reborrowed&bookId="+bookId);
		}else if(user.isOrdered(bookId)){
			//已经被预约，不能续借
			System.out.println("已被预约");
			response.sendRedirect("borrowInfo.jsp?type=ordered&bookId="+bookId);
		}else{
			//达到续借条件
			user.reBorrow(bookId, userId);
			response.sendRedirect("borrowInfo.jsp");
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
