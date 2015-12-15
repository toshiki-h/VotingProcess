package voting.process;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Implement.MysqlConnecter;
import Util.CsvFileController;
import junit.framework.TestCase;

public class VotingProcessTest extends TestCase {
	Scanner inputStream;
	private ResultSet result;

	/**
	 * 
	 */
	public void testCsvController() {
		String fileName = "./testCsv.csv";
		inputStream = new CsvFileController(fileName).getScanner();
	}

	public void testMysqlController() throws SQLException {
		// The query for mysql
		for (int i = 0; i > 200; i++) {
			String selectAll = "select ReviewId, AuthorId, Message from Comment where order by WrittenOn asc;";
			// Mysql Connection
			result = new MysqlConnecter().selectQuery(selectAll);
		}
	}

	public void testOutPut() throws SQLException {
		// Output
		this.testCsvController();
		this.testMysqlController();
		// new ParserReviewData().execute(result);
	}
}
