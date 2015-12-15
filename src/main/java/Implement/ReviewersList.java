package Implement;

import Constants.ConstantValue;

public class ReviewersList {
	public Reviewer[] reviewersList;

	ReviewersList() {
		// TODO if [10000000] is over, you have to expand this value
		reviewersList = new Reviewer[ConstantValue.REVIEWERS_LIST];
	}

	public void setReviewerClass(int authorId, Reviewer reviewer) {
		reviewersList[authorId] = reviewer;
	}

	public void setReviewedPath(int authorId, String path) {
		if (reviewersList[authorId].getPathList().contains(path) == false) {
			reviewersList[authorId].setFilePath(path);
		}
	}

	public boolean isReviewersList(int authorId) {
		System.out.println("result" + authorId);
		if (reviewersList[authorId] != null) {
			return true;
		}
		return false;
	}

	public Reviewer getReveiwClass(int authorId) {
		if (this.isReviewersList(authorId) == true) {
			return reviewersList[authorId];
		} else {
			return null;
		}
	}
}
