package voting.process;

import java.sql.SQLException;

import Implement.FilePathList;
import Implement.ParseReviewDB;
import Implement.ParseReviewPathData;

/**
 * 
 * @author toshiki-h
 *
 */
public class VotingProcess {

	public static void main(String[] args) throws SQLException {
		// Set file path list
		String fileName = "./qt_FilePathList_new2.csv";
		FilePathList filePathList = new ParseReviewPathData().execute(fileName);

		// Parse review data
		new ParseReviewDB().execute(filePathList);
		System.out.println("Parse review data passed.");
	}

}
