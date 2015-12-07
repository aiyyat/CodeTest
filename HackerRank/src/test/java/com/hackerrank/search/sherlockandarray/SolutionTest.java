package com.hackerrank.search.sherlockandarray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import junit.framework.TestCase;

public class SolutionTest {
	@Test
	public void test1() {
		TestCase.assertEquals("NO", Solution.perform(new int[] { 1, 2, 3 }));
	}

	@Test
	public void test2() {
		TestCase.assertEquals("YES", Solution.perform(new int[] { 1, 2, 3, 3 }));
	}

	@Test
	public void test3() throws FileNotFoundException {
		System.out.println(Solution.perform(new FileInputStream(new File(
				"/home/achuth/git/misc/HackerRank/src/test/java/com/hackerrank/search/sherlockandarray/Input.txt"))));
	}
}
