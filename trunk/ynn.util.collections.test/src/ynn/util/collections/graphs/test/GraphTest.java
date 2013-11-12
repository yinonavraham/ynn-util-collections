package ynn.util.collections.graphs.test;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;

import ynn.util.collections.graphs.DirectedEdge;
import ynn.util.collections.graphs.DirectedEdge.Direction;
import ynn.util.collections.graphs.DirectedGraph;
import ynn.util.collections.graphs.DirectedVertex;
import ynn.util.collections.graphs.Edge;
import ynn.util.collections.graphs.Graph;
import ynn.util.collections.graphs.Graphs;
import ynn.util.collections.graphs.Graphs.EdgesProvider;
import ynn.util.collections.graphs.Graphs.WeightProvider;
import ynn.util.collections.graphs.Vertex;

public class GraphTest {

	@Test
	public void test() {
		// Create a simple graph
		Graph<String,String,String> graph = Graphs.createGraph();
		// create vertices...
		Vertex<String, String, String> source = graph.createVertex("1", "A");
		Vertex<String, String, String> vertex2 = graph.createVertex("2", "B");
		Vertex<String, String, String> vertex3 = graph.createVertex("3", "C");
		Vertex<String, String, String> vertex4 = graph.createVertex("4", "D");
		Vertex<String, String, String> target = graph.createVertex("26", "Z");
		// Connect vertices
		source.connect(vertex2, null);
		source.connect(vertex3, null);
		vertex2.connect(vertex4, null);
		vertex3.connect(target, null);
		vertex4.connect(target, null);
		// Calculate simple shortest path from source to target (no weights on the edges)
		List<Entry<Vertex<String, String, String>, Edge<String, String, String>>> shortestPath = 
				Graphs.shortestPath(graph, source, target, new EdgesProvider<Vertex<String, String, String>, Edge<String, String, String>>() {
					@Override
					public Collection<Edge<String, String, String>> getEdges(Vertex<String, String, String> vertex) {
						return vertex.edges();
					}
				});
		print(graph);
		printPath(shortestPath);
		
		System.out.println();
		
		// Create a directed graph
		DirectedGraph<String,Void,Integer> directedGraph = Graphs.createDirectedGraph();
		// create vertices...
		DirectedVertex<String, Void, Integer> source1 = directedGraph.createVertex("1", null);
		DirectedVertex<String, Void, Integer> target1 = directedGraph.createVertex("26", null);
		// Create a weight provider
		WeightProvider<DirectedEdge<String, Void, Integer>> weightProvider = new WeightProvider<DirectedEdge<String,Void,Integer>>() {
			@Override
			public int getWeight(DirectedEdge<String, Void, Integer> edge) {
				return edge.payload();
			}
		};
		// Calculate the shortest path from source to target with respect to the weights on the edges
		List<Entry<DirectedVertex<String, Void, Integer>, DirectedEdge<String, Void, Integer>>> weightedShortestPath = 
				Graphs.shortestPath(directedGraph, source1, target1, weightProvider);
		print(directedGraph);
		printPath(weightedShortestPath);
	}

	private <V, E> void printPath(List<Entry<V, E>> path) {
		System.out.println("Path:");
		for (Entry<V, E> entry : path)
		{
			System.out.println("   Vertex: " + entry.getKey() + " - Edge: " + entry.getValue());
		}
	}

	private <VK, VT, ET> void print(Graph<VK, VT, ET> graph) {
		System.out.println("Graph:");
		System.out.println("   Vertices:");
		for (Vertex<VK, VT, ET> vertex : graph.vertices())
		{
			System.out.println("      " + vertex);
		}
		System.out.println("   Edges:");
		for (Edge<VK, VT, ET> edge : graph.edges())
		{
			System.out.println("      " + edge);
		}
	}

