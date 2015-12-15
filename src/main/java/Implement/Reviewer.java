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
}
