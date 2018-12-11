package library.wtz.dao;

import java.sql.*;
import java.util.Vector;

import library.wtz.form.BookBean;
import library.wtz.form.BorrowInfoBean;
import library.wtz.form.LibraryBean;

/**
 * ��ѯͼ����Ϣ
 * @author wangtianzhi
 *
 */
public class GetBookInfo {
	private PreparedStatement ps = null;
	private ResultSet rs=null;
	private Connection ct=null;
	/**
	 * 
	 * @param pageSize  :һҳ��ʾ������
	 * @param method    :��������  ����/ISBN/����/������
	 * @param language  ��������   �����鿯/����ͼ��/����ͼ��
	 * @param model     ����ģʽ   ǰ��һ��/��ȫƥ��
	 * @param libId     �ݲص�     ���йݲص�/���԰�ֹ�/����
	 * @return          ��������ܹ�����Ŀ
	 */
	public int getPageCount(String method,String key,String language,String model,String libId){
		int count=0;
		String sql;
		ct=new ConnDB().getConn();
		try {
			if(method.equals("����")){
				sql="select count(*) from book where (book_author like '%"+key+"%'"+
				    " or book_translator like '%"+key+"%')";
			}else if(method.equals("ISBN")){
				sql="select count(*) from book where book_isbn='"+key+"'";
			}else if(method.equals("����")){//����
				if(model.equals("ģ��ƥ��")){
					sql="select count(*) from book where book_name like '%"+key+"%'";
				}else{//��ȫƥ��
					sql="select count(*) from book where book_name='"+key+"'";
				}
			}else{//������
				sql="select count(*) from book where book_press='"+key+"'";
			}
			if(!language.equals("�����鿯")){//���������鿯
				sql+=" and book_language='"+language+"'";
				
			}
			if(!libId.equals("")){//�������йݲص�
				sql+=" and book_lib_id='"+libId+"'";
				
			}
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next())
				count=rs.getInt(1);
			System.out.println("count(*)="+count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return count;
	}
	/**
	 * ȡ����ҳ��ʾ����Ϣ
	 * @param pageSize     :һҳ��ʾ����Ŀ
	 * @param pageNow      :Ҫ��ʾ�ڼ�ҳ
	 * @param method       :��������  ����/ISBN/����/������
	 * @param key          :�����ؼ���
	 * @param language     :�������� �����鿯/����ͼ��/����ͼ��
	 * @param model        :����ģʽ ģ��ƥ��/��ȫƥ��
	 * @param libId        :�ݲصر��
	 * @param ordermethod  :��ʲô���� ����/��������/ISBN
	 * @param order        :����ʽ ����/����
	 * @return
	 */
	public Vector<BookBean> getBook(int pageSize,int pageNow,String method,String key,String language,String model,String libId,String ordermethod,String order){
		Vector<BookBean> vector=new Vector<BookBean>();
		String sql;
		String str;
		ct=new ConnDB().getConn();
		try {
			if(method.equals("����")){
				sql="select top "+pageSize+" * from book where (book_author like '%"+key+"%'"+
				    " or book_translator like '%"+key+"%')";
				str="select top "+(pageNow-1)*pageSize+" book_id from book where (book_author like '%"+key+"%'"+
			    " or book_translator like '%"+key+"%')";
			}else if(method.equals("ISBN")){
				sql="select top "+pageSize+" * from book where book_isbn='"+key+"'";
				str="select top "+(pageNow-1)*pageSize+" book_id from book where book_isbn='"+key+"'";
			}else if(method.equals("����")){//����
				if(model.equals("ģ��ƥ��")){
					sql="select top "+pageSize+" * from book where book_name like '%"+key+"%'";
					str="select top "+(pageNow-1)*pageSize+" book_id from book where book_name like '%"+key+"%'";
				}else{//��ȫƥ��
					sql="select top "+pageSize+" * from book where book_name='"+key+"'";
					str="select top "+(pageNow-1)*pageSize+" book_id from book where book_name='"+key+"'";
				}
			}else{//������
				sql="select top "+pageSize+" * from book where book_press='"+key+"'";
				str="select top "+(pageNow-1)*pageSize+" book_id from book where book_press='"+key+"'";
			}
			if(!language.equals("�����鿯")){//���������鿯
				sql+=" and book_language='"+language+"'";
				str+=" and book_language='"+language+"'";
			}
			if(!libId.equals("")){//�������йݲص�
				sql+=" and book_lib_id='"+libId+"'";
				str+=" and book_lib_id='"+libId+"'";
			}
			sql+=" and book_id not in("+str+" order by "+ordermethod+" "+order+") order by "+ordermethod+" "+order;
			System.out.println("sql:"+sql);
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				BookBean book=new BookBean();
				book.setId(rs.getInt(1));
				book.setName(rs.getString(3));
				String author=rs.getString(4);
				if(!rs.getString(5).equals("")){
					author+=";"+rs.getString(5)+"��";
				}
				book.setAuthor(author);
				book.setPress(rs.getString(8));
				book.setState(rs.getString(15));
				book.setLibId(rs.getString(9));
				book.setLanguage(rs.getString(14));
				vector.add(book);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return vector;
	}
	/**
	 * ȡ������ͼ��ݵ������ͱ��
	 * @return
	 */
	public Vector<LibraryBean> getAllLibrary(){
		String sql="select * from library";
		Vector vector=new Vector<LibraryBean>();
		try {
			ct=new ConnDB().getConn();
			ps=ct.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				LibraryBean library=new LibraryBean();
				library.setId(rs.getString(1));
				library.setName(rs.getString(2));
				vector.add(library);
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
	 * ȡ��ָ��ͼ�����ϸ��Ϣ
	 * @param bookId
	 * @return
	 */
	public BookBean getBookDetail(int bookId){
		BookBean book=new BookBean();
		String sql="select * from book where book_id=?";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setInt(1, bookId);
			rs=ps.executeQuery();
			if(rs.next()){
				book.setId(rs.getInt(1));
				book.setIsbn(rs.getString(2));
				book.setName(rs.getString(3));
				book.setAuthor(rs.getString(4));
				book.setTranslator(rs.getString(5));
				book.setPrice(rs.getInt(6));
				book.setTypeId(rs.getString(7));
				book.setPress(rs.getString(8));
				book.setLibId(rs.getString(9));
				book.setDescribtion(rs.getString(10));
				book.setInTime(rs.getString(11));
				book.setPublishTime(rs.getString(12));
				book.setWord(rs.getInt(13));
				book.setLanguage(rs.getString(14));
				book.setState(rs.getString(15));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return book;
	}
	
	public BorrowInfoBean BorrowInfo(int bookId){
		BorrowInfoBean borrow=new BorrowInfoBean();
		String sql="select * from borrow where borrow_book_id=? and borrow_return_time is null";
		ct=new ConnDB().getConn();
		try {
			ps=ct.prepareStatement(sql);
			ps.setInt(1, bookId);
			rs=ps.executeQuery();
			if(rs.next()){
				borrow.setBorrowTime(rs.getDate(5));
				borrow.setRestrictTime(rs.getDate(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return borrow;
	}

	//�ر���Դ����
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
