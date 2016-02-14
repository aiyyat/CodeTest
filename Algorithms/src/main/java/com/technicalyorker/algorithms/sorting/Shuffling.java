package com.technicalyorker.algorithms.sorting;

import com.technicalyorker.algorithms.utilities.Util;

public class Shuffling {
	public static void shuffle(int[] a) {
		for (int i = 0; i < a.length; i++) {
			Util.swap(a, i, Util.rand(i));
		}
	}
}
