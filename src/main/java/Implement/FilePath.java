package Implement;

import java.util.ArrayList;
import java.util.List;

public class FilePath {
	private int reviewId;
	private List<String> pathList;
	
	FilePath(int rId){
		reviewId = rId;
		pathList = new ArrayList<String>();
	}
	
	public int getReviewId(){
		return reviewId;
	}
	
	protected void setFilePath(String path){
		pathList.add(path);
	}
	
	public List<String> getPathList(){
		return pathList;
		
	}
}
