package Implement;

import java.util.ArrayList;
import java.util.List;

public class FilePath {
	private int reviewId;
	private String project;
	private List<String> pathList;

	FilePath(int rId, String project) {
		reviewId = rId;
		this.setProject(project);
		pathList = new ArrayList<String>();
	}

	public int getReviewId() {
		return reviewId;
	}

	protected void setFilePath(String path) {
		pathList.add(path);
	}

	public List<String> getPathList() {
		return pathList;

	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
}
