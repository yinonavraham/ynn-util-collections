package ynn.util.collections.graphs;

interface EdgeBase<
		VK, 
		VT, 
		ET, 
		GRAPH extends GraphBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		VERTEX extends VertexBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		EDGE extends EdgeBase<VK, VT, ET, GRAPH, VERTEX, EDGE>> {
	
	GRAPH graph();
	
	ET payload();
	
	VERTEX vertex1();
	
	VERTEX vertex2();

}
