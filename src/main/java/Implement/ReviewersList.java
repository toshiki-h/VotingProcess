package Implement;

import java.util.List;

public class ReviewersList {
	public Reviewer[] reviewersList;
	
	ReviewersList(){
		//TODO if [10000000] is over, you have to expand this value
		reviewersList = new Reviewer[10000000];
	}
	
	public void setReviewer(Reviewer reviewer){
		reviewersList[reviewer.getReviewerId()] = reviewer;
	}
	
	public List<String> getPathList(Reviewer reviewer){
		return reviewersList[reviewer.getReviewerId()].getPath();
	}
}
