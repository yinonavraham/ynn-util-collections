package ynn.util.collections.graphs;


class GraphImpl<VK, VT, ET> extends AbstractGraph<VK, VT, ET, Graph<VK, VT, ET>, Vertex<VK, VT, ET>, Edge<VK, VT, ET>> implements Graph<VK, VT, ET> {

	@Override
	Vertex<VK, VT, ET> createVertexImpl(VK key, VT payload) {
		return new VertexImpl<VK, VT, ET>(this, key, payload);
	}

	@Override
	void addEdge(Vertex<VK, VT, ET> vertex, Edge<VK, VT, ET> edge) {
		((VertexImpl<VK, VT, ET>)vertex).addEdge(edge);
	}

	@Override
	Edge<VK, VT, ET> createEdgeImpl(Vertex<VK, VT, ET> vertex1, Vertex<VK, VT, ET> vertex2, ET connectionPayload) {
		return new EdgeImpl<VK, VT, ET>(this, vertex1, vertex2, connectionPayload);
	}
	
}
