package library.wtz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import library.wtz.form.*;

public class User {
	private PreparedStatement ps = null;
	private ResultSet rs=null;
	private Connection ct=null;
	/**
	 * 验证用户登入
	 * @param user    :用户ID
	 * @param passwd  :密码
	 * @return
	 */
	public boolean login(String user,String passwd){
		boolean b=false;
		ct=new ConnDB().getConn();
		String sql="select * from user_info where user_id=? and user_passwd=?";
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, passwd);
			rs=ps.executeQuery();
			if(rs.next()){
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return b;
	}
	/**
	 * 通过问题及答案找回密码
	 * @param userId    用户ID
	 * @param question  问题
	 * @param answer    答案
	 * @return          若问题密码不正确返回null，否则返回密码
	 */
	public String getPasswd(String userId,String question,String answer){
		String passwd=null;
//		System.out.println(userId+"  "+question+"  "+answer);
		String sql="select user_passwd from user_info where user_id=? and user_question=? and user_answer=?";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, question);
			ps.setString(3, answer);
			rs=ps.executeQuery();
			if(rs.next()){
				passwd=rs.getString(1);
//				System.out.println("not null");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return passwd;
	}
	
	/**
	 * 取得用户的个人信息
	 * @param userId
	 * @return
	 */
	public UserBean getUserInfo(String userId){
		UserBean userbean=new UserBean();
		try {
			ct=new ConnDB().getConn();
			ps=ct.prepareStatement("select user_id,user_name,user_passwd,user_birthday," +
					"user_sex,user_question,user_answer,purview_name,user_dept,user_email from" +
					" user_info,purview where user_id=? and user_purview_id=purview_id");
			ps.setString(1, userId);
			rs=ps.executeQuery();

			if(rs.next()){
				userbean.setUser_id(rs.getString(1));
				userbean.setUser_name(rs.getString(2));
				userbean.setUser_passwd(rs.getString(3));
				userbean.setUser_birthday(rs.getString(4));
				if(rs.getString(5).equals("0"))
					userbean.setUser_sex("男");
				else 
					userbean.setUser_sex("女");
				userbean.setUser_question(rs.getString(6));
				userbean.setUser_answer(rs.getString(7));
				userbean.setUser_purview_id(rs.getString(8));
				userbean.setUser_dept(rs.getString(9));
				userbean.setUser_email(rs.getString(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return userbean;
	}
	
	/**
	 * 更新用户邮箱
	 * @param userId  :用户号
	 * @param email   :新邮箱
	 */
	public void updateEmail(String userId,String email){
		String sql="update user_info set user_email=? where user_id=?";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, userId);
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
	}
	
	/**
	 * 更新用户问题答案
	 * @param userId   :用户号
	 * @param question :问题
	 * @param answer   :答案
	 */
	public void updateQA(String userId,String question,String answer){
		String sql="update user_info set user_question=?,user_answer=? where user_id=?";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, question);
			ps.setString(2, answer);
			ps.setString(3, userId);
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
	}
	
	/**
	 * 更新用户密码
	 * @param userId
	 * @param passwd
	 */
	public void updatePasswd(String userId,String passwd){
		String sql="update user_info set user_passwd=? where user_id=?";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, passwd);
			ps.setString(2, userId);			
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
	}
	
	/**
	 * 取得指定用户还未还的书
	 * @param userId  :用户号
	 * @return
	 */
	public Vector<BorrowInfoBean> getBorrowBook(String userId){
		Vector<BorrowInfoBean> vector=new Vector<BorrowInfoBean>();
		String sql="select book_id,book_isbn,book_name,book_author,book_translator,lib_name,borrow_time,borrow_restrict_time"+
        " from borrow,book,library where borrow_book_id=book_id and borrow_user_id=? and"+ 
        " borrow_return_time is null and book_lib_id=lib_id";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, userId);
			rs=ps.executeQuery();
			while(rs.next()){
				BorrowInfoBean borrowbean=new BorrowInfoBean();
				borrowbean.setId(rs.getInt(1));
				borrowbean.setIsbn(rs.getString(2));
				borrowbean.setName(rs.getString(3));
				//将作者和译者放在一起
				String author=rs.getString(5);
				if(author!=null&&!author.equals(""))
					borrowbean.setAuthor(rs.getString(4)+";"+author+"译");
				else
					borrowbean.setAuthor(rs.getString(4));
				borrowbean.setLibrary(rs.getString(6));
				borrowbean.setBorrowTime(rs.getDate(7));
				borrowbean.setRestrictTime(rs.getDate(8));
				vector.add(borrowbean);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return vector;
	}
	
	/**
	 * 查看指定用户是否有超期未还的书
	 * @param userId  :用户id
	 * @return        :有超期未还的返回true，否则false
	 */
	public boolean isOvertime(String userId){
		boolean b=false;
		String sql="select * from borrow where borrow_user_id=? and borrow_return_time is null and datediff(day,borrow_restrict_time,getdate())>0";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, userId);
			rs=ps.executeQuery();
			if(rs.next()){
				//有超期的
				b=true;
//				System.out.println(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	
	/**
	 * 判断用户正在借的一本书是否有续借过
	 * @param bookId   :书ID号
	 * @return         :没续借过返回false,否则返回true
	 */
	public boolean isReborrowed(int bookId){
		boolean b=true;
		//根据一本书一次只能被一个人借去，因此bookId能唯一确定此书是否被续借
		String sql="select * from borrow where borrow_book_id=? and borrow_return_time is null and borrow_is_renew=1";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setInt(1, bookId);
			rs=ps.executeQuery();
			if(rs.next()){
				//还没续借过
				b=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	
	/**
	 * 判断指定的图书是否被预约
	 * @param bookId
	 * @return        :没被预约返回false,否则返回true
	 */
	public boolean isOrdered(int bookId){
		boolean b=true;
		String sql="select * from book where book_id=? and book_state='借出'";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setInt(1, bookId);
			rs=ps.executeQuery();
			if(rs.next()){
				//没被预约
				b=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	
	/**
	 * 指定用户能借多久
	 * @param userId
	 * @return
	 */
	public int borrowTime(String userId){
		int time=0;
		System.out.println("borrowTime:"+userId);
		String sql="select book_time from purview,user_info where user_id=? and user_purview_id=purview_id";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, userId);
			rs=ps.executeQuery();
			if(rs.next()){
				System.out.println("borrowTime ok");
			}
			time=rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return time;
	}
	
	/**
	 * 续借
	 * @param bookId
	 * @param userId
	 */
	public void reBorrow(int bookId,String userId){
		
		int borrowTime=this.borrowTime(userId);
		String sql="update borrow set borrow_restrict_time=dateadd(day,?,borrow_restrict_time),borrow_is_renew=0 where"+ 
                    " borrow_return_time is null and borrow_book_id=?";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setInt(1, borrowTime);
			ps.setInt(2, bookId);
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
	}
	/**
	 * 指定用户能借多少书,能预约多少本书
	 * @param userId
	 * @return
	 */
	public PurviewBean getPurview(String userId){
		PurviewBean purview=new PurviewBean();
		String sql="select book_num,order_num from purview,user_info where user_id=? and user_purview_id=purview_id";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, userId);
			rs=ps.executeQuery();
			if(rs.next()){
				purview.setBookNum(rs.getInt(1));
				purview.setOrderNum(rs.getInt(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return purview;
	}
	/**
	 * 获取用户借了多少书（包括预约的书）
	 * @param userId
	 * @return
	 */
	public int getBorrowNum(String userId){
		int num=0;
		String sql="select count(*)+(select count(*) from order_info where order_user_id=?) from borrow"+ 
        " where borrow_user_id=? and borrow_return_time is null";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, userId);
			rs=ps.executeQuery();
			if(rs.next()){
				num=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return num;
	}
	
	/**
	 * 获取用户违章历史记录
	 * @param userId  :用户号
	 * @return
	 */
	public Vector<DurtyInfoBean> getDurtyInfo(String userId){
		Vector<DurtyInfoBean> vector=new Vector<DurtyInfoBean>();
		String sql="select book_id,book_name,borrow_time,borrow_return_time,durty_money,durty_describtion from book,durty,borrow"+
		" where borrow_user_id=? and borrow_id=durty_borrow_id and book_id=borrow_book_id ";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, userId);
			rs=ps.executeQuery();
			while(rs.next()){
				DurtyInfoBean durty=new DurtyInfoBean();
				durty.setBookId(rs.getInt(1));
				durty.setBookName(rs.getString(2));
				durty.setBorrowTime(rs.getDate(3));
				durty.setReturnTime(rs.getDate(4));
				durty.setMoney(rs.getLong(5));
				durty.setDescribtion(rs.getString(6));
				vector.add(durty);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return vector;
	}
	
	/**
	 * 获取用户借阅的历史记录
	 * @param userId
	 * @return
	 */
	public Vector<BorrowHistoryBean> getBorrowHistory(String userId){
		Vector<BorrowHistoryBean> vector=new Vector<BorrowHistoryBean>();
		String sql="select book_id,book_name,book_author,book_translator,lib_name,borrow_time,borrow_return_time from"+ 
			" borrow,book,library where borrow_user_id=? and borrow_return_time is not null"+ 
			" and borrow_book_id=book_id and book_lib_id=lib_id";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, userId);
			rs=ps.executeQuery();
			while(rs.next()){
				BorrowHistoryBean history=new BorrowHistoryBean();
				history.setBookId(rs.getInt(1));
				history.setBookName(rs.getString(2));
				if(rs.getString(4)==null)
					history.setAuthor(rs.getString(3));
				else
					history.setAuthor(rs.getString(3)+";"+rs.getString(4)+"译");
				history.setLibrary(rs.getString(5));
				history.setBorrowTime(rs.getDate(6));
				history.setReturnTime(rs.getDate(7));
				vector.add(history);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return vector;
	}
	
	/**
	 * 获取用户预约的信息
	 * @param userId
	 * @return
	 */
	public Vector<OrderInfoBean> getOrderInfo(String userId){
		System.out.println("用户号:"+userId);
		Vector<OrderInfoBean> vector=new Vector<OrderInfoBean>();
		String sql="select book_id,book_name,lib_name,book_state,order_restrict_time,order_return_time from order_info,book,library where order_user_id=? and order_book_id=book_id and book_lib_id=lib_id";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, userId);
			rs=ps.executeQuery();
			while(rs.next()){
				OrderInfoBean order=new OrderInfoBean();
				order.setBookId(rs.getInt(1));
				order.setBookName(rs.getString(2));
				order.setLibrary(rs.getString(3));
				order.setState(rs.getString(4));
				order.setRestrictedTime(rs.getDate(5));
				order.setReturnTime(rs.getDate(6));
				vector.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		System.out.println("预约数目:"+vector.size());
		return vector;
	}
	
	/**
	 * 获取用户预约了多少书
	 * @param userId  :用户号
	 * @return        
	 */
	public int OrderedNum(String userId){
		int orderedNum=0;
		String sql="select count(*) from order_info where order_user_id=?";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setString(1, userId);
			rs=ps.executeQuery();
			if(rs.next()){
				//
				orderedNum=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return orderedNum;
		
	}
	
	/**
	 * 执行预约图书，往order_info表里插入相应预约信息
	 * （将book表对应书的状态改为被预约由触发器自动执行）
	 * @param userId
	 * @param bookId
	 */
	public void orderBook(String userId,int bookId){
		String sql="insert into order_info(order_book_id,order_user_id,order_restrict_time)"+ 
        "values(?,?,(select borrow_restrict_time from borrow where borrow_book_id=? and borrow_return_time is null))";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setString(2, userId);
			ps.setInt(3, bookId);
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
	}
	
	/**
	 * 取消预约图书
	 * @param bookId
	 */
	public void cancelOrder(int bookId,String bookState){
		Statement sm;
		ConnDB conn=new ConnDB();
		String sql="delete from order_info where order_book_id="+bookId;
		String sql1;
		if(bookState.equals("被预约")){
			sql1="update book set book_state='借出' where book_id="+bookId;
		}else{
			//预约到馆
			sql1="update book set book_state='在馆' where book_id="+bookId;
		}
		try {
			sm=conn.getStatement();
			sm.addBatch(sql);
			sm.addBatch(sql1);
			int result[]=sm.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			conn.closeStatement();
			conn.close();
		}
	}
	
	/**
	 * 关闭资源
	 */
	public void close(){
		
		try {
			
			if(rs!=null){
				
				rs.close();
				rs=null;//
			}
			if(ps!=null){
				
				ps.close();
				ps=null;
			}
			
			if(!ct.isClosed()){
				
				ct.close();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
