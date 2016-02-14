package com.technicalyorker.algorithms.sorting;

import com.technicalyorker.algorithms.utilities.Util;

public class SelectionSort implements Sortable {

	public void sort(int[] a) {
		int N = a.length;
		for (int i = 0; i < N - 1; i++) {
			int max = i;
			for (int j = i; j < N; j++) {
				if (a[j] < a[max]) {
					max = j;
				}
			}
			Util.swap(a, i, max);
		}
	}
}
