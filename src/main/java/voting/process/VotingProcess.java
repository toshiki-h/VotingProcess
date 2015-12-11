package voting.process;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.integration.c3p0.MysqlConnectionTester;

import Implement.MysqlConnecter;
import Implement.ParserReviewData;
import Util.CsvFileController;

/**
 * 
 * @author toshiki-h
 *
 */
public class VotingProcess {
	public static ResultSet result;
	
	public static void main(String[] args) throws SQLException {
		// File path perser
		Scanner inputStream = new CsvFileController(args[0]).getScanner();
		
		// Parse
		new ParserReviewData().execute(result);
	}

}
