import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import prob.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		int numProbs = Integer.parseInt(input.readLine());
		for (int i = 0; i < numProbs; i++) {
			String[] line = input.readLine().split(" ");
			float maxJump = Float.parseFloat(line[0]);
			int numTrees = Integer.parseInt(line[1]);
			Problem prob = new Problem(maxJump, numTrees);
			for (int j = 0; j < numTrees; j++) {
				String[] treeInfo = input.readLine().split(" ");
				prob.addTree(Integer.parseInt(treeInfo[0]), Integer.parseInt(treeInfo[1]),
						Integer.parseInt(treeInfo[2]), Integer.parseInt(treeInfo[3]));
			}
			List<Integer> list = prob.solve();
			if (list.isEmpty())
				System.out.println(-1);
			else {
				for (int k = 0; k < list.size(); k++) {
					if (k == list.size() - 1)
						System.out.println(list.get(k));
					else
						System.out.print(list.get(k) + " ");
				}
			}
		}

	}
}
