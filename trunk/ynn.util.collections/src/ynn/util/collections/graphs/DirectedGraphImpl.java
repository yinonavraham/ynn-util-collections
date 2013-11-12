package ynn.util.collections.graphs;

import ynn.util.collections.graphs.DirectedEdge.Direction;

class DirectedGraphImpl<VK, VT, ET> 
	extends AbstractGraph<VK, VT, ET, DirectedGraph<VK, VT, ET>, DirectedVertex<VK, VT, ET>, DirectedEdge<VK, VT, ET>>
	implements DirectedGraph<VK, VT, ET> {

	@Override
	public DirectedEdge<VK, VT, ET> connect(DirectedVertex<VK, VT, ET> vertex1, DirectedVertex<VK, VT, ET> vertex2, ET connectionPayload, Direction direction) {
		checkCanConnect(vertex1, vertex2);
		DirectedEdge<VK, VT, ET> edge = new DirectedEdgeImpl<VK, VT, ET>(this, vertex1, vertex2, connectionPayload, direction);
		addEdge(edge);
		return edge;
	}

	@Override
	DirectedVertex<VK, VT, ET> createVertexImpl(VK key, VT payload) {
		return new DirectedVertexImpl<VK, VT, ET>(this, key, payload);
	}

	@Override
	void addEdge(DirectedVertex<VK, VT, ET> vertex, DirectedEdge<VK, VT, ET> edge) {
		((DirectedVertexImpl<VK, VT, ET>)vertex).addEdge(edge);
	}

	@Override
	DirectedEdge<VK, VT, ET> createEdgeImpl(DirectedVertex<VK, VT, ET> vertex1, DirectedVertex<VK, VT, ET> vertex2, ET connectionPayload) {
		return new DirectedEdgeImpl<VK, VT, ET>(this, vertex1, vertex2, connectionPayload, null);
	}


}
