package ynn.util.collections.graphs;

class EdgeImpl<VK, VT, ET> extends AbstractEdge<VK, VT, ET, Graph<VK, VT, ET>, Vertex<VK, VT, ET>, Edge<VK, VT, ET>> implements Edge<VK, VT, ET> {

	EdgeImpl(Graph<VK, VT, ET> graph, Vertex<VK, VT, ET> vertex1, Vertex<VK, VT, ET> vertex2, ET payload) {
		super(graph, vertex1, vertex2, payload);
	}
	
}
