package graph;

public class Edge {

	private Tree source, dest;
	private int weight;

	public Edge(Tree source, Tree dest, int weight) {
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}

	public Tree getSource() {
		return source;
	}

	public Tree getDest() {
		return dest;
	}

	public int getWeight() {
		return weight;
	}

}
