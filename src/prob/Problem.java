package prob;

import java.util.List;

import graph.*;

public class Problem {

	private Graph graph;

	public Problem(float maxJump, int numTrees) {
		graph = new Graph(maxJump, numTrees);
	}

	public void addTree(int x, int y, int numMonkeys, int maxJumps) {
		Tree tree = new Tree(x, y, numMonkeys, maxJumps);
		graph.addTree(tree);
	}

	public List<Integer> solve() {
		return graph.solve();
	}

}
