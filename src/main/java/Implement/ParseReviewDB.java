package Implement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Calculator.SimilarityScoreMethod;
import Calculator.VotingScoreMethod;
import Validation.SkippingReviewersList;

public class ParseReviewDB {
	private String selectCommentsQuery;
	private String selectReviewInformationQuery;
	private String projectName;
	private SkippingReviewersList skippingReviewersList;

	private List<Integer> ynIdList;
	private List<Boolean> ynResultsList;
	private double ynExpertness;
	private VotingScoreMethod votingScoreMethod;

	public ParseReviewDB() {
		skippingReviewersList = new SkippingReviewersList();
		ynIdList = new ArrayList<Integer>();
		ynResultsList = new ArrayList<Boolean>();
		votingScoreMethod = new VotingScoreMethod();
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
					/** Init List **/
					ynIdList.clear();
					ynResultsList.clear();
					ynExpertness = 0;
					/** Select Query **/
					selectCommentsQuery = "select ReviewId, AuthorId, Message from Comment where ReviewId = '"
							+ reviewId + "' order by WrittenOn asc;";
					selectReviewInformationQuery = "select ReviewId, Status from Review where ReviewId = '" + reviewId
							+ "';";
					ResultSet resultComments1 = new MysqlConnecter().selectQuery(selectCommentsQuery);
					ResultSet resultComments2 = new MysqlConnecter().selectQuery(selectCommentsQuery);
					ResultSet resultReviewInfo = new MysqlConnecter().selectQuery(selectReviewInformationQuery);

					/** Make author List for Expertness calcuration **/
					this.makeAuthorList(resultComments1, authorList);
					/** Calucrate reviewers' correctness **/
					this.calcurateVotingScoreAndResults(resultComments2, resultReviewInfo, reviewersList);
					/** Calucrate reviewers' Expertness **/
					this.calculateReviewersExpertness(reviewId, authorList, reviewersList, filePathList);
					/** Create new reviewers class **/
					this.createNewReviewersClass(reviewId, authorList, reviewersList, filePathList); // UpdateReviewersExpertness
					/** OutPut reviewers' correctness and expertness **/
					this.outputReviewersCorrectnessAndExpertness(authorList, reviewersList);
				}
			}
		}
	}

	public List<String> makeAuthorList(ResultSet result, List<String> authorList) throws SQLException {
		while (result.next()) {
			if (skippingReviewersList.isSkipReviewers(result.getString(2))) {
				continue;
			}
			// Put authorId into authorList
			if (authorList.contains(result.getString(2)) != true) {
				authorList.add(result.getString(2));
			}
			// System.out.println(result.getInt(1) + "," +
			// result.getString(2) + "," + result.getString(3));
		}
		return authorList;
	}

	// TODO konosyori atodeha?
	// TODO reviewresList no index ha authorId da.
	public void createNewReviewersClass(int reviewId, List<String> authorList, ReviewersList reviewersList,
			FilePathList filePathList) {
		for (String author : authorList) {
			// Validation of skip reviewers list.
			if (skippingReviewersList.isSkipReviewers(author)) {
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
			/** Count correctness for new reviewers **/
			assert ynIdList.size() == ynResultsList.size() : "ynIdList.size has to equal ynResultsList.size.";
			for (int i = 0; i < ynIdList.size(); i++) {
				if (reviewersList.isReviewersList(ynIdList.get(i)) == false) {
					Reviewer reviewer = new Reviewer(Integer.toString(ynIdList.get(i)));
					reviewersList.setReviewerClass(ynIdList.get(i), reviewer);
				}
				reviewersList.countCorrectness(ynIdList.get(i), ynResultsList.get(i));
				reviewersList.setExpertiseScore(ynIdList.get(i), ynExpertness);
			}
		}
	}

	public void calculateReviewersExpertness(int reviewId, List<String> authorList, ReviewersList reviewersList,
			FilePathList filePathList) {
		for (String author : authorList) {
			int authorId = Integer.parseInt(author);
			double resultScore = 0;
			// Validation of skip reviewers list.
			if (skippingReviewersList.isSkipReviewers(author)) {
				continue;
			}
			if (reviewersList.isReviewersList(authorId)) {
				Reviewer reviewer = reviewersList.getReveiwClass(authorId);
				List<String> pastReviewedPathList = reviewer.getPathList();
				List<String> currentReviewedPathList = filePathList.getFilePath(reviewId);
				int ctAveNum = 0;
				for (String currentPath : currentReviewedPathList) {
					ctAveNum++;
					int ctMethod = 0;
					double tmpScore = 0;
					for (String pastPath : pastReviewedPathList) {
						SimilarityScoreMethod simMethod = new SimilarityScoreMethod();
						tmpScore += simMethod.longestCommonPrefix(currentPath, pastPath) / Math.min(currentPath.length(), pastPath.length());
						tmpScore += simMethod.longestCommonSuffix(currentPath, pastPath) / Math.min(currentPath.length(), pastPath.length());
						tmpScore += simMethod.longestCommonSubstring(currentPath, pastPath) / Math.min(currentPath.length(), pastPath.length());
						tmpScore += simMethod.longestCommonSubsequence(currentPath, pastPath) / Math.min(currentPath.length(), pastPath.length());
						ctMethod++;
					}
					resultScore += tmpScore / ctMethod;
				}
				assert (resultScore / ctAveNum) <= 1 : "resultScore / ctAveNum is over 1.0.";
				reviewersList.getReveiwClass(authorId).setExpertness(resultScore / ctAveNum);
			} else {
				// TODO error procedure
				;
			}
		}
	}

	public void calcurateVotingScoreAndResults(ResultSet resultComments, ResultSet resultReviewInfo,
			ReviewersList reviewersList) throws SQLException {
		String status = "";
		List<Integer> authorId_List = new ArrayList<Integer>();
		List<Integer> author_reviewed_Score_List = new ArrayList<Integer>();

		while (resultReviewInfo.next()) {
			status = resultReviewInfo.getString(2);
		}

		while (resultComments.next()) {
			//System.out.println(resultComments.getString(3));
			int authorId = Integer.parseInt(resultComments.getString(2));
			String comment = resultComments.getString(3);
			int judge = votingScoreMethod.getVotingScore(comment);
			if (judge == 1 || judge == -1) {
				authorId_List.add(authorId);
				author_reviewed_Score_List.add(judge);
			} else if (judge == 2 || judge == -2) {
				assert authorId_List.size() == author_reviewed_Score_List.size() : "Invalid";
				for (int i = 0; i < authorId_List.size(); i++) {
					if (reviewersList.isReviewersList(authorId_List.get(i))) {
						reviewersList.countCorrectness(authorId_List.get(i),
								votingScoreMethod.judgeCorrectIncorrect(author_reviewed_Score_List.get(i), judge));
					} else {
						ynIdList.add(authorId);
						ynResultsList
								.add(votingScoreMethod.judgeCorrectIncorrect(author_reviewed_Score_List.get(i), judge));
					}
				}
			}
		}
		// process remaining votes
		assert authorId_List.size() == author_reviewed_Score_List.size() : "Invalid";
		int judge = 0;
		if (status.equals("merged")) {
			judge = 2;
		} else if (status.equals("abandoned")) {
			judge = -2;
		}
		for (int i = 0; judge != 0 && i < authorId_List.size(); i++) {
			if (reviewersList.isReviewersList(authorId_List.get(i))) {
				reviewersList.countCorrectness(authorId_List.get(i),
						votingScoreMethod.judgeCorrectIncorrect(author_reviewed_Score_List.get(i), judge));
			} else {
				ynIdList.add(authorId_List.get(i));
				ynResultsList.add(votingScoreMethod.judgeCorrectIncorrect(author_reviewed_Score_List.get(i), judge));
			}
		}
	}

	private void outputReviewersCorrectnessAndExpertness(List<String> authorList, ReviewersList reviewersList) {
		for (String authorId : authorList) {
			double correctness;
			double allCt = reviewersList.getReveiwClass(Integer.parseInt(authorId)).getNumOfCorrectAnswer()
					+ reviewersList.getReveiwClass(Integer.parseInt(authorId)).getNumOfIncorrectAnswer();
			if (allCt == 0) {
				correctness = 0;
			} else {
				correctness = reviewersList.getReveiwClass(Integer.parseInt(authorId)).getNumOfCorrectAnswer()
						/ (reviewersList.getReveiwClass(Integer.parseInt(authorId)).getNumOfCorrectAnswer()
								+ reviewersList.getReveiwClass(Integer.parseInt(authorId)).getNumOfIncorrectAnswer());
			}
			double expertise = reviewersList.getReveiwClass(Integer.parseInt(authorId)).getPerOfExpertness();
			System.out.println(authorId + "," + correctness + "," + expertise);
		}

	}
}
