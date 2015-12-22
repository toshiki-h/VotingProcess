package Validation;

import java.util.ArrayList;
import java.util.List;

public class SkippingReviewersList {
	private final List<String> skipList;

	public SkippingReviewersList() {
		skipList = new ArrayList<String>();
		// TODO you have to add any more skip id
		skipList.add("-1");
		skipList.add("1000049");
	}

	public boolean isSkipReviewers(String author) {
		if (skipList.contains(author) == true) {
			return true;
		} else {
			return false;
		}
	}
}
