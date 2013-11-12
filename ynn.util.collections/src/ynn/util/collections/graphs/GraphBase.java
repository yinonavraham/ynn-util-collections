package ynn.util.collections.graphs;

import java.util.Collection;

interface GraphBase<
		VK, 
		VT, 
		ET, 
		GRAPH extends GraphBase<VK, VT, ET, GRAPH, VERTEX, EDGE>,
		VERTEX extends VertexBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		EDGE extends EdgeBase<VK, VT, ET, GRAPH, VERTEX, EDGE>> {
	
	Collection<VERTEX> vertices();
	
	Collection<EDGE> edges();
	
	VERTEX createVertex(VK key, VT payload);
	
	VERTEX getVertex(VK key);
	
	boolean exists(VK key);
	
	boolean connected(VERTEX vertex1, VERTEX vertex2);
	
	EDGE connect(VERTEX vertex1, VERTEX vertex2, ET connectionPayload);
	
	boolean remove(VK vertexKey);
	
	boolean remove(EDGE edge);

}
