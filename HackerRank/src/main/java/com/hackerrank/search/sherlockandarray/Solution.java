package com.hackerrank.search.sherlockandarray;

import java.io.InputStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/sherlock-and-array
 * 
 * @author achuth
 *
 */
public class Solution {
	public static void main(String[] args) {
		perform(System.in);
	}

	static String perform(InputStream is) {
		Scanner s = new Scanner(is);
		try {
			int ts = s.nextInt();
			while (ts-- > 0) {
				int n = s.nextInt();
				int[] ar = new int[n];
				for (int i = 0; i < n; i++) {
					ar[i] = s.nextInt();
				}
				System.out.println(perform(ar));
			}
		} finally {
			s.close();
		}
		return null;
	}

	static String perform(int[] ar) {
		int n = ar.length;
		if (n == 1) {
			return "YES";
		} else if (n < 3) {
			return "NO";
		} else {
			int sumLeft = 0;
			int sumRight = 0;
			for (int j = 0; j < n - 1; j++) {
				sumLeft += ar[j];
			}
			// System.out.println(Arrays.toString(ar));
			for (int j = n - 2; j >= 1; j--) {
				// System.out.println(ar[j]);
				sumRight += ar[j + 1];
				sumLeft -= ar[j];
				// System.out.printf("%d<%d>%d\n", sumLeft, ar[j], sumRight);
				if (sumRight == sumLeft) {
					return "YES";
				}
			}
		}
		return "NO";
	}
}
