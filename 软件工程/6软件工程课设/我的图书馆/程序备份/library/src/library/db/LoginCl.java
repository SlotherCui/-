package library.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * �û�������֤
 * @author wangtianzhi
 *
 */
public class LoginCl {
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection ct=null;
	/**
	 * ͼ�����Ա��¼��֤
	 * @param user       :ͼ�����Ա��(librarian_id)
	 * @param password   ������(librarian_password)
	 * @return           :������ȷ����true������false
	 */
	public boolean librarianLogin(String user,String password){
		boolean b=false;
		try {
			ct=new ConnDB().getConn();
			ps=ct.prepareStatement("select * from librarian where librarian_id=? and librarian_passwd=?");
			ps.setString(1, user);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()){
				b=true;		
			}
			System.out.println(b);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return b;
	}
	/**
	 * ϵͳ����Ա������֤
	 * @param user       :ϵͳ����Ա�û�id(admin_id)
	 * @param password   :����
	 * @return           ��������ȷ����true������false
	 */
	public boolean adminLogin(String user,String password){
		boolean b=false;
		try {
			ct=new ConnDB().getConn();
			ps=ct.prepareStatement("select * from administrator where admin_id=? and admin_passwd=?");
			ps.setString(1, user);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if(rs.next()){
				b=true;		
			}
			System.out.println(b);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return b;
	}
	/**
	 * �ر���Դ
	 */
	public void close(){
		try {
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(ps!=null){
				ps.close();
				ps=null;
			}
			if(ct!=null){
				ct.close();
				ct=null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
