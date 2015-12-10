package Implement;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author toshiki-h
 *
 */
public class Reviewer {
	private String reviewerId;
	private List<String> path;
	
	Reviewer(String id){
		this.setReviewerId(id);
		this.path = new ArrayList<String>();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getReviewerId() {
		return reviewerId;
	}
	
	/**
	 * 
	 * @param id
	 */
	private void setReviewerId(String id) {
		this.reviewerId = id;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPath(int i) {
		String p = this.path.get(i);
		return p;
	}
	
	/**
	 * 
	 * @param p
	 */
	public void setPath(String p) {
		this.path.add(p);
	}
}