	private <VK, VT, ET> void print(DirectedGraph<VK, VT, ET> graph) {
		System.out.println("Graph:");
		System.out.println("   Vertices:");
		for (DirectedVertex<VK, VT, ET> vertex : graph.vertices())
		{
			System.out.println("      " + vertex);
		}
		System.out.println("   Edges:");
		for (DirectedEdge<VK, VT, ET> edge : graph.edges())
		{
			System.out.println("      " + edge);
		}
	}
	
	@Test
	public void testGraph()
	{
		Graph<String, String, String> graph = Graphs.createGraph();
		
		Vertex<String, String, String> vertex1 = graph.createVertex("1", "A");
		Vertex<String, String, String> vertex2 = graph.createVertex("2", "B");
		Vertex<String, String, String> vertex3 = graph.createVertex("3", "C");
		Vertex<String, String, String> vertex4 = graph.createVertex("4", "D");

		System.out.println("Init");
		vertex1.connect(vertex2, "1-2");
		vertex1.connect(vertex3, "1-3");
		vertex2.connect(vertex3, "2-3");
		Edge<String, String, String> edge3_4 = graph.connect(vertex3, vertex4, "3-4");
		Edge<String, String, String> edge4_4 = graph.connect(vertex4, vertex4, "4-4");
		
		print(graph);
		System.out.println("2-4 are connected: " + graph.connected(vertex2, vertex4));
		System.out.println("5 exists: " + graph.exists("5"));

		System.out.println("Create 5, connect 2-4, 1-2'");
		graph.connect(vertex2, vertex4, "2-4");
		graph.connect(vertex1, vertex2, "1-2'");
		graph.createVertex("5", "E");
		
		print(graph);
		System.out.println("2-4 are connected: " + graph.connected(vertex2, vertex4));
		System.out.println("5 exists: " + graph.exists("5"));

		System.out.println("remove 1");
		graph.remove(vertex1.key());
		
		print(graph);

		System.out.println("Remove 1");
		graph.remove(vertex1.key());
		
		print(graph);

		System.out.println("Remove 4-4");
		graph.remove(edge4_4);
		
		print(graph);

		System.out.println("Remove 3-4");
		vertex3.remove(edge3_4);
		
		print(graph);
	}
	
	@Test
	public void testDirectedGraph() {
		DirectedGraph<String, String, String> graph = Graphs.createDirectedGraph();
		
		DirectedVertex<String, String, String> vertex1 = graph.createVertex("1", "A");
		DirectedVertex<String, String, String> vertex2 = graph.createVertex("2", "B");
		DirectedVertex<String, String, String> vertex3 = graph.createVertex("3", "C");
		DirectedVertex<String, String, String> vertex4 = graph.createVertex("4", "D");

		System.out.println("Init");
		vertex1.connect(vertex2, "1-2");
		vertex1.connect(vertex3, "1-3");
		graph.connect(vertex2, vertex3, "2-3", Direction.Both);
		DirectedEdge<String, String, String> edge3_4 = graph.connect(vertex3, vertex4, "3-4", Direction.Vertex1);
		DirectedEdge<String, String, String> edge4_4 = graph.connect(vertex4, vertex4, "4-4", Direction.Vertex2);
		
		print(graph);
		System.out.println("2-4 are connected: " + graph.connected(vertex2, vertex4));
		System.out.println("5 exists: " + graph.exists("5"));

		System.out.println("Create 5, connect 2-4, 1-2'");
		graph.connect(vertex2, vertex4, "2-4");
		graph.connect(vertex1, vertex2, "1-2'");
		graph.createVertex("5", "E");
		
		print(graph);
		System.out.println("2-4 are connected: " + graph.connected(vertex2, vertex4));
		System.out.println("5 exists: " + graph.exists("5"));

		System.out.println("remove 1");
		graph.remove(vertex1.key());
		
		print(graph);

		System.out.println("Remove 1");
		graph.remove(vertex1.key());
		
		print(graph);

		System.out.println("Remove 4-4");
		graph.remove(edge4_4);
		
		print(graph);

		System.out.println("Remove 3-4");
		vertex3.remove(edge3_4);
		
		print(graph);
	}

}
