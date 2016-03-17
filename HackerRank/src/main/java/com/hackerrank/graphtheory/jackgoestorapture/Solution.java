package com.hackerrank.graphtheory.jackgoestorapture;

import java.io.InputStream;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		System.out.println(new Solution().perform(System.in));
	}

	int V;
	int E;
	List<Edge>[] vs;
	int begin = 1;
	int end;
	static long then;

	public String perform(InputStream is) {
		Scanner s = new Scanner(is);
		try {
			end = s.nextInt();
			V = end + 1;
			E = s.nextInt();
			distance = new int[V];
			vs = new LinkedList[V];
			qc = new boolean[V];
			for (int i = 0; i < V; i++) {
				vs[i] = new LinkedList<Edge>();
				if (i > 1) {
					distance[i] = Integer.MAX_VALUE;
				}
			}
			for (int i = 0; i < E; i++) {
				int one = s.nextInt();
				int other = s.nextInt();
				int weight = s.nextInt();
				vs[one].add(new Edge(one, other, weight));
				vs[other].add(new Edge(other, one, weight));
			}
			q.add(begin);
			perform();
			if (distance[end] != Integer.MAX_VALUE) {
				return "" + distance[end];
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
		return "NO PATH EXISTS";
	}

	Queue<Integer> q = new PriorityQueue<Integer>();
	int[] distance;

	private void perform() {
		while (!q.isEmpty()) {
			int first = q.poll();
			qc[first] = false;
			for (Edge e : vs[first]) {
				int other = e.other;
				int max = Math.max(e.w, distance[first]);
				if (distance[other] > max) {
					distance[other] = max;
					if (!qc[other]) {
						q.add(other);
					}
					qc[other] = true;
				}
			}
		}
	}

	boolean[] qc;

	class Edge {
		int w;
		int one;
		int other;
		boolean visited = false;

		public Edge(int one, int other, int w) {
			super();
			this.w = w;
			this.one = one;
			this.other = other;
		}

		public int getOther(int other) {
			if (one == other) {
				return this.other;
			} else {
				return one;
			}
		}

		@Override
		public String toString() {
			return one + "-" + w + "->" + other;
		}
	}
}