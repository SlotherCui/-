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
			//�г���δ�����飬��������
			response.sendRedirect("borrowInfo.jsp?type=overtime&bookId="+bookId);
		}
		else if(user.isReborrowed(bookId)){
			//�Ѿ������������������
			System.out.println("�Ѿ������������������");
			response.sendRedirect("borrowInfo.jsp?type=reborrowed&bookId="+bookId);
		}else if(user.isOrdered(bookId)){
			//�Ѿ���ԤԼ����������
			System.out.println("�ѱ�ԤԼ");
			response.sendRedirect("borrowInfo.jsp?type=ordered&bookId="+bookId);
		}else{
			//�ﵽ��������
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
