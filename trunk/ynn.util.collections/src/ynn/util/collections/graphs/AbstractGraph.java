package ynn.util.collections.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractGraph<
		VK, 
		VT, 
		ET, 
		GRAPH extends GraphBase<VK, VT, ET, GRAPH, VERTEX, EDGE>,
		VERTEX extends VertexBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		EDGE extends EdgeBase<VK, VT, ET, GRAPH, VERTEX, EDGE>> 
		implements GraphBase<VK, VT, ET, GRAPH, VERTEX, EDGE> {
	
	private final Collection<EDGE> edges;
	private final Map<VK, VERTEX> vertices;
	
	AbstractGraph() {
		this.vertices = new HashMap<VK, VERTEX>();
		this.edges = new ArrayList<EDGE>();
	}

	@Override
	public Collection<VERTEX> vertices() {
		return Collections.unmodifiableCollection(this.vertices.values());
	}

	@Override
	public Collection<EDGE> edges() {
		return Collections.unmodifiableCollection(this.edges);
	}

	@Override
	public VERTEX createVertex(VK key, VT payload) {
		VERTEX vertex = createVertexImpl(key, payload);
		this.vertices.put(vertex.key(), vertex);
		return vertex;
	}
	
	abstract VERTEX createVertexImpl(VK key, VT payload);

	@Override
	public VERTEX getVertex(VK key) {
		return this.vertices.get(key);
	}

	@Override
	public boolean exists(VK key) {
		return this.vertices.containsKey(key);
	}

	@Override
	public boolean connected(VERTEX vertex1, VERTEX vertex2) {
		// Iterate over all edges of vertex #1
		for (EDGE edge : vertex1.edges())
		{
			// If one of the vertices in the edge is vertex #2 - then #1 & #2 are connected
			if (edge.vertex1().equals(vertex2) || edge.vertex2().equals(vertex2))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public EDGE connect(VERTEX vertex1, VERTEX vertex2, ET connectionPayload) {
		checkCanConnect(vertex1, vertex2);
		EDGE edge = createEdgeImpl(vertex1, vertex2, connectionPayload);
		addEdge(edge);
		return edge;
	}

	protected void checkCanConnect(VERTEX vertex1, VERTEX vertex2) {
		if (vertex1.graph() != vertex2.graph())
			throw new IllegalArgumentException("Both vertices must be of the same graph");
	}
	
	protected void addEdge(EDGE edge)
	{
		this.edges.add(edge);
		addEdge(edge.vertex1(), edge);
		addEdge(edge.vertex2(), edge);
	}
	
	abstract void addEdge(VERTEX vertex, EDGE edge);

	abstract EDGE createEdgeImpl(VERTEX vertex1, VERTEX vertex2, ET connectionPayload);

	@Override
	public boolean remove(VK vertexKey) {
		VERTEX vertex = getVertex(vertexKey);
		if (vertex == null) return false;
		Collection<EDGE> vertexEdges = new ArrayList<EDGE>(vertex.edges());
		for (EDGE edge : vertexEdges)
		{
			remove(edge);
		}
		return this.vertices.remove(vertexKey) != null;
	}

	@Override
	public boolean remove(EDGE edge) {
		edge.vertex1().remove(edge);
		edge.vertex2().remove(edge);
		return this.edges.remove(edge);
	}

}
