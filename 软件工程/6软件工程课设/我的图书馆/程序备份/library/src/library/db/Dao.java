package library.db;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import library.vo.*;
/**
 * 对数据库数据的增删改查
 * @author wangtianzhi
 *
 */
public class Dao {
	private ConnDB d;
	public Dao(){
		d=new ConnDB();
	}
	//关闭连接
	public void close(){
		d.close();
	}
	/**
	 * 获得管理员的信息
	 * @param id
	 * @return
	 */
	public LibrarianBean getLibrarian(String id){
		String sql="select * from librarian where librarian_id=?";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				LibrarianBean lb=new LibrarianBean();
				lb.setLibrarian_id(rs.getString(1));
				lb.setLibrarian_name(rs.getString(2));
				lb.setLibrarian_sex(rs.getString(3));
				lb.setLibrarian_bir(rs.getString(4));
				lb.setLibrarian_passwd(rs.getString(5));
				lb.setLibrarian_email(rs.getString(6));
				lb.setLibrarian_tel(rs.getString(7));
				lb.setLibrarian_work_place(rs.getString(8));
				return lb;
			}else 
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.close();
		}
	}
	
	
	/**
	 * 更新图书管理员的邮箱
	 * @param id
	 * @param mail
	 * @return
	 */
	public int updateLibrarianMail(String id,String mail){
		String sql="update librarian set librarian_email=? where librarian_id=?";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, mail);
			ps.setString(2, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	public int updateLibrarianTel(String id,String tel){
		String sql="update librarian set librarian_tel=? where librarian_id=?";
		PreparedStatement ps;
		try {
			ps = d.getConn().prepareStatement(sql);
			ps.setString(1, tel);
			ps.setString(2, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}		
	}
	/**
	 * 更新指定图书管理员的密码
	 * @param id
	 * @param pw
	 * @return
	 */
	public int updateLibrarianPW(String id,String pw){
		String sql="update librarian set librarian_passwd=? where librarian_id=?";
		try {
			PreparedStatement ps = d.getConn().prepareStatement(sql);
			ps.setString(1, pw);
			ps.setString(2, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 还书过程中根据输入的用户号查找用户所借阅的图书
	 * @return
	 */
	public List backInfo(String userId){
		List list=new ArrayList();
		String sql="select borrow_id,borrow_book_id,book_isbn,book_name,borrow_time,borrow_restrict_time,lib_name "+ 
        "from borrow,book,library where borrow_user_id=? and borrow.borrow_book_id=book.book_id "+ 
        "and book.book_lib_id=library.lib_id and borrow_return_time is null";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Back back=new Back();
				back.setBorrowId(rs.getInt(1));
				back.setBookId(rs.getInt(2));
				back.setBookIsbn(rs.getString(3));
				back.setBookName(rs.getString(4));
				back.setBorrowTime(rs.getDate(5));
				back.setReturnTime(rs.getDate(6));
				back.setLibraryName(rs.getString(7));
				list.add(back);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 判断指定的图书是否被预约
	 * @param bookId
	 * @return        :没被预约返回false,否则返回true
	 */
	public boolean isOrdered(int bookId){
		boolean b=true;
		String sql="select * from book where book_id=? and book_state='借出'";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setInt(1, bookId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				//没被预约
				b=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return b;
	}
	
	/**
	 * 归还图书更改数据库数据
	 * 1.更新borrow表归还时间
	 * 
	 * @param list  
	 */
	public void sendBack(List list,List<Durty> durty){
		Statement stat=d.getStatement();
		Date date=new Date();
		SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
		String nowdate=matter1.format(date);
		for(int i=0;i<list.size();i++){
			String sql="update borrow set borrow_return_time='"+nowdate+"' where borrow_book_id="+list.get(i)+" and borrow_return_time is null";
			String sql1="";
			try {
				if(this.isOrdered((Integer)list.get(i))){
					//此书已被预约
					sql1="update book set book_state='预约到馆' where book_id="+list.get(i);
					String sql2="update order_info set order_return_time=getdate()";
					stat.addBatch(sql2);
				}else{
					sql1="update book set book_state='在馆' where book_id="+list.get(i);
				}
				stat.addBatch(sql);
				stat.addBatch(sql1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int j=0;j<durty.size();j++){
			Durty dur=durty.get(j);
			String sql2="insert into durty values("+dur.getBorrowId()+","+dur.getMoney()+",'"+dur.getDescribtion()+"')";
			try {
				stat.addBatch(sql2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			int[] counts=stat.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 根据用户id获取用户的姓名，最多能借多少书，能借多久
	 * @param userId  :用户id
	 * @return        :List
	 */
	public List getUserBorrowInfo(String userId){
		List list=new ArrayList();
		String sql="select user_name,book_num,book_time from purview,user_info"+ 
        " where user_id=? and user_purview_id=purview_id";
		System.out.println(sql);
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				list.add(rs.getString("user_name"));
				list.add(rs.getInt("book_num"));
				list.add(rs.getInt("book_time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取用户已借书的数量（包括预约的书）
	 * @param userId  :用户id
	 * @return        :返回用户已借书的数量(包括预约的书)，出错返回-1
	 */
	public int getBorrowNum(String userId){
		String sql="select count(*)+(select count(*) from order_info where order_user_id=?) from borrow"+ 
        " where borrow_user_id=? and borrow_return_time is null";
		try {
			System.out.println("userID:"+userId);
			System.out.println(sql);
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, userId);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				System.out.println("rs.getInt(1)"+rs.getInt(1));
			return rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            return -1;			
		}
		
	}
	/**
	 * 查看指定用户是否有超期未还的书
	 * @param userId  :用户id
	 * @return        :有超期未还的返回true，否则false
	 */
	public boolean isOvertime(String userId){
		boolean b=false;
		String sql="select * from borrow where borrow_user_id=? and borrow_return_time is null and datediff(day,borrow_restrict_time,getdate())>0";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				//有超期的
				b=true;
//				System.out.println(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 根据书编号获取书名，状态（在馆，被预约，到馆，借出）
	 * @param bookId   :书编号
	 * @return
	 */
	public List getbookBorrowInfo(int bookId){
		List list=new ArrayList();
		String sql="select book_name,book_state from book where book_id=?";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setInt(1, bookId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				list.add(rs.getString("book_name"));
				list.add(rs.getString("book_state"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 判定此书是否此用户预约的
	 * @param userId  :用户号
	 * @param bookId  :被预约的书号
	 * @return        :若是此用户预约的返回true，否则false
	 */
	public boolean isUserOrdered(String userId,int bookId){
		boolean b=false;
		String sql="select * from order_info where order_book_id=? and order_user_id=?";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setString(2, userId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				//是此用户预约的
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		return b;
	}
	/**
	 * 借阅图书，往borrow表里插入借阅信息，book表相应的图书信息数据库触发器自动执行
	 * @param userId      :用户id
	 * @param bookId      :书id
	 * @param borrowTime   :能借多久
	 */
	public void borrow(String userId,int bookId,int borrowTime){
//		boolean b=false;
		String sql="insert into borrow(borrow_user_id,borrow_book_id,borrow_restrict_time) values(?,?,dateadd(day,?,getdate()))";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, bookId);
			ps.setInt(3, borrowTime);
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 借预约的书时，将预约表的相应预约信息删除
	 * @param userId
	 * @param bookId
	 */
	public void clearOrdered(String userId,int bookId){
		String sql="delete from order_info where order_user_id=? and order_book_id=?";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, userId);
			ps.setInt(2, bookId);
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取权限的所有名称
	 * @return
	 */
	public Vector<PurviewBean> getAllPurviewName(){
		Vector<PurviewBean> vector=new Vector<PurviewBean>();
		String sql="select purview_id,purview_name from purview";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				PurviewBean pb=new PurviewBean();
				pb.setId(rs.getString("purview_id"));
				pb.setName(rs.getString("purview_name"));
				vector.add(pb);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vector;
	}
	/**
	 * 添加用户
	 * @param ub
	 * @return
	 */
	public boolean AddUser(UserBean ub){
		boolean b=false;
		String sql="insert into user_info values(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, ub.getUser_id());
			ps.setString(2, ub.getUser_name());
			ps.setString(3, ub.getUser_passwd());
			ps.setString(4, ub.getUser_birthday());
			ps.setString(5, ub.getUser_sex());
			ps.setString(6, ub.getUser_question());
			ps.setString(7, ub.getUser_answer());
			ps.setString(8, ub.getUser_purview_id());
			ps.setString(9, ub.getUser_dept());
			ps.setString(10, ub.getUser_email());
			int result=ps.executeUpdate();
			if(result==1)
				b=true;
			System.out.println("result:"+result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 查找用户信息,如果查找信息为空，则查找所有用户
	 * @param key  :要查找的关键词：用户号/姓名/年级
	 * @param way  :查找方式      用户号/姓名/年级方式
	 * @return     :Vector<UserBean
	 */
	public Vector<UserBean> getUserInfo(String key,String way){
//		System.out.println("key:"+key+"way:"+way);
		Vector<UserBean> vector=new Vector<UserBean>();
		try {
			String sql="";
			PreparedStatement ps;
			if(key.equals("")){
				sql="select * from user_info";
				ps=d.getConn().prepareStatement(sql);
			}else if(way.equals("用户号")){
				sql="select * from user_info where user_id=?";
				ps=d.getConn().prepareStatement(sql);
				ps.setString(1, key);
			}else if(way.equals("姓   名")){
				sql="select * from user_info where user_name=?";
				ps=d.getConn().prepareStatement(sql);
				ps.setString(1, key);
			}else{
				sql="select * from user_info where user_id like ?";
				ps=d.getConn().prepareStatement(sql);
				ps.setString(1, key+"%");
			}
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				UserBean userbean=new UserBean();
				userbean.setUser_id(rs.getString(1));
				userbean.setUser_name(rs.getString(2));
				userbean.setUser_passwd(rs.getString(3));
				userbean.setUser_birthday(rs.getString(4));
				userbean.setUser_sex(rs.getString(5));
				userbean.setUser_question(rs.getString(6));
				userbean.setUser_answer(rs.getString(7));
				userbean.setUser_purview_id(rs.getString(8));
				userbean.setUser_dept(rs.getString(9));
				userbean.setUser_email(rs.getString(10));
				vector.add(userbean);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
		return vector;
	}
    
	/**
	 * 查找图书信息，如果查找关键字为空，则查找所有图书
	 * @param key  关键字: 书名/isbn/书编号
	 * @param way  查找方式: 书名方式/ISBN方式/书编号方式
	 * @return
	 */
	public Vector<BookBean> getBookInfo(String key,String way){
		Vector<BookBean> vector=new Vector<BookBean>();
		try {
			String sql="";
			PreparedStatement ps;
			if(key.equals("")){
				sql="select * from book";
				ps=d.getConn().prepareStatement(sql);
			}else if(way.equals("书编号")){
				sql="select * from book where book_id=?";
				ps=d.getConn().prepareStatement(sql);
				ps.setString(1, key);
			}else if(way.equals("书名")){
				sql="select * from book where book_name=?";
				ps=d.getConn().prepareStatement(sql);
				ps.setString(1, key);
			}else{
				sql="select * from book where book_isbn=?";
				ps=d.getConn().prepareStatement(sql);
				ps.setString(1, key);
			}
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				BookBean bookbean=new BookBean(); 
				bookbean.setId(rs.getInt(1));
				bookbean.setIsbn(rs.getString(2));
				bookbean.setName(rs.getString(3));
				bookbean.setAuthor(rs.getString(4));
				bookbean.setTranslator(rs.getString(5));
				bookbean.setPrice(rs.getInt(6));
				bookbean.setTypeId(rs.getString(7));
				bookbean.setPress(rs.getString(8));
				bookbean.setLibId(rs.getString(9));
				bookbean.setDescribtion(rs.getString(10));
				bookbean.setInTime(rs.getString(11));
				bookbean.setPublishTime(rs.getString(12));
				bookbean.setWord(rs.getInt(13));
				bookbean.setLanguage(rs.getString(14));
				bookbean.setState(rs.getString(15));
				vector.add(bookbean);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
		
		return vector;
	}
	
	public Vector<LibrarianBean> getAllLibrarian(){
		String sql="select * from librarian";
		Vector<LibrarianBean> vector=new Vector<LibrarianBean>();
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				LibrarianBean librarian=new LibrarianBean();
				librarian.setLibrarian_id(rs.getString(1));
				librarian.setLibrarian_name(rs.getString(2));
				librarian.setLibrarian_sex(rs.getString(3));//0-男,1-女
				librarian.setLibrarian_bir(rs.getString(4));
				librarian.setLibrarian_passwd(rs.getString(5));
				librarian.setLibrarian_email(rs.getString(6));
				librarian.setLibrarian_tel(rs.getString(7));
				librarian.setLibrarian_work_place(rs.getString(8));
				vector.add(librarian);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vector;
	}
	/**
	 * 更新用户信息
	 * @param ub
	 */
	public void UpdateUser(UserBean ub){
//		boolean b=false;
		String sql="update user_info set user_name=?,user_passwd=?,user_birthday='"+ub.getUser_birthday()+"',user_sex=?,"+
        "user_question=?,user_answer=?,user_purview_id=?,user_dept=?,user_email=? where user_id=?";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, ub.getUser_name());
			ps.setString(2, ub.getUser_passwd());
			ps.setString(3, ub.getUser_sex());
			//ps.setDate(3, ub.getUser_birthday());
//			ps.setString(4, ub.getUser_sex());
			ps.setString(4, ub.getUser_question());
			ps.setString(5, ub.getUser_answer());
			ps.setString(6, ub.getUser_purview_id());
			ps.setString(7, ub.getUser_dept());
			ps.setString(8, ub.getUser_email());
			ps.setString(9, ub.getUser_id());
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return b;
	}
	/**
	 * 删除用户信息
	 * @param userId :用户ID
	 */
	public void deleteUser(String userId){
		String sql="delete from user_info where user_id=?";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, userId);
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除图书信息
	 * @param bookId :图书编号
	 */
	public void deleteBook(String bookId){
		String sql="delete from book where book_id=?";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, bookId);
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得所有的图书类型
	 * @return
	 */
	public Vector<TypeBean> getAllType(){
//		List list=new ArrayList<String>();
		Vector<TypeBean> vector=new Vector<TypeBean>();
		String sql="select * from type";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				TypeBean typebean=new TypeBean();
				typebean.setId(rs.getString(1));
				typebean.setName(rs.getString(2));
				typebean.setDescribtion(rs.getString(3));
				vector.add(typebean);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vector;
	}
	
	public Vector<LibraryBean> getAllLibrary(){
		String sql="select * from library";
		Vector vector=new Vector<LibraryBean>();
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				LibraryBean library=new LibraryBean();
				library.setId(rs.getString(1));
				library.setName(rs.getString(2));
				vector.add(library);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vector;
	}
	
	/**
	 * 添加图书
	 * @param bookbean
	 * @return
	 */
	public boolean addBook(BookBean bookbean){
		boolean b=false;
		String sql="insert into book values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, bookbean.getIsbn());
			ps.setString(2, bookbean.getName());
			ps.setString(3, bookbean.getAuthor());
			ps.setString(4, bookbean.getTranslator());
			ps.setInt(5, bookbean.getPrice());
			ps.setString(6, bookbean.getTypeId());
			ps.setString(7, bookbean.getPress());
			ps.setString(8, bookbean.getLibId());
			ps.setString(9, bookbean.getDescribtion());
			ps.setString(10, bookbean.getInTime());
			ps.setString(11, bookbean.getPublishTime());
			ps.setInt(12, bookbean.getWord());
			ps.setString(13, bookbean.getLanguage());
			ps.setString(14, "在馆");
			int result=ps.executeUpdate();
			if(result==1)
				b=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 更新图书
	 * @param bookbean
	 */
	public void updateBook(BookBean bookbean){
		String sql="update book set book_isbn=?,book_name=?,book_author=?," +
				"book_translator=?,book_price=?,book_type_id=?,book_press=?," +
				"book_lib_id=?,book_describtion=?,book_in_time=?," +
				"book_publish_time=?,book_word=?,book_language=?,book_state=?" +
				" where book_id=?";
        try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, bookbean.getIsbn());
			ps.setString(2, bookbean.getName());
			ps.setString(3, bookbean.getAuthor());
			ps.setString(4, bookbean.getTranslator());
			ps.setInt(5, bookbean.getPrice());
			ps.setString(6, bookbean.getTypeId());
			ps.setString(7, bookbean.getPress());
			ps.setString(8, bookbean.getLibId());
			ps.setString(9, bookbean.getDescribtion());
			ps.setString(10, bookbean.getInTime());
			ps.setString(11, bookbean.getPublishTime());
			ps.setInt(12, bookbean.getWord());
			ps.setString(13, bookbean.getLanguage());
			ps.setString(14, bookbean.getState());
			ps.setInt(15, bookbean.getId());
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
	}
	
	/**
	 * 添加图书管理员
	 * @param lib
	 * @return
	 */
	public boolean addLibrarian(LibrarianBean librarian){
		String sql="insert into librarian values(?,?,?,?,?,?,?,?)";
		boolean b=false;
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, librarian.getLibrarian_id());
			ps.setString(2, librarian.getLibrarian_name());
			ps.setString(3, librarian.getLibrarian_sex());
			ps.setString(4, librarian.getLibrarian_bir());
			ps.setString(5, librarian.getLibrarian_passwd());
			ps.setString(6, librarian.getLibrarian_email());
			ps.setString(7, librarian.getLibrarian_tel());
			ps.setString(8, librarian.getLibrarian_work_place());
			int result=ps.executeUpdate();
			if(result==1)
				b=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 更新图书管理员信息
	 * @param librarian
	 */
	public void updateLibrarian(LibrarianBean librarian){
		String sql="update librarian set librarian_name=?,librarian_sex=?," +
				"librarian_bir=?,librarian_passwd=?,librarian_email=?," +
				"librarian_tel=?,librarian_work_place=? where librarian_id=?";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, librarian.getLibrarian_name());
			ps.setString(2, librarian.getLibrarian_sex());
			ps.setString(3, librarian.getLibrarian_bir());
			ps.setString(4, librarian.getLibrarian_passwd());
			ps.setString(5, librarian.getLibrarian_email());
			ps.setString(6, librarian.getLibrarian_tel());
			ps.setString(7, librarian.getLibrarian_work_place());
			ps.setString(8, librarian.getLibrarian_id());
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 删除图书管理员信息
	 * @param librarianId
	 */
	public void deleteLibrarian(String librarianId){
		String sql="delete from librarian where librarian_id=?";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, librarianId);
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
		}
	}
	/**
	 * 添加图书类型
	 * @param type
	 * @return
	 */
	public boolean addType(TypeBean type){
		boolean b=false;
		String sql="insert into type values(?,?,?)";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, type.getId());
			ps.setString(2, type.getName());
			ps.setString(3, type.getDescribtion());
			int result=ps.executeUpdate();
			if(result==1)
				b=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
	/**
	 * 更新图书类型信息
	 */
	public void updateType(TypeBean type){
		String sql="update type set type_name=?,type_describtion=? where type_id=?";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, type.getName());
			ps.setString(2, type.getDescribtion());
			ps.setString(3, type.getId());
			int result=ps.executeUpdate();
//			System.out.println("update type");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 删除图书类型
	 * @param typeId
	 */
	public void deleteType(String typeId){
		String sql="delete from type where type_id=?";
		try {
			PreparedStatement ps=d.getConn().prepareStatement(sql);
			ps.setString(1, typeId);
			int result=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
