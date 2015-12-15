package Calculator;

public class SimilarityScoreMethod {

	public int longestCommonPrefix(String currentPath, String pastPath) {
		String[] currentCompornents = currentPath.split("/");
		String[] pastCompornents = pastPath.split("/");
		int index = 0;
		while (currentCompornents[index].equals(pastCompornents[index])) {
			index++;
		}
		return index;
	}

	public int longestCommonSuffix(String currentPath, String pastPath) {
		String[] currentCompornents = currentPath.split("/");
		String[] pastCompornents = pastPath.split("/");
		int cIndex = currentCompornents.length - 1;
		int pIndex = pastCompornents.length - 1;
		int ctEquals = 0;
		for (; cIndex >= 0 && pIndex >= 0; cIndex--, pIndex--) {
			if (currentCompornents[cIndex].equals(pastCompornents[pIndex])) {
				ctEquals++;
			} else {
				break;
			}
		}
		return ctEquals;
	}

	public int longestCommonSubstring(String currentPath, String pastPath) {

		return 0;

	}

	public int longestCommonSubsequence(String currentPath, String pastPath) {
		String[] currentCompornents = currentPath.split("/");
		String[] pastCompornents = pastPath.split("/");
		int cIndex = currentCompornents.length + 1;
		int pIndex = pastCompornents.length + 1;

		int[][] table;
		table = new int[cIndex][pIndex];

		// System.out.println("Start");
		for (int i = 1; i < cIndex; i++) {
			for (int j = 1; j < pIndex; j++) {
				int match = currentCompornents[i - 1].equals(pastCompornents[j - 1]) ? 1 : 0;
				int value1 = Math.max(table[i - 1][j - 1] + match, table[i - 1][j]);
				int value2 = Math.max(value1, table[i][j - 1]);
				table[i][j] = value2;
				// System.out.print(table[i][j]+" ");
			}
			// System.out.println("");
		}

		int lcs = 0;
		for (int x = 0; x < cIndex; x++) {
			for (int y = 0; y < pIndex; y++) {
				if (lcs < table[x][y]) {
					lcs = table[x][y];
				}
				// System.out.println(table[x][y]);
			}
		}
		// System.out.println(lcs);
		return lcs;
	}
}
