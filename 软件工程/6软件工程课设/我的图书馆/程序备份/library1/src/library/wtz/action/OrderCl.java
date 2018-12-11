package library.wtz.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.wtz.dao.User;
import library.wtz.form.PurviewBean;
/**
 * ԤԼͼ�鴦��
 * @author wangtianzhi
 *
 */
public class OrderCl extends HttpServlet {

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
		String userId=(String)request.getSession().getAttribute("user");
		int bookId=Integer.parseInt(request.getParameter("bookId"));
		if(userId==null){
			response.sendRedirect("login.jsp");
		}else{
			User user=new User();
			PurviewBean purview=user.getPurview(userId);
			int borrowedNum=user.getBorrowNum(userId);
			if(borrowedNum>=purview.getBookNum()){
				//�Ѿ�����������ԤԼ
				response.sendRedirect("showDetail.jsp?bookId="+bookId+"&state=overBorrowNum");
			}else if(user.isOvertime(userId)){
				//�г���δ�����飬����ԤԼ
				response.sendRedirect("showDetail.jsp?bookId="+bookId+"&state=overTime");
			}else if(user.OrderedNum(userId)>=purview.getOrderNum()){
				//����ԤԼ����������ԤԼ
				response.sendRedirect("showDetail.jsp?bookId="+bookId+"&state=overOrderNum");
			}else{
				//�ﵽԤԼ����������ԤԼ
				user.orderBook(userId, bookId);
				response.sendRedirect("showDetail.jsp?bookId="+bookId+"&state=success");
				
			}
			
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
