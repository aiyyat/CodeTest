package com.technicalyorker.algorithms.graphs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph {
	private int vertices;
	private int edges;
	private List<Integer>[] elements;

	@SuppressWarnings("unchecked")
	public Graph(InputStream is) {
		Scanner s = new Scanner(is);
		edges = s.nextInt();
		vertices = s.nextInt();
		elements = (List<Integer>[]) new ArrayList[edges];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < vertices; i++) {
			addEdge(s.nextInt(), s.nextInt());
		}
		s.close();
	}

	public void addEdge(int v, int w) {
		elements[v].add(w);
		elements[w].add(v);
	}

	public List<Integer> adj(int v) {
		return elements[v];
	}

	public void print() {
		for (int i = 0; i < elements.length; i++) {
			System.out.println(i + "->" + elements[i]);
		}
	}

	public static void main(String[] args) {
		InputStream is = new ByteArrayInputStream("7 5 1 2 2 3 4 5 5 6 2 6\n".getBytes());
		Graph g = new Graph(is);
		g.print();
	}

	public int getVertices() {
		return vertices;
	}

	public int getEdges() {
		return edges;
	}

}
