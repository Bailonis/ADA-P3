package graph;

import java.util.LinkedList;
import java.util.List;

public class Tree {

	private int x, y, numMonkeys, maxJumps, distanceToDest, pos;
	private List<Edge> edges;

	public Tree(int x, int y, int numMonkeys, int maxJumps) {
		this.x = x;
		this.y = y;
		this.numMonkeys = numMonkeys;
		this.maxJumps = maxJumps;
		this.edges = new LinkedList<>();
		this.pos = 0;
	}

	public Tree() {
		this.x = -1;
		this.y = -1;
		this.numMonkeys = 0;
		this.maxJumps = Integer.MAX_VALUE;
		this.edges = new LinkedList<>();
		this.pos = 0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getNumMonkeys() {
		return numMonkeys;
	}

	public int getMaxJumps() {
		return maxJumps;
	}

	public boolean isClose(Tree tree, float maxJump) {
		if (tree.getX() == -1 && tree.getY() == -1)
			return false;
		float distance = (float) Math.sqrt(Math.pow(x - tree.getX(), 2) + Math.pow(y - tree.getY(), 2));
		return distance <= maxJump;
	}

	public void setDistance(int i) {
		this.distanceToDest = i;
	}

	public int getDistance() {
		return distanceToDest;
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}

	public List<Edge> getEdges() {
		return edges;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
}
