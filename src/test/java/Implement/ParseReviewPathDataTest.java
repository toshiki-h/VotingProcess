package Implement;

import java.util.List;

import Util.CsvFileController;
import junit.framework.TestCase;

public class ParseReviewPathDataTest extends TestCase {

	public void testExecute() {
		String fileName = "./testCsv.csv";
		FilePathList filePathList = new ParseReviewPathData().execute(fileName);
		for (int i = 1;i < 10;i++){
			List<String> list = filePathList.getFilePath(i);
			System.out.println(list.toString());;
		}
	}

}
