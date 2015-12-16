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
		String[] currentCompornents = currentPath.split("/");
		String[] pastCompornents = pastPath.split("/");
		// TODO length+1
		int l1 = currentCompornents.length;
		int l2 = pastCompornents.length;
		if (l1 == 1 && l2 == 1 && (currentCompornents[0].equals("") || pastCompornents[0].equals(""))){
			return 0;
		}
		int[][] table = new int[l1][l2];
		int maxLen = 0;
		for (int i = 0; i < l1; i++) {
			if (currentCompornents[i].equals(pastCompornents[0])) {
				table[i][0] = 1;
				maxLen = 1;
			} else {
				table[i][0] = 0;
			}
		}
		for (int i = 0; i < l2; i++) {
			if (pastCompornents[i].equals(currentCompornents[0])) {
				table[0][i] = 1;
				maxLen = 1;
			} else {
				table[0][i] = 0;
			}
		}
		
		for (int i = 1;i < l1;i++){
			for (int j = 1;j < l2;j++){
				if(currentCompornents[i].equals(pastCompornents[j])){
					table[i][j] = table[i-1][j-1] + 1;
					maxLen = (table[i][j] > maxLen) ? table[i][j] : maxLen;
				}
				else{
					table[i][j] = 0;
				}
			}
		}
		return maxLen;
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
