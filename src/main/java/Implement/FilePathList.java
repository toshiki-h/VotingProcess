package Implement;

import java.util.List;

public class FilePathList {
	private FilePath[] filePath;
	
	FilePathList() {
		//TODO if [100000] is over, you have to expand this value
		filePath = new FilePath[100000];
	}
	
	public void setFilePath(FilePath fPath){
		filePath[fPath.getReviewId()] = fPath;
	}
	
	public int getFilePathId(FilePath fPath){
		return filePath[fPath.getReviewId()].getReviewId();
	}
	
	public List<String> getFilePath(FilePath fPath){
		return filePath[fPath.getReviewId()].getPathList();
	}
}
