package com.technicalyorker.algorithms.graphs;

public class Edge implements Comparable<Edge> {
	int v;
	int w;
	Double weight;

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int compareTo(Edge o) {
		return this.weight.compareTo(o.weight);
	}
}
