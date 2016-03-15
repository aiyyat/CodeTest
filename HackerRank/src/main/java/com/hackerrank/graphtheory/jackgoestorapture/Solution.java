package com.hackerrank.graphtheory.jackgoestorapture;

import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		new Solution().perform(System.in);
	}

	int V;
	int E;
	List<Edge>[] vs;
	int begin = 1;
	int end;

	public void perform(InputStream is) {
		Scanner s = new Scanner(is);
		try {
			end = s.nextInt();
			V = end + 1;
			E = s.nextInt();
			paidTillStation = new int[V];
			vs = new LinkedList[V];
			for (int i = 0; i < V; i++) {
				vs[i] = new LinkedList<Edge>();
				if (i > 1) {
					paidTillStation[i] = Integer.MAX_VALUE;
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
			perform(begin, visited);
			if (paidTillStation[end] == Integer.MAX_VALUE) {
				System.out.println("NO PATH EXISTS");
			} else {
				System.out.println(paidTillStation[end]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	Queue<Integer> q = new LinkedList<Integer>();
	int[] paidTillStation;

	private void perform(int parent) {
		if (parent == end) {
			System.out.println();
			return;
		}
		System.out.print(parent + "->");
		for (Edge e : vs[parent]) {
			if (!e.visited) {
				e.visited = true;
				int other = e.other;
				paidTillStation[other] = Math.min(paidTillStation[other], Math.max(paidTillStation[parent], e.w));
				perform(other);
			}
		}
	}

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