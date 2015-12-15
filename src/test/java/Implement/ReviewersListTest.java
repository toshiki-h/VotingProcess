package Implement;

import java.util.List;

import junit.framework.TestCase;

public class ReviewersListTest extends TestCase {

	public void testSetReviewer() {
		Reviewer reviewer = new Reviewer("100041");
		reviewer.setFilePath("/src/main/java");
		reviewer.setFilePath("/src/main/test");

		ReviewersList reviewersList = new ReviewersList();
		System.out.println(reviewersList.isReviewersList(100041));
		reviewersList.setReviewerClass(100041, reviewer);
		Reviewer actualReviewer = reviewersList.getReveiwClass(100041);
		List<String> actualList = actualReviewer.getPathList();
		assertEquals("/src/main/java", actualList.get(0));
		assertEquals("/src/main/test", actualList.get(1));
	}

	public void testGetPathList() {
		;
	}

}
