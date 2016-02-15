package com.technicalyorker.algorithms.sorting;

import com.technicalyorker.algorithms.utilities.Util;

public class HeapSort {
	private int N = 0;
	private int[] a;
	private int size;

	public HeapSort(int size) {
		this.size = size;
		a = new int[size + 1];
	}

	public void swim(int i) {
		int parent = i / 2;
		if (parent >= 1 && a[i] > a[parent]) {
			Util.swap(a, i, parent);
			swim(parent);
		}
	}

	public void sink(int i) {
		int left = i * 2;
		int right = left + 1;
		if (right <= N) {
			int greater = Util.greaterIndex(a, left, right);
			if (a[greater] > a[i]) {
				Util.swap(a, greater, i);
				sink(greater);
			}
		}
	}

	public void insert(int i) {
		if (N == size) {
			throw new ArrayIndexOutOfBoundsException(
					"This at present is not expandable, Heap initialized with size " + size);
		}
		a[++N] = i;
		swim(N);
	}

	public int removeFirst() {
		int first = a[1];
		a[1] = a[N];
		a[N] = 0;
		N--;
		sink(1);
		return first;
	}

	public static void main(String[] args) {
		HeapSort h = new HeapSort(19);
		h.insert(1);
		h.insert(2);
		h.insert(3);
		h.insert(4);
		h.insert(5);
		h.insert(6);
		h.insert(7);
		h.insert(8);
		h.insert(9);
		System.out.println(h.removeFirst());
		System.out.println(h.removeFirst());
		System.out.println(h.removeFirst());
		System.out.println(h.removeFirst());
		System.out.println(h.removeFirst());
		System.out.println(h.removeFirst());
		System.out.println(h.removeFirst());
		System.out.println(h.removeFirst());
		System.out.println(h.removeFirst());
	}
}
