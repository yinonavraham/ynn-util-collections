package ynn.util.collections.graphs;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class Graphs {
	
	public static <VK, VT, ET> Graph<VK, VT, ET> createGraph()
	{
		return new GraphImpl<VK, VT, ET>();
	}
	
	public static <VK, VT, ET> DirectedGraph<VK, VT, ET> createDirectedGraph()
	{
		return new DirectedGraphImpl<VK, VT, ET>();
	}
	
	public static <VK, VT, ET, 
		GRAPH extends GraphBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		VERTEX extends VertexBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		EDGE extends EdgeBase<VK, VT, ET, GRAPH, VERTEX, EDGE>> 
	List<Entry<VERTEX,EDGE>> shortestPath(GRAPH graph, VERTEX source, VERTEX target, EdgesProvider<VERTEX, EDGE> edgesProvider)
	{
		return bfs(graph, source, target, edgesProvider);
	}
	
	public static <VK, VT, ET, 
		GRAPH extends GraphBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		VERTEX extends VertexBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		EDGE extends EdgeBase<VK, VT, ET, GRAPH, VERTEX, EDGE>> 
	List<Entry<VERTEX,EDGE>> shortestPath(GRAPH graph, VERTEX source, VERTEX target, WeightProvider<EDGE> weightProvider)
	{
		// TODO Implement shortestPath(GraphBase<VK, VT, ET, GRAPH, VERTEX, EDGE> graph, VERTEX source, VERTEX target)
		return Collections.emptyList();
	}
	
	public static interface WeightProvider<EDGE> {
		int getWeight(EDGE edge);
	}
	
	public static interface EdgesProvider<VERTEX, EDGE> {
		Collection<EDGE> getEdges(VERTEX vertex);
	}
	
	/**
	 * Calculate the path from source to target in a graph, using the BFS algorithm (Breadth-First Search)
	 * @param graph 
	 * @param source
	 * @param target
	 * @param edgesProvider a service for getting relevant edges from a vertex
	 * @return an ordered list of entries, each entry includes a vertex and the edge used to get to it from the previous vertex.
	 * The edge associated with the first vertex (the source) is always <code>null</code>. An empty list is returned in case no path was found. 
	 */
	private static <VK, VT, ET, 
		GRAPH extends GraphBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		VERTEX extends VertexBase<VK, VT, ET, GRAPH, VERTEX, EDGE>, 
		EDGE extends EdgeBase<VK, VT, ET, GRAPH, VERTEX, EDGE>> 
	List<Entry<VERTEX,EDGE>> bfs(GRAPH graph, VERTEX source, VERTEX target, EdgesProvider<VERTEX, EDGE> edgesProvider) {
		class EntryImpl implements Entry<VERTEX,EDGE> {
			
			private final VERTEX vertex;
			private EDGE edge;
			
			public EntryImpl(VERTEX vertex, EDGE edge) {
				this.vertex = vertex;
				this.edge = edge;
			}

			@Override
			public VERTEX getKey() {
				return this.vertex;
			}

			@Override
			public EDGE getValue() {
				return this.edge;
			}

			@Override
			public EDGE setValue(EDGE edge) {
				this.edge = edge;
				return this.edge;
			}
			
		}
		
		LinkedList<Entry<VERTEX,EDGE>> path = new LinkedList<Entry<VERTEX,EDGE>>();
		
		Map<VK, VERTEX> visited = new HashMap<VK, VERTEX>();
		visited.put(source.key(), source);
		Deque<Entry<VERTEX,EDGE>> queue = new ArrayDeque<Entry<VERTEX,EDGE>>();
		queue.push(new EntryImpl(source, null));
		
		while (!queue.isEmpty()) {
			Entry<VERTEX, EDGE> entry = queue.pop();
			VERTEX vertex = entry.getKey();
			if (vertex == target) return path;
			Collection<EDGE> edges = edgesProvider.getEdges(vertex);
			for (EDGE edge : edges) {
				VERTEX otherVertex = vertex == edge.vertex1() ? edge.vertex2() : edge.vertex1();
				if (!visited.containsKey(otherVertex.key())) {
					visited.put(otherVertex.key(), otherVertex);
					queue.add(new EntryImpl(otherVertex, edge));
				}
			}
		}
		
		return path;
	}
	
}
