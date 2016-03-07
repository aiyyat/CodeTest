package com.technicalyorker.algorithms.graphs;

import java.util.Stack;

public abstract class GraphSearch {
	private int root;
	private Graph g;
	private Stack<Integer> s = new Stack<Integer>();
	boolean[] marked;
	int[] connections;

	public GraphSearch(Graph g, int root) {
		this.g = g;
		this.root = root;
		marked = new boolean[g.getEdges()];
		connections = new int[g.getEdges()];
		marked[root] = true;
		formConnections(root);
	}

	protected abstract void formConnections(int parent);

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

	public int getRoot() {
		return root;
	}

	public void setRoot(int root) {
		this.root = root;
	}

	public Graph getG() {
		return g;
	}

	public void setG(Graph g) {
		this.g = g;
	}
}
