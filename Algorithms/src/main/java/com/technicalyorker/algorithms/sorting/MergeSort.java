package com.technicalyorker.algorithms.sorting;

public class MergeSort implements Sortable {
	private static int[] copy = null;

	public synchronized void sort(int[] a) {
		copy = new int[a.length];
		split(a, 0, a.length);
	}

	public static void merge(int[] a, int start, int end, int median) {
		for (int i = start; i < end; i++) {
			copy[i] = a[i];
		}
		int i = start;
		int j = median;
		int index = start;
		while (true) {
			if (i >= median && j >= end) {
				break;
			}
			if (i >= median) {
				a[index++] = copy[j++];
			} else if (j >= end) {
				a[index++] = copy[i++];
			} else if (copy[i] > copy[j]) {
				a[index++] = copy[j++];
			} else {
				a[index++] = copy[i++];
			}
		}
	}

	public static void split(int[] a, int start, int end) {
		int median = (start + end) / 2;
		if (end - start == 1) {
			return;
		}
		split(a, start, median);
		split(a, median, end);
		merge(a, start, end, median);
	}

	public static void main(String[] args) {
		int[] a = new int[] { 9, 8, 0, 7, 6, 3, 5, 2, 4, 1 };
		new MergeSort().sort(a);
	}
}
