package com.hackerrank.search.pairs;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class SolutionTest {
	@Test
	public void test() {
		Solution.perform(new ByteArrayInputStream("5 2 \n1 5 3 4 2".getBytes()));
	}
}
