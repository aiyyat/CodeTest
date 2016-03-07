package com.technicalyorker.algorithms.graphs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Finding a path using Depth first search.
 * 
 * @author achuth
 *
 */
public class DepthFirstSearch extends GraphSearch {

	public DepthFirstSearch(Graph g, int root) {
		super(g, root);
	}

	protected void formConnections(int parent) {
		for (int a : getG().adj(parent)) {
			if (!marked[a]) {
				connections[a] = parent;
				marked[a] = true;
				formConnections(a);
			}
		}
	}

	public static void main(String[] args) {
		InputStream is = new ByteArrayInputStream("7 5 0 1 1 2 3 4 5 6 1 5\n".getBytes());
		Graph g = new Graph(is);
		g.print();
		DepthFirstSearch p = new DepthFirstSearch(g, 0);
		System.out.println(p.printConnection(6));
	}
}
