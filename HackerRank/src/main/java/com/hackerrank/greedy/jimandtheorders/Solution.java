package com.hackerrank.greedy.jimandtheorders;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/jim-and-the-orders
 * 
 * @author achuth
 *
 */
public class Solution {
	public static void main(String[] args) {
		System.out.println(perform(System.in));
	}

	public static String perform(InputStream is) {
		Scanner s = new Scanner(is);
		int n = s.nextInt();
		int[] ar = new int[n];
		while (n-- > 0) {
			ar[ar.length - n - 1] = s.nextInt() + s.nextInt();
		}
		n = ar.length;
		int[] rank = new int[n];
		for (int i = 0; i < n; i++) {
			rank[i]++;
			for (int j = 0; j < n; j++) {
				if (ar[i] > ar[j]) {
					rank[i]++;
				} else if (ar[i] == ar[j]) {
					if (i > j) {
						rank[i]++;
					}
				}
			}
		}
		for (int i = 0; i < rank.length; i++) {
			ar[rank[i] - 1] = i + 1;
		}
		return Arrays.toString(ar).replaceAll(" ", "").replaceAll(",", " ").replaceAll("\\[", "").replaceAll("\\]", "");
	}
}
