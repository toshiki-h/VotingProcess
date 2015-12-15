package Implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author toshiki-h
 *
 */
public class MysqlConnecter {
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/qt";
	private static String user = "root";
	private static String pass = "hirao";
	private final Connection con;

	public MysqlConnecter() throws SQLException {
		con = this.getConnection();
	}

	/**
	 * 
	 * @return
	 */
	private Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public ResultSet selectQuery(String selectQuery) throws SQLException {
		Statement stmt = con.createStatement();
		return stmt.executeQuery(selectQuery);
	}
}
