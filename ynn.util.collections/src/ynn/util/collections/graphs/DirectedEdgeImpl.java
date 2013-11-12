package ynn.util.collections.graphs;

class DirectedEdgeImpl<VK, VT, ET> extends AbstractEdge<VK, VT, ET, DirectedGraph<VK, VT, ET>, DirectedVertex<VK, VT, ET>, DirectedEdge<VK, VT, ET>> implements DirectedEdge<VK, VT, ET> {

	private final Direction direction;

	DirectedEdgeImpl(DirectedGraph<VK, VT, ET> graph, DirectedVertex<VK, VT, ET> vertex1, DirectedVertex<VK, VT, ET> vertex2, ET payload, Direction direction) {
		super(graph, vertex1, vertex2, payload);
		this.direction = direction;
	}

	@Override
	public Direction direction() {
		return this.direction;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.vertex1());
		if (Direction.Both.equals(this.direction) || Direction.Vertex1.equals(this.direction)) sb.append("<");
		sb.append(String.format("---[%s]---", this.payload()));
		if (Direction.Both.equals(this.direction) || Direction.Vertex2.equals(this.direction)) sb.append(">");
		sb.append(this.vertex2());
		return sb.toString();
	}

}
