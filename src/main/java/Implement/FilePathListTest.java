package Implement;

import java.util.List;

import junit.framework.TestCase;

public class FilePathListTest extends TestCase {

	public void testFilePathList() {
		FilePath filePath = new FilePath("2");
		filePath.setPathList("/src/main/java");
		filePath.setPathList("/src/main/test");
		
		FilePathList filePathList = new FilePathList();
		filePathList.setFilePath(filePath);
		List<String> actualList = filePathList.getFilePath(filePath);
		assertEquals(2,filePathList.getFilePathId(filePath));
		assertEquals("/src/main/java", actualList.get(0));
		assertEquals("/src/main/test", actualList.get(1));
	}

}
