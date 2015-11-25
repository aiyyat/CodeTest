package com.hackerrank.implementation.encryption;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/encryption
 * 
 * @author achuth
 *
 */
public class Solution {
	public static void main(String[] args) {
		System.out.println(new Solution().perform(System.in));
	}

	public String perform(InputStream is) {
		Scanner s = new Scanner(is);
		try {
			return encrypt(s.next());
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			s.close();
		}
	}

	public String encrypt(String str) {
		str = str.replaceAll(" ", "");
		int len = str.length();
		int n = 10;
		int row = 0, col = 0;
		while (n-- > 0) {
			int lstRwCnt = n * (n) - len;
			if (0 <= lstRwCnt && lstRwCnt < n) {
				row = n;
				col = n;
				break;
			}

			lstRwCnt = n * (n + 1) - len;
			if (0 <= lstRwCnt && lstRwCnt < n + 1) {
				row = n;
				col = n + 1;
				break;
			}
		}
		char[][] ar = new char[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				ar[i][j] = '_';
			}
		}
		outer: for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (i * col + j >= len) {
					break outer;
				}
				ar[i][j] = str.charAt(i * col + j);
			}
		}
		return printableString(ar);
	}

	public String printableString(char[][] ar) {
		StringBuilder out = new StringBuilder("");
		char[][] transposed = new char[ar[0].length][ar.length];
		for (int i = 0; i < ar.length; i++) {
			for (int j = 0; j < ar[i].length; j++) {
				transposed[j][i] = ar[i][j];
			}
		}
		for (int i = 0; i < transposed.length; i++) {
			for (int j = 0; j < transposed[i].length; j++) {
				char ch = transposed[i][j];
				if (ch != '_') {
					out.append(ch);
				}
			}
			if (i < transposed.length - 1) {
				out.append(" ");
			}
		}
		return out.toString();
	}
}
