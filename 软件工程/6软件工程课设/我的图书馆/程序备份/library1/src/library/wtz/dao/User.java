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
	 * ��֤�û�����
	 * @param user    :�û�ID
	 * @param passwd  :����
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
	 * ͨ�����⼰���һ�����
	 * @param userId    �û�ID
	 * @param question  ����
	 * @param answer    ��
	 * @return          ���������벻��ȷ����null�����򷵻�����
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
	 * ȡ���û��ĸ�����Ϣ
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
					userbean.setUser_sex("��");
				else 
					userbean.setUser_sex("Ů");
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
	 * �����û�����
	 * @param userId  :�û���
	 * @param email   :������
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
	 * �����û������
	 * @param userId   :�û���
	 * @param question :����
	 * @param answer   :��
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
	 * �����û�����
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
	 * ȡ��ָ���û���δ������
	 * @param userId  :�û���
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
				//�����ߺ����߷���һ��
				String author=rs.getString(5);
				if(author!=null&&!author.equals(""))
					borrowbean.setAuthor(rs.getString(4)+";"+author+"��");
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
	 * �鿴ָ���û��Ƿ��г���δ������
	 * @param userId  :�û�id
	 * @return        :�г���δ���ķ���true������false
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
				//�г��ڵ�
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
	 * �ж��û����ڽ��һ�����Ƿ��������
	 * @param bookId   :��ID��
	 * @return         :û���������false,���򷵻�true
	 */
	public boolean isReborrowed(int bookId){
		boolean b=true;
		//����һ����һ��ֻ�ܱ�һ���˽�ȥ�����bookId��Ψһȷ�������Ƿ�����
		String sql="select * from borrow where borrow_book_id=? and borrow_return_time is null and borrow_is_renew=1";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setInt(1, bookId);
			rs=ps.executeQuery();
			if(rs.next()){
				//��û�����
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
	 * �ж�ָ����ͼ���Ƿ�ԤԼ
	 * @param bookId
	 * @return        :û��ԤԼ����false,���򷵻�true
	 */
	public boolean isOrdered(int bookId){
		boolean b=true;
		String sql="select * from book where book_id=? and book_state='���'";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setInt(1, bookId);
			rs=ps.executeQuery();
			if(rs.next()){
				//û��ԤԼ
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
	 * ָ���û��ܽ���
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
	 * ����
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
	 * ָ���û��ܽ������,��ԤԼ���ٱ���
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
	 * ��ȡ�û����˶����飨����ԤԼ���飩
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
	 * ��ȡ�û�Υ����ʷ��¼
	 * @param userId  :�û���
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
	 * ��ȡ�û����ĵ���ʷ��¼
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
					history.setAuthor(rs.getString(3)+";"+rs.getString(4)+"��");
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
	 * ��ȡ�û�ԤԼ����Ϣ
	 * @param userId
	 * @return
	 */
	public Vector<OrderInfoBean> getOrderInfo(String userId){
		System.out.println("�û���:"+userId);
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
		System.out.println("ԤԼ��Ŀ:"+vector.size());
		return vector;
	}
	
	/**
	 * ��ȡ�û�ԤԼ�˶�����
	 * @param userId  :�û���
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
	 * ִ��ԤԼͼ�飬��order_info���������ӦԤԼ��Ϣ
	 * ����book���Ӧ���״̬��Ϊ��ԤԼ�ɴ������Զ�ִ�У�
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
	 * ȡ��ԤԼͼ��
	 * @param bookId
	 */
	public void cancelOrder(int bookId,String bookState){
		Statement sm;
		ConnDB conn=new ConnDB();
		String sql="delete from order_info where order_book_id="+bookId;
		String sql1;
		if(bookState.equals("��ԤԼ")){
			sql1="update book set book_state='���' where book_id="+bookId;
		}else{
			//ԤԼ����
			sql1="update book set book_state='�ڹ�' where book_id="+bookId;
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
	 * �ر���Դ
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
