package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * 数据库连接
 *@author  wsz
 *@createdTime 2018年3月15日
 */
public class DBUtils {

	private static String DRIVERCLASS;
	private static String URL;
	private static String USERNAME ;
	private static String PASSWORD ;
	
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("db");
		DRIVERCLASS = bundle.getString("DRIVERCLASS");
		URL 		 = bundle.getString("URL");
		USERNAME = bundle.getString("USERNAME");
		PASSWORD = bundle.getString("PASSWORD");
	}
	
	public static Connection getConn() {
		Connection connection = null;
		try {
			Class.forName(DRIVERCLASS);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void clossAll(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		try {
			if(conn != null) 
				conn.close();
			if(pstmt != null) 
				pstmt.close();
			if(rs != null) 
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(DBUtils.getConn());
	}
}
