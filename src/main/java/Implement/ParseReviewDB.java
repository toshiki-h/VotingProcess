package Implement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Calculator.SimilarityScoreMethod;
import Validation.SkippingReviewersList;

public class ParseReviewDB {
	private String selectQuery;
	private String projectName;
	private SkippingReviewersList skippingReviewersList;
	
	public ParseReviewDB() {
		skippingReviewersList = new SkippingReviewersList();
	}

	public void execute(FilePathList filePathList) throws SQLException {
		this.parserComment(filePathList);
	}

	public void parserComment(FilePathList filePathList) throws SQLException {
		projectName = "qt-creator/qt-creator";
		ReviewersList reviewersList = new ReviewersList();
		List<String> authorList = new ArrayList<String>();
		// TODO I have to change 100000 to the appropriate value.
		for (int reviewId = 0; reviewId < 100000; reviewId++) {
			if (filePathList.isFilePathCalss(reviewId) == true) {
				if (filePathList.getProject(reviewId).equals(projectName) == true) {
					selectQuery = "select ReviewId, AuthorId, Message from Comment where ReviewId = '" + reviewId
							+ "' order by WrittenOn asc;";
					ResultSet result = new MysqlConnecter().selectQuery(selectQuery);
					// Make authorList
					this.makeAuthorList(result, authorList);
					// Calcurate Expertness and Output the results
					this.calculateReviewersExpertness(reviewId, authorList, reviewersList, filePathList);
					// UpdateReviewersExpertness
					this.updateReviewersExpertness(reviewId, authorList, reviewersList, filePathList);

				}
			}
		}
	}

	public List<String> makeAuthorList(ResultSet result, List<String> authorList) throws SQLException {
		while (result.next()) {
			// Put authorId into authorList
			if (authorList.contains(result.getString(2)) != true) {
				authorList.add(result.getString(2));
			}
			// System.out.println(result.getInt(1) + "," +
			// result.getString(2) + "," + result.getString(3));
		}
		return authorList;
	}

	//TODO konosyori atodeha?
	//TODO reviewresList no index ha authorId da.
	public void updateReviewersExpertness(int reviewId, List<String> authorList, ReviewersList reviewersList,
			FilePathList filePathList) {
		for (String author : authorList) {
			//Validation of skip reviewers list.
			if (skippingReviewersList.isSkipReviewers(author)){
				continue;
			}
			int authorId = Integer.parseInt(author);
			if (reviewersList.isReviewersList(authorId) == false) {
				Reviewer reviewer = new Reviewer(author);
				reviewersList.setReviewerClass(authorId, reviewer);
			} else {
				;
			}
			for (String path : filePathList.getFilePath(reviewId)) {
				reviewersList.setReviewedPath(authorId, path);
			}
		}
	}

	public void calculateReviewersExpertness(int reviewId, List<String> authorList, ReviewersList reviewersList, FilePathList filePathList) {
		for (String author : authorList) {
			int authorId = Integer.parseInt(author);
			double resultScore = 0;
			//Validation of skip reviewers list.
			if (skippingReviewersList.isSkipReviewers(author)){
				continue;
			}
			if (reviewersList.isReviewersList(authorId)) {
				Reviewer reviewer = reviewersList.getReveiwClass(authorId);
				List<String> pastReviewedPathList = reviewer.getPathList();
				List<String> currentReviewedPathList = filePathList.getFilePath(reviewId);
				for (String currentPath : currentReviewedPathList){
					int ctMethod = 0;
					double tmpScore = 0;
					for(String pastPath : pastReviewedPathList){
						SimilarityScoreMethod simMethod = new SimilarityScoreMethod();
						tmpScore = simMethod.longestCommonPrefix(currentPath, pastPath);
						tmpScore = simMethod.longestCommonSuffix(currentPath, pastPath);
						tmpScore = simMethod.longestCommonSubstring(currentPath, pastPath);
						tmpScore = simMethod.longestCommonSubsequence(currentPath, pastPath);
						ctMethod++;
					}
					resultScore = tmpScore / ctMethod;
				}
			}
			else{
				//TODO error procedure
				;
			}
		}
	}
}
