package Implement;

import java.util.List;

import junit.framework.TestCase;

public class ParseReviewPathDataTest extends TestCase {

	public void testExecute() {
		String fileName = "./qt_FilePathList_new.csv";
		FilePathList filePathList = new ParseReviewPathData().execute(fileName);
		for (int i = 1; i < 10; i++) {
			List<String> list = filePathList.getFilePath(i);
			System.out.println(list.toString());
			;
		}
	}

}
