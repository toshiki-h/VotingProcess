package Implement;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParserReviewData {

	public void execute(ResultSet result) throws SQLException{
		this.parserComment(result);
	}
	
	public void parserComment(ResultSet result) throws SQLException{
		while (result.next()) {
			System.out.println(result.getInt(1) + "\t" + result.getString(2));
		}
	}
}
