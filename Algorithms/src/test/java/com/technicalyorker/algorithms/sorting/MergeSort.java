package com.technicalyorker.algorithms.sorting;

import com.technicalyorker.algorithms.utilities.Util;

public class MergeSort implements Sortable {

	public void sort(int[] a) {
		split(a, 0, a.length);
	}

	public static void merge(int[] a, int start, int end, int median) {
		int[] copy = a.clone();
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
		// Util.print(a);
	}

	public static void split(int[] a, int start, int end) {
		int median = (start + end) / 2;
		if (end - start == 1) {
			return;
		}
		split(a, start, median);
		split(a, median, end);
		// Util.print(a, start, end, median);
		merge(a, start, end, median);
	}

	public static void main(String[] args) {
		int[] a = new int[] { 9, 8, 0, 7, 6, 3, 5, 2, 4, 1 };
		split(a, 0, a.length);
	}
}
