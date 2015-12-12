package Implement;

import java.util.List;

import junit.framework.TestCase;

public class FilePathListTest extends TestCase {

	public void testFilePathList() {
		FilePath filePath = new FilePath(2);
		FilePathList filePathList = new FilePathList();
		filePathList.setFilePathClass(2, filePath);
		filePathList.setFilePath(2, "/src/main/java");
		filePathList.setFilePath(2, "/src/main/test");
		filePathList.setFilePath(2, "/src/main/test");
		List<String> actualList = filePathList.getFilePath(2);
		assertEquals(2,filePathList.getFilePathId(filePath));
		assertEquals("/src/main/java", actualList.get(0));
		assertEquals("/src/main/test", actualList.get(1));
		assertEquals(actualList.size(), 2);
	}

}
