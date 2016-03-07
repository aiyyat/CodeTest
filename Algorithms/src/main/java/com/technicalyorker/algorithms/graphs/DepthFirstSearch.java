package com.technicalyorker.algorithms.graphs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Stack;

/**
 * Finding a path using Depth first search.
 * 
 * @author achuth
 *
 */
public class DepthFirstSearch {
	private int root;
	private Graph g;
	boolean[] marked;
	int[] connections;
	private Stack<Integer> s = new Stack<Integer>();

	public DepthFirstSearch(Graph g, int root) {
		this.g = g;
		this.root = root;
		marked = new boolean[g.getEdges()];
		connections = new int[g.getEdges()];
		marked[root] = true;
		formConnections(root);
	}

	private void formConnections(int parent) {
		for (int a : g.adj(parent)) {
			if (!marked[a]) {
				connections[a] = parent;
				marked[a] = true;
				formConnections(a);
			}
		}
	}

	public Stack<Integer> printConnection(int end) {
		if (marked[end]) {
			return check(end);
		} else {
			System.out.println("No Connection");
			return null;
		}
	}

	private Stack<Integer> check(int end) {
		s.push(end);
		if (end != root) {
			int parent = connections[end];
			check(parent);
		}
		return s;
	}

	public static void main(String[] args) {
		InputStream is = new ByteArrayInputStream("7 5 0 1 1 2 3 4 5 6 1 5\n".getBytes());
		Graph g = new Graph(is);
		g.print();
		DepthFirstSearch p = new DepthFirstSearch(g, 0);
		System.out.println(p.printConnection(6));
	}
}
