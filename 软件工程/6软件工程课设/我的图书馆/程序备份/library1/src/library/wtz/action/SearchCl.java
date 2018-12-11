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
import javax.servlet.http.HttpSession;

import library.wtz.dao.GetBookInfo;
import library.wtz.form.BookBean;
import library.wtz.util.*;
public class SearchCl extends HttpServlet {

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
		
		String key=ToolString.getNewString(request.getParameter("textfield"));
		String language=ToolString.getNewString(request.getParameter("language"));
		String method=ToolString.getNewString(request.getParameter("method"));
		String model=ToolString.getNewString(request.getParameter("model"));
		String page=request.getParameter("pageSize").trim();
		Matcher number=Pattern.compile("(\\d{"+page.length()+"})").matcher(page);
		int pageSize=10;
		if(!page.equals("")&&number.matches())
			pageSize=Integer.parseInt(page);		
		String libId=ToolString.getNewString(request.getParameter("library"));
		String ordermethod=ToolString.getNewString(request.getParameter("ordermethod"));
		String order=request.getParameter("order");
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
		int count=new GetBookInfo().getPageCount(method, key, language, model, libId);
		Vector<BookBean> vec=new GetBookInfo().getBook(pageSize, pageNow, method, key, language, model, libId, ordermethod, order);
		int pageCount=0;
		//计算总共需要多少页
		if(count%pageSize==0)
			pageCount=count/pageSize;
		else 
			pageCount=count/pageSize+1;
		System.out.println("count="+count);
//		System.out.println("pagecount="+pageCount);
//		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageNow", pageNow);
		HttpSession session=request.getSession(true);
		session.setAttribute("pageCount", pageCount);
		session.setAttribute("key", key);
		session.setAttribute("language", language);
		session.setAttribute("model", model);
		session.setAttribute("method", method);
		session.setAttribute("libId", libId);
		session.setAttribute("ordermethod", ordermethod);
		session.setAttribute("order", order);
		session.setAttribute("pageSize", pageSize);
		session.setAttribute("count", count);
		
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
