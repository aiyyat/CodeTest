package com.technicalyorker.algorithms.coursera.algs4;

import java.util.Arrays;

import edu.princeton.cs.algs4.QuickUnionUF;

public class Percolation {
	QuickUnionUF q = null;
	private int N;
	private boolean[] e;
	private int begin = 0;
	private int end;
	private int extra = 0;

	public Percolation(int N) {
		this.N = N;
		q = new QuickUnionUF(N);
		extra = N + 1;
		end = N * N + 2 * (extra);
		e = new boolean[end];
		for (int i = 0; i < extra; i++) {
			open(i);
			open(end - 1 - i);
		}
	}

	public void open(int i, int j) {
		e[pos(i, j) + extra] = true;
	}

	public void open(int pos) {
		e[pos] = true;
	}

	public boolean isOpen(int i, int j) {
		return e[pos(i, j)];
	}

	public boolean isFull(int i, int j) {
		return false;
	}

	public boolean percolates() {
		return false;
	}

	public int pos(int i, int j) {
		return j * N + i;
	}

	public void print() {
		for (int i = 0; i < end; i++) {
			String pad = e[i] ? " " : "";
			if (i == 0 || i == end) {
				System.out.print(e[i] + pad);
			} else {
				if ((i - 1) % N == 0) {
					System.out.println();
				}
				System.out.print(e[i] + pad + " ");
			}
		}
	}

	public static void main(String[] args) {
		Percolation p = new Percolation(3);
		p.open(0, 0);
		p.open(1, 1);
		p.open(2, 2);
		p.print();
		;
	}
}
