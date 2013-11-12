package ynn.util.collections.graphs;


class VertexImpl<VK, VT, ET> extends AbstractVertex<VK, VT, ET, Graph<VK, VT, ET>, Vertex<VK, VT, ET>, Edge<VK, VT, ET>> implements Vertex<VK, VT, ET> {

	VertexImpl(Graph<VK, VT, ET> graph, VK key, VT payload) {
		super(graph, key, payload);
	}
	
}
