package library.db;
import java.sql.*;
import library.vo.*;

public class GetUserInfo {
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	private Connection ct=null;
	
	/**
	 * 从数据库获取用户信息
	 * @param user      用户号
	 * @return
	 */
	public UserBean getUserInfo(String user){
		UserBean userbean=new UserBean();
		try {
			ct=new ConnDB().getConn();
			ps=ct.prepareStatement("select * from user_info where user_id=?");
			ps.setString(1, user);
			rs=ps.executeQuery();
//			if(rs.next()){
//				System.out.println("ok");
//			}
//			ResultSetMetaData rsmd=rs.getMetaData();
//			System.out.println(rsmd.getColumnName(1));
			if(rs.next()){
				userbean.setUser_id(rs.getString(1));
				userbean.setUser_name(rs.getString(2));
				userbean.setUser_passwd(rs.getString(3));
				userbean.setUser_birthday(rs.getString(4));
				userbean.setUser_sex(rs.getString(5));
				userbean.setUser_question(rs.getString(6));
				userbean.setUser_answer(rs.getString(7));
				userbean.setUser_purview_id(rs.getString(8));
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
