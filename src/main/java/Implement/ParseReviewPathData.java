package Implement;

import java.util.Scanner;

import Util.CsvFileController;

public class ParseReviewPathData {
	private Scanner inputStream;
	FilePathList filePathList;

	public ParseReviewPathData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 * 
	 */
	public FilePathList execute(String fileName) {
		inputStream = new CsvFileController(fileName).getScanner();
		setReviewPathData();
		return getFilePathList();
	}

	/**
	 * 
	 */
	private void setReviewPathData() {
		filePathList = new FilePathList();
		FilePath filePath;
		int i = 1;
		while (inputStream.hasNext()) {
			// read single line, put in string
			String data = inputStream.next();
			String[] words = data.split(",");
			// System.out.println(words[0]);
			if (words[0].equals("path/app") == true) {
				System.out.println(i + ":" + data);
				continue;
			}
			i++;

			int reviewId = Integer.parseInt(words[0]);
			String path = words[1];
			String project = words[4];

			// System.out.println(words[0]+"--"+words[1]);
			if (filePathList.isFilePathCalss(reviewId) == false) {
				filePath = new FilePath(reviewId, project);
				filePathList.setFilePathClass(reviewId, filePath);
			} else {
				;
			}
			filePathList.setFilePath(reviewId, path);
		}
	}

	public FilePathList getFilePathList() {
		return filePathList;
	}
}
