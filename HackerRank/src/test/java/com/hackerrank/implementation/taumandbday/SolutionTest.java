package com.hackerrank.implementation.taumandbday;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import junit.framework.TestCase;

public class SolutionTest {
	String str = "10\n" + "27984 1402\n" + "619246 615589 247954\n" + "95677 39394\n" + "86983 311224 588538\n"
			+ "68406 12718\n" + "532909 315341 201009\n" + "15242 95521\n" + "712721 628729 396706\n" + "21988 67781\n"
			+ "645748 353796 333199\n" + "22952 80129\n" + "502975 175136 340236\n" + "38577 3120\n"
			+ "541637 657823 735060\n" + "5943 69851\n" + "674453 392925 381074\n" + "62990 61330\n"
			+ "310144 312251 93023\n" + "11152 43844\n" + "788543 223872 972572";
	
	String output = "18192035842\n" + "20582630747\n" + "39331944938\n" + "70920116291\n" + "38179353700\n"
			+ "25577754744\n" + "22947138309\n" + "31454478354\n" + "38686324390\n" + "18609275504\n";

	@Test
	public void test1() {
		System.out.println(Solution.perform(new ByteArrayInputStream(str.getBytes())));
		TestCase.assertEquals(output, Solution.perform(new ByteArrayInputStream(str.getBytes())));
	}
}
