package com.technicalyorker.nasdaq;

import java.math.BigInteger;

public class Solution {
	public static void main(String[] args) {
		Solution s = new Solution();
		s.performNPrint(1000000, 200);
		s.performNPrint(10000000, 200);
	}

	public String performNPrint(int n, int C) {
		String output = perform(n, C).toString();
		System.out.printf("\nfor n=%d & C=%d \n result=%s", n, C,
				output.toString().substring(0, Math.min(10, output.length())));
		return output;
	}

	public BigInteger perform(int n, int C) {
		BigInteger product = new BigInteger("1");
		BigInteger sum = new BigInteger("0");
		for (int i = 1; i < n; i++) {
			if (i <= C) {
				product = product.multiply(new BigInteger("" + i));
			} else {
				product = product.multiply(new BigInteger("" + i)).divide(new BigInteger("" + (i - C)));
			}
			sum = sum.add(product);
		}
		return sum;
	}
}
