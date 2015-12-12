package voting.process;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import com.mysql.jdbc.integration.c3p0.MysqlConnectionTester;

import Implement.FilePathList;
import Implement.MysqlConnecter;
import Implement.ParseReviewDB;
import Implement.ParseReviewPathData;
import Util.CsvFileController;

/**
 * 
 * @author toshiki-h
 *
 */
public class VotingProcess {
	public static ResultSet result;
	
	public static void main(String[] args) throws SQLException {
		String fileName = "./testCsv.csv";
		FilePathList filePathList = new ParseReviewPathData().execute(fileName);
		for (int i = 1;i < 10;i++){
			List<String> list = filePathList.getFilePath(i);
			System.out.println(list.toString());;
		}
		// Parse
		//new ParseReviewDB().execute(result);
	}

}
