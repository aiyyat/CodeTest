package com.hackerrank.search.pairs;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/pairs
 * 
 * @author achuth
 *
 */
public class Solution {

	public static void perform(InputStream is) {
		Scanner s = new Scanner(is);
		try {
			int n = s.nextInt();
			int k = s.nextInt();
			int[] ar = new int[n];
			for (int i = 0; i < n; i++) {
				ar[i] = s.nextInt();
				insertedAt(ar, i);
			}
			searchMatches(ar, k);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	private static void searchMatches(int[] ar, int k) {
		int count = 0;
		for (int i = 0; i < ar.length; i++) {
			int diff = k + ar[i];
			count += findIndex(ar, diff, i);
		}
		System.out.println(count);
	}

	public static void main(String[] args) {
		perform(System.in);
	}

	private static int findIndex(int[] ar, int diff, int i) {
		int lo = 0;
		int hi = ar.length - 1;
		while (lo <= hi) {
			int mid = (hi + lo) / 2;
			int midVal = ar[mid];
			if (diff == midVal) {
				int count = 0;
				while (mid - 1 > 0 && ar[mid - 1] == diff) {
					mid--;
				}
				while (mid < ar.length && ar[mid] == diff) {
					if (mid != i) {
						count++;
					}
					mid++;
				}
				return count;
			} else if (diff < midVal) {
				hi = mid - 1;
			} else if (diff > midVal) {
				lo = mid + 1;
			}
		}
		return 0;
	}

	private static void insertedAt(int[] ar, int i) {
		if (i > 0) {
			int temp = ar[i];
			int j = i;
			for (; j > 0; j--) {
				if (ar[j - 1] > temp) {
					ar[j] = ar[j - 1];
				} else {
					break;
				}
			}
			ar[j] = temp;
		}
	}

	private static void print(int[] ar) {
		System.out.println(Arrays.toString(ar));
	}
}