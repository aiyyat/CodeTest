package com.hackerrank.string.sherlockandvalidstring;

import java.io.InputStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/sherlock-and-valid-string
 * 
 * @author achuth
 *
 */
public class Solution {
	private static final String YES = "YES";
	private static final String NO = "NO";

	public static void main(String[] args) {
		System.out.println(perform(System.in));
	}

	public static String perform(InputStream in) {
		Scanner s = new Scanner(in);
		try {
			int[] alphabetOccurances = count(s.next());
			boolean removable = true;
			int i = -1;
			int expectedCount = -1;
			i = nextIndexOfNonNullElement(alphabetOccurances, i);
			int a0 = alphabetOccurances[i];
			i = nextIndexOfNonNullElement(alphabetOccurances, i);
			if (-1 == i) {
				return YES;
			}
			int a1 = alphabetOccurances[i];
			if (a0 == a1) {
				expectedCount = a0;
			} else {
				int greater;
				int lesser;
				if (a0 > a1) {
					greater = a0;
					lesser = a1;
				} else {
					greater = a1;
					lesser = a0;
				}
				if (lesser == 1) {
					removable = false;
					expectedCount = greater;
				} else if (greater - lesser == 1) {
					expectedCount = lesser;
					removable = false;
				} else {
					return NO;
				}
			}
			for (i = nextIndexOfNonNullElement(alphabetOccurances,
					i); i != -1; i = nextIndexOfNonNullElement(alphabetOccurances, i)) {
				if (alphabetOccurances[i] == expectedCount) {
					continue;
				} else if (removable == true
						&& (alphabetOccurances[i] - 1 == expectedCount || alphabetOccurances[i] - 1 == 0)) {
					removable = false;
					continue;
				} else {
					return NO;
				}
			}
		} finally {
			s.close();
		}
		return YES;
	}

	private static int nextIndexOfNonNullElement(int[] a, int i) {
		if (a[++i] != 0) {
			return i;
		}
		return -1;
	}

	public static int[] count(String next) {
		// a is for Alphabet count
		int[] alphabetOccurances = new int[26];
		for (int i = 0; i < next.length(); i++) {
			int idx = next.charAt(i) - 'a';
			alphabetOccurances[idx] = alphabetOccurances[idx] + 1;
		}
		return alphabetOccurances;
	}
}
