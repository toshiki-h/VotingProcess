package voting.process;

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
public class VotingProcess {
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost/qt";
    private static String user = "root";
    private static String pass = "hirao";
	public static void main(String[] args) throws SQLException {
    	String selectAll = "SELECT * FROM Review";
        Connection con = VotingProcess.getConnection();
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery(selectAll);
        int i = 0;
        while (result.next() && i < 10) {
            System.out.println(result.getInt(1) + "\t" + result.getString(8));
            i++;
        }
	}

	/**
	 * 
	 * @return
	 */
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(driverName);
            con = DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
