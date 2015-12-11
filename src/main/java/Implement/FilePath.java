package Implement;

import java.util.ArrayList;
import java.util.List;

public class FilePath {
	private int reviewId;
	private List<String> pathList;
	
	FilePath(String rId){
		reviewId = Integer.parseInt(rId);
		pathList = new ArrayList<String>();
	}
	
	public int getReviewId(){
		return reviewId;
	}
	
	public void setPathList(String path){
		pathList.add(path);
	}
	
	public List<String> getPathList(){
		return pathList;
		
	}
}
