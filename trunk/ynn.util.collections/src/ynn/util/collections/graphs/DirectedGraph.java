package ynn.util.collections.graphs;

import ynn.util.collections.graphs.DirectedEdge.Direction;

public interface DirectedGraph<VK, VT, ET> extends GraphBase<VK, VT, ET, DirectedGraph<VK, VT, ET>, DirectedVertex<VK, VT, ET>, DirectedEdge<VK, VT, ET>> {
	
	DirectedEdge<VK, VT, ET> connect(DirectedVertex<VK, VT, ET> vertex1, DirectedVertex<VK, VT, ET> vertex2, ET connectionPayload);
	
	DirectedEdge<VK, VT, ET> connect(DirectedVertex<VK, VT, ET> vertex1, DirectedVertex<VK, VT, ET> vertex2, ET connectionPayload, Direction direction);

}
