package com.technicalyorker.algorithms.sorting;

import com.technicalyorker.algorithms.utilities.Util;

public class QuickSort implements Sortable {

	public void sort(int[] a) {
		split(a, 0, a.length);
	}

	public static int sift(int[] a, int start, int end) {
		int median = start;
		int i = start + 1;
		int j = end - 1;
		while (true) {
			while (i < end && a[i] < a[median]) {
				i++;
			}
			// This is so that the loop ends with j=start and not start+1 i.e.
			// if the swap has to happen with self when median is the smallest
			// of elements
			while (j > start && a[median] < a[j]) {
				j--;
			}
			if (j <= i) {
				break;
			}
			Util.swap(a, j, i);
		}
		Util.swap(a, median, j);
		return j;
	}

	public static void split(int[] a, int start, int end) {
		// End Condition
		if (start == end) {
			return;
		}
		int median = sift(a, start, end);
		split(a, start, median);
		split(a, median + 1, end);
	}

	public static void main(String[] args) {
		split(new int[] { 4, 5, 3, 2, 6 }, 0, 5);
	}
}
