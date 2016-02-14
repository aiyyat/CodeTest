package com.technicalyorker.algorithms.sorting;

import static com.technicalyorker.algorithms.utilities.Util.swap;

public class InsertionSort implements Sortable {

	public void sort(int[] a) {
		int N = a.length;
		for (int i = 1; i < N; i++) {
			for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
				swap(a, j - 1, j);
			}
		}
	}
}
