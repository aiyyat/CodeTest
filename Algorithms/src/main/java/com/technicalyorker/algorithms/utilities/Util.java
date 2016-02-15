package com.technicalyorker.algorithms.utilities;

import java.util.Arrays;

public class Util {

	public static int rand(int i) {
		return (int) (Math.random() * i);
	}

	public static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static int[] initialize(int size) {
		return initialize(size, null);
	}

	public static int[] initialize(int size, Integer val) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			if (val == null) {
				a[i] = i;
			} else {
				a[i] = val;
			}
		}
		return a;
	}

	public static void print(int a[]) {
		System.out.println(asString(a));
	}

	public static String asString(int a[]) {
		return Arrays.toString(a);
	}

	public static boolean checkSorted(int[] a) {
		for (int i = 1; i < a.length; i++) {
			if (a[i - 1] > a[i]) {
				return false;
			}
		}
		return true;
	}

	public static void print(int[] a, int start, int end, int median) {
		for (int i = start; i < end; i++) {
			if (i > start) {
				System.out.print(" ");
			}
			if (i == median) {
				System.out.print("[" + a[i] + "]");
			} else {
				System.out.print(a[i]);
			}
		}
		System.out.println();
	}

	public static int greaterIndex(int[] a, int left, int right) {
		if (a[left] < a[right]) {
			return right;
		}
		return left;
	}
}
