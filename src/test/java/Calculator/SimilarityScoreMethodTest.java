package Calculator;

import junit.framework.TestCase;

public class SimilarityScoreMethodTest extends TestCase {

	public void testValidLongestCommonPrefix1() {
		String currentPath = "src/com/android/settings/LocationSettings.java";
		String pastPath = "src/com/android/settings/Utils.java";
		SimilarityScoreMethod similarityScoreMethod = new SimilarityScoreMethod();
		double actual = similarityScoreMethod.longestCommonPrefix(currentPath, pastPath);
		double expected = 4;
		assertEquals(expected, actual);
	}

	public void testValidLongestCommonPrefix2() {
		String currentPath = "";
		String pastPath = "src/com/android/settings/Utils.java";
		SimilarityScoreMethod similarityScoreMethod = new SimilarityScoreMethod();
		double actual = similarityScoreMethod.longestCommonPrefix(currentPath, pastPath);
		double expected = 0;
		assertEquals(expected, actual);
	}

	public void testValidLongestCommonPrefix3() {
		String currentPath = "src2/com/android/settings/LocationSettings.java";
		String pastPath = "src/com/android/settings/Utils.java";
		SimilarityScoreMethod similarityScoreMethod = new SimilarityScoreMethod();
		double actual = similarityScoreMethod.longestCommonPrefix(currentPath, pastPath);
		double expected = 0;
		assertEquals(expected, actual);
	}

	public void testValidLongestCommonSuffix1() {
		String currentPath = "src/imports/undo/undo.pro";
		String pastPath = "tests/auto/undo/undo.pro";
		SimilarityScoreMethod similarityScoreMethod = new SimilarityScoreMethod();
		double actual = similarityScoreMethod.longestCommonSuffix(currentPath, pastPath);
		double expected = 2;
		assertEquals(expected, actual);
		
		String currentPath2 = "src/imports/undo/undo.pro";
		String pastPath2 = "tests/auto/undo/do.pro";
		SimilarityScoreMethod similarityScoreMethod2 = new SimilarityScoreMethod();
		double actual2 = similarityScoreMethod2.longestCommonSuffix(currentPath2, pastPath2);
		double expected2 = 0;
		assertEquals(expected2, actual2);
	}

	public void testValidLongestCommonSuffix2() {
		String currentPath = "undo/undo.pro";
		String pastPath = "tests/auto/undo/undo.pro";
		SimilarityScoreMethod similarityScoreMethod = new SimilarityScoreMethod();
		double actual = similarityScoreMethod.longestCommonSuffix(currentPath, pastPath);
		double expected = 2;
		assertEquals(expected, actual);

		String currentPath2 = "src/imports/undo/undo.pro";
		String pastPath2 = "undo.pro";
		SimilarityScoreMethod similarityScoreMethod2 = new SimilarityScoreMethod();
		double actual2 = similarityScoreMethod2.longestCommonSuffix(currentPath2, pastPath2);
		double expected2 = 1;
		assertEquals(expected2, actual2);
	}
	
	public void testValidLongestCommonSuffix3() {
		String currentPath = "";
		String pastPath = "tests/auto/undo/undo.pro";
		SimilarityScoreMethod similarityScoreMethod = new SimilarityScoreMethod();
		double actual = similarityScoreMethod.longestCommonSuffix(currentPath, pastPath);
		double expected = 0;
		assertEquals(expected, actual);

		String currentPath2 = "src/imports/undo/undo.pro";
		String pastPath2 = "";
		SimilarityScoreMethod similarityScoreMethod2 = new SimilarityScoreMethod();
		double actual2 = similarityScoreMethod2.longestCommonSuffix(currentPath2, pastPath2);
		double expected2 = 0;
		assertEquals(expected2, actual2);
	}

	public void testLongestCommonSubstring() {
	}

	public void testLongestCommonSubsequence1() {
		String currentPath = "aaa/bbb/ccc/ddd/eee/fff/ggg/hhh.";
		String pastPath = "xxx/ccc/zzz/eee/fff/mmm";
		SimilarityScoreMethod similarityScoreMethod = new SimilarityScoreMethod();
		double actual = similarityScoreMethod.longestCommonSubsequence(currentPath, pastPath);
		double expected = 3;
		assertEquals(expected, actual);
	}
	
	public void testLongestCommonSubsequence2() {
		String currentPath = "xxx/bbb/ccc/ddd/eee/eee/fff/ggg/hhh.";
		String pastPath = "xxx/ccc/zzz/eee/fff/mmm";
		SimilarityScoreMethod similarityScoreMethod = new SimilarityScoreMethod();
		double actual = similarityScoreMethod.longestCommonSubsequence(currentPath, pastPath);
		double expected = 4;
		assertEquals(expected, actual);
	}
	
	public void testLongestCommonSubsequence3() {
		String currentPath = "xxx/bbb/ccc/ddd/eee/eee/fff/ggg/hhh.";
		String pastPath = "xxx/ccc/zzz/eee/ghg/eee/fff/mmm";
		SimilarityScoreMethod similarityScoreMethod = new SimilarityScoreMethod();
		double actual = similarityScoreMethod.longestCommonSubsequence(currentPath, pastPath);
		double expected = 5;
		assertEquals(expected, actual);
	}
	
	public void testLongestCommonSubsequence4() {
		String currentPath = "xxx/ccc/zzz/eee/ghg/eee/fff/mmm";
		String pastPath = "";
		SimilarityScoreMethod similarityScoreMethod = new SimilarityScoreMethod();
		double actual = similarityScoreMethod.longestCommonSubsequence(currentPath, pastPath);
		double expected = 5;
		assertEquals(expected, actual);
	}
	
	public void testLongestCommonSubsequence5() {
		String currentPath = "xxx/ccc/zzz/eee/ghg/eee/fff/mmm/eee";
		String pastPath = "xxx/ccc/zzz/eee/ghg/eee/fff/mmm";
		SimilarityScoreMethod similarityScoreMethod = new SimilarityScoreMethod();
		double actual = similarityScoreMethod.longestCommonSubsequence(currentPath, pastPath);
		double expected = 5;
		assertEquals(expected, actual);
	}


}
