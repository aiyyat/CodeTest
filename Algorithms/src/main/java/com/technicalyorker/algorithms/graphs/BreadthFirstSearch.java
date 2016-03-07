package com.technicalyorker.algorithms.graphs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Finding a path using Breadth first search.
 * 
 * @author achuth
 *
 */
public class BreadthFirstSearch extends GraphSearch {

	public BreadthFirstSearch(Graph g, int root) {
		super(g, root);
	}

	protected void formConnections(int parent) {
		Queue<Integer> que = new LinkedList<Integer>();
		que.add(parent);
		poll(que);
	}

	private void poll(Queue<Integer> que) {
		while (!que.isEmpty()) {
			int top = que.poll();
			for (int child : getG().adj(top)) {
				if (!marked[child]) {
					connections[child] = top;
					que.add(child);
				}
			}
			marked[top] = true;
		}
	}

	public static void main(String[] args) {
		InputStream is = new ByteArrayInputStream("7 5 0 1 1 2 3 4 5 6 1 5\n".getBytes());
		Graph g = new Graph(is);
		g.print();
		BreadthFirstSearch p = new BreadthFirstSearch(g, 0);
		System.out.println(p.printConnection(6));
	}
}
