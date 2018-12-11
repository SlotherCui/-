package library.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户登入验证
 * @author wangtianzhi
 *
 */
public class LoginCl {
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection ct=null;
	/**
	 * 图书管理员登录验证
	 * @param user       :图书管理员号(librarian_id)
	 * @param password   ：密码(librarian_password)
	 * @return           :输入正确返回true，否则false
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
	 * 系统管理员登入验证
	 * @param user       :系统管理员用户id(admin_id)
	 * @param password   :密码
	 * @return           ：输入正确返回true，否则false
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
	 * 关闭资源
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
