package systemview;
import java.util.*;
import java.sql.*;
public class Dao 
{
	protected static String dbClassName="com.mysql.jdbc.Driver";
	protected static String dbUrl="jdbc:mysql://localhost/student";
	protected static String dbUser="root";
	protected static String dbPassword="960830";
	private static Connection conn=null;
	private Dao()
	{
		try{
			if(conn==null){
				Class.forName(dbClassName);
				conn=DriverManager.getConnection(dbUrl,dbUser,dbPassword);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static ResultSet executeQuery(String sql)
	{
		try{
			if(conn==null)
				new Dao();
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
		}catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}finally{
			
		}
	}
	public static int executeUpdate(String sql)
	{
		try{
			if(conn==null)
				new Dao();
			return conn.createStatement().executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}finally{
			
		}
	}
	public static void close()
	{
		try{
			conn.close();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}finally{
			conn=null;
		}
	}

}
