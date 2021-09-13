package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

	private float maxJump;
	private List<Tree> vertices;
	private List<Edge> edges;
	private List<Tree> clones;
	private Tree source;
	private int numMonkeys;
	private int numTrees;

	public Graph(float maxJump, int numTrees) {
		this.maxJump = maxJump;
		vertices = new LinkedList<>();
		edges = new LinkedList<>();
		source = new Tree();
		clones = new LinkedList<>();
		numMonkeys = 0;
		this.numTrees = numTrees;
	}

	public void addTree(Tree tree) {
		numMonkeys += tree.getNumMonkeys();
		Tree clone = new Tree();
		addEdge(source, tree, tree.getNumMonkeys());
		addEdge(tree, clone, tree.getMaxJumps());
		for (Tree entry : vertices) {
			if (entry.isClose(tree, maxJump)) {
				addEdge(clone, entry, tree.getMaxJumps());
				addEdge(clones.get(entry.getPos()), tree, entry.getMaxJumps());
			}
		}
		tree.setPos(vertices.size());
		clone.setPos(vertices.size() + numTrees);
		vertices.add(tree);
		clones.add(clone);
	}

	private void addEdge(Tree source, Tree dest, int weight) {
		Edge e1 = new Edge(source, dest, weight);
		Edge e2 = new Edge(dest, source, 0);
		edges.add(e1);
		edges.add(e2);
		source.addEdge(e1);
		dest.addEdge(e2);
	}

	public List<Integer> solve() {
		vertices.addAll(clones);
		source.setPos(vertices.size());
		vertices.add(source);
	//	System.out.println("num vertices=" + vertices.size());
	//	System.out.println("num edges=" + edges.size());
		List<Integer> result = new LinkedList<>();
		for (int i = 0; i < numTrees; i++) {
			//System.out.println(i);
			if (solve(source, vertices.get(i)))
				result.add(i);
		}
		return result;
	}

	public boolean solve(Tree source, Tree dest) {
		int num = vertices.size();
		int[][] flow = new int[num][num];
		Tree[] via = new Tree[num];
		int flowValue = 0;
		int increment;
		while ((increment = findPath(flow, source, dest, via)) != 0) {
			flowValue += increment;
			Tree t = dest;
			while (t != source) {
				int tPos = t.getPos();
				Tree o = via[tPos];
				int oPos = o.getPos();
				flow[oPos][tPos] += increment;
				flow[tPos][oPos] -= increment;
				t = o;
			}
		}
		return flowValue >= numMonkeys;
	}

	private int findPath(int[][] flow, Tree source, Tree dest, Tree[] via) {

		int num = vertices.size();
		Queue<Tree> fifo = new LinkedList<Tree>();
		boolean[] found = new boolean[num];
		int[] path = new int[num];
		fifo.add(source);
		int sourcePos = source.getPos();
		found[sourcePos] = true;
		via[sourcePos] = source;
		path[sourcePos] = Integer.MAX_VALUE;
		do {
			Tree o = fifo.remove();
			List<Edge> edgeList = o.getEdges();
			for (Edge e : edgeList) {
				Tree d = e.getDest();
				int dPos = d.getPos();
				int oPos = o.getPos();
				int residue = e.getWeight() - flow[oPos][dPos];
				if (!found[dPos] && residue > 0) {
					via[dPos] = o;
					path[dPos] = Math.min(path[oPos], residue);
					if (d.equals(dest))
						return path[dPos];
					fifo.add(d);
					found[dPos] = true;

				}
			}

		} while (!fifo.isEmpty());
		return 0;
	}

}
