package Implement;

import java.util.List;

import Constants.ConstantValue;

public class FilePathList {
	public FilePath[] filePathList;

	FilePathList() {
		// TODO if [100000] is over, you have to expand this value
		filePathList = new FilePath[ConstantValue.FILE_PATH_LIST];
	}

	public void setFilePath(int reviewId, String path) {
		if (filePathList[reviewId].getPathList().contains(path) == false) {
			filePathList[reviewId].setFilePath(path);
		}
	}

	public void setFilePathClass(int reviewId, FilePath filePath) {
		filePathList[reviewId] = filePath;
	}

	public int getFilePathId(FilePath fPath) {
		return filePathList[fPath.getReviewId()].getReviewId();
	}

	public List<String> getFilePath(int reviewId) {
		return filePathList[reviewId].getPathList();
	}

	public boolean isFilePathCalss(int reviewId) {
		if (filePathList[reviewId] != null) {
			return true;
		}
		return false;
	}

	public FilePath getFilePathClass(int reviewId) {
		return filePathList[reviewId];
	}

	public String getProject(int reviewId) {
		return filePathList[reviewId].getProject();
	}
}
