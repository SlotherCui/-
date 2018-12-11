package library.wtz.dao;
import java.sql.*;
/**
 * �������ݿ�
 * @author wangtianzhi
 *
 */

public class ConnDB {
	private Connection ct=null;
	private Statement st=null;
	private ResultSet rs;
	public Connection getConn(){
		//1.��������
	    try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//2.�õ�����
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
	//��ý����
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
	//�������ݿ���Ϣ
	public int getUpdate(String sql){
		try {
			return getStatement().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	//�ر����Ӷ���
	public void close(){
		try {
			ct.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�ر����Ӷ���
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
	//�رս����
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