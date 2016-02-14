package com.technicalyorker.algorithms.sorting;

import com.technicalyorker.algorithms.utilities.Util;

public class BubbleSort implements Sortable {

	public void sort(int[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N - 1; j++) {
				if (a[j] > a[j + 1]) {
					Util.swap(a, j + 1, j);
				}
			}
		}
	}
}
