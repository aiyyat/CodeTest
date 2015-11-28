package com.hackerrank.greedy.jimandtheorders;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import junit.framework.TestCase;

public class SolutionTest {
	@Test
	public void test1() {
		TestCase.assertEquals("4 2 5 1 3",
				Solution.perform(new ByteArrayInputStream(("5\n8 1\n4 2\n5 6\n3 1\n4 3").getBytes())));
	}

	@Test
	public void test2() {
		TestCase.assertEquals("25 11 6 1 9 15 24 2 8 3 16 7 12 23 22 4 13 21 19 14 17 10 20 18 5",
				Solution.perform(new ByteArrayInputStream(
						("25\n122470 725261\n193218 693005\n355776 603340\n830347 284246\n974815 823134\n251206 572501\n55509 927152\n299590 651593\n222305 641645\n984018 463771\n494787 286091\n217190 833029\n820867 380896\n744986 630663\n875880 667\n449199 520904\n924615 511326\n862614 890277\n959638 373599\n685864 923011\n922324 407528\n157354 943714\n380643 714960\n269853 608576\n290422 195768")
								.getBytes())));
	}

}
