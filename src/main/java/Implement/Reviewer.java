package Implement;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author toshiki-h
 *
 */
public class Reviewer {
	private int reviewerId;
	private List<String> path;
	private double numOfCorrectAnswer;
	private double numOfIncorrectAnswer;
	private double perOfExpertness;

	Reviewer(String id) {
		this.setReviewerId(id);
		this.path = new ArrayList<String>();
	}

	/**
	 * 
	 * @return
	 */
	public int getReviewerId() {
		return reviewerId;
	}

	/**
	 * 
	 * @param id
	 */
	private void setReviewerId(String id) {
		this.reviewerId = Integer.parseInt(id);
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getPathList() {
		return this.path;
	}

	/**
	 * 
	 * @param p
	 */
	public void setFilePath(String p) {
		this.path.add(p);
	}

	public void countCorrectCt() {
		setNumOfCorrectAnswer(getNumOfCorrectAnswer() + 1);
	}

	public void countIncorrectCt() {
		setNumOfIncorrectAnswer(getNumOfIncorrectAnswer() + 1);
	}

	public void setExpertness(double expScore) {
		setPerOfExpertness(expScore);
	}

	public double getNumOfCorrectAnswer() {
		return numOfCorrectAnswer;
	}

	public void setNumOfCorrectAnswer(double numOfCorrectAnswer) {
		this.numOfCorrectAnswer = numOfCorrectAnswer;
	}

	public double getNumOfIncorrectAnswer() {
		return numOfIncorrectAnswer;
	}

	public void setNumOfIncorrectAnswer(double numOfIncorrectAnswer) {
		this.numOfIncorrectAnswer = numOfIncorrectAnswer;
	}

	public double getPerOfExpertness() {
		return perOfExpertness;
	}

	public void setPerOfExpertness(double perOfExpertness) {
		this.perOfExpertness = perOfExpertness;
	}
}
