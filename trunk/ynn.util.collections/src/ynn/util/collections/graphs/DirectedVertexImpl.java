package ynn.util.collections.graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class DirectedVertexImpl<VK, VT, ET> extends AbstractVertex<VK, VT, ET, DirectedGraph<VK, VT, ET>, DirectedVertex<VK, VT, ET>, DirectedEdge<VK, VT, ET>> implements DirectedVertex<VK, VT, ET> {

	DirectedVertexImpl(DirectedGraph<VK, VT, ET> directedGraph, VK key, VT payload) {
		super(directedGraph, key, payload);
	}

	@Override
	public Collection<DirectedEdge<VK, VT, ET>> inboundEdges() {
		Collection<DirectedEdge<VK, VT, ET>> inboundEdges = new ArrayList<DirectedEdge<VK,VT,ET>>(edges().size());
		for (DirectedEdge<VK, VT, ET> edge : edges())
		{
			if (edge.direction() == null) continue;
			switch (edge.direction())
			{
				case Both:
					inboundEdges.add(edge);
					break;
				case Vertex1:
					if (edge.vertex1() == this) inboundEdges.add(edge);
					break;
				case Vertex2:
					if (edge.vertex2() == this) inboundEdges.add(edge);
					break;
			}
		}
		return Collections.unmodifiableCollection(inboundEdges);
	}

	@Override
	public Collection<DirectedEdge<VK, VT, ET>> outboundEdges() {
		Collection<DirectedEdge<VK, VT, ET>> outboundEdges = new ArrayList<DirectedEdge<VK,VT,ET>>(edges().size());
		for (DirectedEdge<VK, VT, ET> edge : edges())
		{
			if (edge.direction() == null) continue;
			switch (edge.direction())
			{
				case Both:
					outboundEdges.add(edge);
					break;
				case Vertex1:
					if (edge.vertex2() == this) outboundEdges.add(edge);
					break;
				case Vertex2:
					if (edge.vertex1() == this) outboundEdges.add(edge);
					break;
			}
		}
		return Collections.unmodifiableCollection(outboundEdges);
	}

}
