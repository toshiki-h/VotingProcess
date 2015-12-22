package Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VotingScoreMethod {
	private List<Pattern> score2;
	private List<Pattern> score1;
	// private List<Pattern> score0;
	private List<Pattern> score_1;
	private List<Pattern> score_2;

	public VotingScoreMethod() {
		score2 = new ArrayList<Pattern>();
		score1 = new ArrayList<Pattern>();
		// score0 = new ArrayList<Pattern>();
		score_1 = new ArrayList<Pattern>();
		score_2 = new ArrayList<Pattern>();

		// TODO add regular expressions
		this.setScore2_2();
		this.setScore1_1();
	}

	private void setScore1_1() {
		// Score +1
		String regexp1 = "Patch Set [1-9]*: Looks good to me, but someone else must approve";
		String regexp2 = "Patch Set [1-9]*: Works for me";
		String regexp3 = "Patch Set [1-9]*: Verified";
		String regexp4 = "Patch Set [1-9]*: Sanity review passed";
		String regexp5 = "Patch Set [1-9]*: Code-Review+1";
		String regexp6 = "Patch Set [1-9]*: Workflow+1";
		Pattern p1 = Pattern.compile(regexp1);
		Pattern p2 = Pattern.compile(regexp2);
		Pattern p3 = Pattern.compile(regexp3);
		Pattern p4 = Pattern.compile(regexp4);
		Pattern p5 = Pattern.compile(regexp5);
		Pattern p6 = Pattern.compile(regexp6);
		score1.add(p1);
		score1.add(p2);
		score1.add(p3);
		score1.add(p4);
		score1.add(p5);
		score1.add(p6);

		// Score -1
		String regexp7 = "Patch Set [1-9]*: I would prefer that you didn'*t submit this";
		String regexp8 = "Patch Set [1-9]*: Sanity problems found";
		String regexp9 = "Patch Set [1-9]*: Code-Review-1";
		String regexp10 = "Patch Set [1-9]*: Workflow-1";
		String regexp11 = "Patch Set [1-9]*: Doesn'*t seem to work";
		String regexp12 = "Patch Set [1-9]*: I would prefer that you didn'*t merge this";
		String regexp13 = "Patch Set [1-9]*: No score";
		Pattern p7 = Pattern.compile(regexp7);
		Pattern p8 = Pattern.compile(regexp8);
		Pattern p9 = Pattern.compile(regexp9);
		Pattern p10 = Pattern.compile(regexp10);
		Pattern p11 = Pattern.compile(regexp11);
		Pattern p12 = Pattern.compile(regexp12);
		Pattern p13 = Pattern.compile(regexp13);
		score_1.add(p7);
		score_1.add(p8);
		score_1.add(p9);
		score_1.add(p10);
		score_1.add(p11);
		score_1.add(p12);
		score_1.add(p13);
	}

	private void setScore2_2() {
		// score +2
		String regexp1 = "Patch Set [1-9]*: Looks good to me, approved";
		String regexp2 = "Patch Set [1-9]*: Looks good to me\n"; // TODO

		// score -2
		String regexp3 = "Patch Set [1-9]*: Do not merge";
		String regexp4 = "Patch Set [1-9]*: Do not submit";
		String regexp5 = "Patch Set [1-9]*: Major sanity problems found";
		String regexp6 = "Uploaded patch set [1-9]*"; // Actually, this is not
														// score -2, but this is
														// regarded as -2.
		String regexp7 = "Patch Set [1-9]*: Fails";

		// Create patterns
		Pattern p1 = Pattern.compile(regexp1);
		Pattern p2 = Pattern.compile(regexp2);
		score2.add(p1);
		score2.add(p2);
		Pattern p3 = Pattern.compile(regexp3);
		Pattern p4 = Pattern.compile(regexp4);
		Pattern p5 = Pattern.compile(regexp5);
		Pattern p6 = Pattern.compile(regexp6);
		Pattern p7 = Pattern.compile(regexp7);
		score_2.add(p3);
		score_2.add(p4);
		score_2.add(p5);
		score_2.add(p6);
		score_2.add(p7);
	}

	public int getVotingScore(String comment) {
		for (Pattern voteRegStr : score2) {
			Matcher matcher = voteRegStr.matcher(comment);
			if (matcher.find()) {
				return 2;
			}
		}
		for (Pattern voteRegStr : score_2) {
			Matcher matcher = voteRegStr.matcher(comment);
			if (matcher.find()) {
				return -2;
			}
		}
		for (Pattern voteRegStr : score1) {
			Matcher matcher = voteRegStr.matcher(comment);
			if (matcher.find()) {
				return 1;
			}
		}
		for (Pattern voteRegStr : score_1) {
			Matcher matcher = voteRegStr.matcher(comment);
			if (matcher.find()) {
				return -1;
			}
		}

		return 0;
	}

	public boolean judgeCorrectIncorrect(int votedScore, int judge) {
		assert judge == 2 || judge == -2 : "judge is invalid" + judge;
		if ((votedScore > 0 && judge > 0) || (votedScore < 0 && judge < 0)) {
			return true;
		} else {
			return false;
		}
	}
}
