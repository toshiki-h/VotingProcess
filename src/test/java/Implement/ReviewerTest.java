package Implement;

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
		String expected = id;
		String actual = reviewer.getReviewerId();
		assertEquals(expected, actual);
	}
	
	/**
	 * 
	 */
	public void testAddPathIntoList(){
		String id = "100049";
		Reviewer reviewer = new Reviewer(id);
		String expectedPath1 = "/src/main/java/app.java";
		String expectedPath2 = "/src/main/test/java/testApp.java";
		String expectedPath3 = "/README.md";
		
		reviewer.setPath(expectedPath1);
		reviewer.setPath(expectedPath2);
		reviewer.setPath(expectedPath3);
		String actual1 = reviewer.getPath(0);
		String actual2 = reviewer.getPath(1);
		String actual3 = reviewer.getPath(2);
		
		assertEquals(expectedPath1, actual1);
		assertEquals(expectedPath2, actual2);
		assertEquals(expectedPath3, actual3);
	}

}
