package Calculator;

import java.util.ArrayList;
import java.util.List;

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
		int cIndex = currentCompornents.length;
		int pIndex = pastCompornents.length;
		
		String[][] table;
		table = new String[cIndex][pIndex];
		
		for (int i = 1;i < cIndex-1;i++){
			for (int j = 1;j < pIndex;j++){
				int match = if (currentCompornents[i-1].equals(anObject)))
			}
		}
		/**
		 for each table[MAX_Y][MAX_X] = 0  // DP表を初期化
			for y = [1, a.length]:
  				for x = [1, b.length]:
    				match = ( a[y-1] == b[x-1] ? 1 : 0 )
    				value = max(table[y-1][x-1] + match, table[y-1][x], table[y][x-1])
    				table[y][x] = value

		printLine t[a.length][b.length]
		**/
		
		/**
		String[] currentCompornents = currentPath.split("/");
		String[] pastCompornents = pastPath.split("/");
		int cIndex = currentCompornents.length - 1;
		int pIndex = pastCompornents.length - 1;
		int ctEquals = 0;
		List<String> subSequence = new ArrayList<String>();
		for (;cIndex >= 0;cIndex--) {
			String cPath = currentCompornents[cIndex];
			int i = pIndex;
			//System.out.print(cPath+":"+i+"-->"+pastCompornents[i]+"\n");
			for (; i >= 0; i--) {
				//System.out.println("---"+cPath+":"+i+"-->"+pastCompornents[i]);
				if (pastCompornents[i].equals(cPath)) {
					subSequence.add(0,cPath);
					i--;
					break;
				}
			}
			if (i >= 0){
				//System.out.println("------"+cPath+":"+i+"-->"+pastCompornents[i]);
				pIndex = i;
			}
			else{
				if(pastCompornents[0].equals(cPath)){
					pIndex = 0;
				}
			}
		}
		System.out.println(subSequence);
		return 0;
		**/
	}
}
