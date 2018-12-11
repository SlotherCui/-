package library.wtz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.wtz.dao.GetBookInfo;
import library.wtz.form.BookBean;
import library.wtz.util.ToolString;

public class SearchCl1 extends HttpServlet {

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
		
		String key=(String)request.getSession().getAttribute("key");
		String language=(String)request.getSession().getAttribute("language");
		String method=(String)request.getSession().getAttribute("method");
		String model=(String)request.getSession().getAttribute("model");
		int pageSize=(Integer)request.getSession().getAttribute("pageSize");	
		String libId=(String)request.getSession().getAttribute("libId");
		String ordermethod=(String)request.getSession().getAttribute("ordermethod");
		String order=(String)request.getSession().getAttribute("order");
		
		String s_pageNow=request.getParameter("pageNow");
		int pageNow=1;
		if(s_pageNow!=null&&!s_pageNow.equals(""))
			pageNow=Integer.parseInt(s_pageNow);
		System.out.println("key:"+key);
		System.out.println("language:"+language);
		System.out.println("method:"+method);
		System.out.println("model:"+model);
		System.out.println("pageSize:"+pageSize);
		System.out.println("library:"+libId);
		System.out.println("ordermethod:"+ordermethod);
		System.out.println("order:"+order);
		
		Vector<BookBean> vec=new GetBookInfo().getBook(pageSize, pageNow, method, key, language, model, libId, ordermethod, order);
		
		
//		System.out.println("pagecount="+pageCount);
		
		request.setAttribute("pageNow", pageNow);
		
		request.setAttribute("result", vec);
		request.getRequestDispatcher("result.jsp").forward(request, response);
		
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
