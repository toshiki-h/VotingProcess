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
	public FilePathList execute(String fileName){
		inputStream = new CsvFileController(fileName).getScanner();
		setReviewPathData();
		return getFilePathList();
	}
	
	/**
	 * 
	 */
	private void setReviewPathData(){
		filePathList = new FilePathList();
		FilePath filePath;
		while (inputStream.hasNext()) {
			// read single line, put in string
			String data = inputStream.next();
			String[] words = data.split(",");
			int reviewId = Integer.parseInt(words[0]);
			String path = words[1];
			
			//System.out.println(words[0]+"--"+words[1]);
			if(filePathList.isFilePathCalss(reviewId) == false){
				filePath = new FilePath(reviewId);
				filePathList.setFilePathClass(reviewId, filePath);
			}
			else{
				;
			}
			filePathList.setFilePath(reviewId, path);
		}
	}
	
	public FilePathList getFilePathList(){
		return filePathList;
	}
}
