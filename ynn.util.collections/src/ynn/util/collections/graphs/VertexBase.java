package ynn.util.collections.graphs;

import java.util.Collection;

interface VertexBase<
		VK, 
		VT, 
		ET, 
		GRAPH extends GraphBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		VERTEX extends VertexBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		EDGE extends EdgeBase<VK, VT, ET, GRAPH, VERTEX, EDGE>> {
	
	GRAPH graph();

	VK key();
	
	VT payload();
	
	Collection<EDGE> edges();
	
	EDGE connect(VERTEX vertex, ET connectionPayload);

	boolean remove(EDGE edge);
	
}
