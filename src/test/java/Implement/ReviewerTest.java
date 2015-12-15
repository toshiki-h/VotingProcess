package Implement;

import java.util.List;

import junit.framework.TestCase;

/**
 * 
 * @author toshiki-h
 *
 */
public class ReviewerTest extends TestCase {

	/**
	 * 
	 */
	public void testGenerateReviewerClass() {
		String id = "100049";
		Reviewer reviewer = new Reviewer(id);
		int expected = 100049;
		int actual = reviewer.getReviewerId();
		assertEquals(expected, actual);
	}

	/**
	 * 
	 */
	public void testAddPathIntoList() {
		String id = "100049";
		Reviewer reviewer = new Reviewer(id);
		String expectedPath1 = "/src/main/java/app.java";
		String expectedPath2 = "/src/main/test/java/testApp.java";
		String expectedPath3 = "/README.md";

		reviewer.setFilePath(expectedPath1);
		reviewer.setFilePath(expectedPath2);
		reviewer.setFilePath(expectedPath3);
		List<String> actual = reviewer.getPathList();

		assertEquals(expectedPath1, actual.get(0));
		assertEquals(expectedPath2, actual.get(1));
		assertEquals(expectedPath3, actual.get(2));
	}

}
