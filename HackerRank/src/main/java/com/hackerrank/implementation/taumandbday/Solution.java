package com.hackerrank.implementation.taumandbday;

import java.io.InputStream;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/taum-and-bday
 * 
 * @author achuth
 *
 */
public class Solution {
	public static void main(String[] args) {
		System.out.println(perform(System.in));
	}

	public static String perform(InputStream is) {
		long greaterValue = 0, smallerValue = 0, greaterValueCnt = 0, smallerValueCnt = 0;
		String output = "";
		Scanner s = new Scanner(is);
		int testCase = s.nextInt();
		while (testCase-- > 0) {
			int b = s.nextInt();
			int w = s.nextInt();
			int x = s.nextInt();
			int y = s.nextInt();
			int z = s.nextInt();
			if (x > y) {
				greaterValue = x;
				greaterValueCnt = b;
				smallerValue = y;
				smallerValueCnt = w;
			} else {
				greaterValue = y;
				greaterValueCnt = w;
				smallerValue = x;
				smallerValueCnt = b;
			}
			if (smallerValue + z < greaterValue) {
				greaterValue = smallerValue + z;
			}
			output += greaterValue * greaterValueCnt + smallerValue * smallerValueCnt + "\n";
		}
		s.close();
		return output;
	}
}
