package library.wtz.dao;
import java.sql.*;
/**
 * 连接数据库
 * @author wangtianzhi
 *
 */

public class ConnDB {
	private Connection ct=null;
	private Statement st=null;
	private ResultSet rs;
	public Connection getConn(){
		//1.加载驱动
	    try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//2.得到连接
			ct=DriverManager.getConnection("jdbc:sqlserver://localhost:1433; DatabaseName=library","wtz","362413");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return ct;
	}
	public Statement getStatement(){
		try {
			st=getConn().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return st;
	}
	//获得结果集
	public ResultSet getResultSet(String sql){
		try {
			rs=getStatement().executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return rs;
	}
	//更新数据库信息
	public int getUpdate(String sql){
		try {
			return getStatement().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	//关闭连接对象
	public void close(){
		try {
			ct.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//关闭连接对象
	public void closeStatement(){
		try {
			if(st!=null){
				st.close();
				st=null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//关闭结果集
	public void closeResultSet(){
		if(rs!=null){
			try {
				rs.close();
				rs=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}