package ynn.util.collections.graphs;

import java.util.Collection;

public interface DirectedVertex<VK, VT, ET> extends VertexBase<VK, VT, ET, DirectedGraph<VK, VT, ET>, DirectedVertex<VK, VT, ET>, DirectedEdge<VK, VT, ET>> {
	
	Collection<DirectedEdge<VK, VT, ET>> inboundEdges();

	Collection<DirectedEdge<VK, VT, ET>> outboundEdges();

}
