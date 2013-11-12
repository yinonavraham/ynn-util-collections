package ynn.util.collections.graphs;

abstract class AbstractEdge<
		VK, 
		VT, 
		ET, 
		GRAPH extends GraphBase<VK, VT, ET, GRAPH, VERTEX, EDGE>,
		VERTEX extends VertexBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		EDGE extends EdgeBase<VK, VT, ET, GRAPH, VERTEX, EDGE>> 
		implements EdgeBase<VK, VT, ET, GRAPH, VERTEX, EDGE> {
	
	private final GRAPH graph;
	private final VERTEX vertex1;
	private final VERTEX vertex2;
	private final ET payload;
	
	AbstractEdge(GRAPH graph, VERTEX vertex1, VERTEX vertex2, ET payload) {
		this.graph = graph;
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.payload = payload;
	}

	@Override
	public GRAPH graph() {
		return this.graph;
	}

	@Override
	public ET payload() {
		return this.payload;
	}

	@Override
	public VERTEX vertex1() {
		return this.vertex1;
	}

	@Override
	public VERTEX vertex2() {
		return this.vertex2;
	}
	
	@Override
	public String toString() {
		return String.format("%s---[%s]---%s", this.vertex1, this.payload, this.vertex2);
	}
	
}
