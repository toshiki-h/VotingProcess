package Implement;

import java.util.List;

import junit.framework.TestCase;

public class ReviewersListTest extends TestCase {

	public void testSetReviewer() {
		Reviewer reviewer = new Reviewer("100041");
		reviewer.setPath("/src/main/java");
		reviewer.setPath("/src/main/test");
		
		ReviewersList reviewerList = new ReviewersList();
		reviewerList.setReviewer(reviewer);
		List<String> actualList = reviewerList.getPathList(reviewer);
		assertEquals("/src/main/java", actualList.get(0));
		assertEquals("/src/main/test", actualList.get(1));
	}

	public void testGetPathList() {
		;
	}

}
