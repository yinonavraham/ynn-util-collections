package ynn.util.collections.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

abstract class AbstractVertex<
		VK, 
		VT, 
		ET, 
		GRAPH extends GraphBase<VK, VT, ET, GRAPH, VERTEX, EDGE>,
		VERTEX extends VertexBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		EDGE extends EdgeBase<VK, VT, ET, GRAPH, VERTEX, EDGE>> 
		implements VertexBase<VK, VT, ET, GRAPH, VERTEX, EDGE> {
	
	private final GRAPH graph;
	private final VK key;
	private final VT payload;
	private final Collection<EDGE> edges;
	
	AbstractVertex(GRAPH graph, VK key, VT payload) {
		this.graph = graph;
		this.key = key;
		this.payload = payload;
		this.edges = new ArrayList<EDGE>();
	}

	@Override
	public GRAPH graph() {
		return this.graph;
	}

	@Override
	public VK key() {
		return this.key;
	}

	@Override
	public VT payload() {
		return this.payload;
	}

	@Override
	public Collection<EDGE> edges() {
		return Collections.unmodifiableCollection(this.edges);
	}

	@Override
	public EDGE connect(VERTEX vertex, ET connectionPayload) {
		@SuppressWarnings("unchecked")
		EDGE edge = this.graph.connect((VERTEX)this, vertex, connectionPayload);
		return edge;
	}
	
	void addEdge(EDGE edge) {
		this.edges.add(edge);
	}
	
	@Override
	public boolean remove(EDGE edge)
	{
		boolean removed = this.edges.remove(edge);
		if (removed)
		{
			this.graph.remove(edge);
		}
		return removed;
	}
	
	@Override
	public String toString() {
		return String.format("(%s)", this.key);
	}

}
