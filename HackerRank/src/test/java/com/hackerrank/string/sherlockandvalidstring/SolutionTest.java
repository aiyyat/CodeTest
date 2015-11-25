package com.hackerrank.string.sherlockandvalidstring;

import java.io.ByteArrayInputStream;

import org.junit.Test;

import junit.framework.TestCase;

public class SolutionTest {
	@Test
	public void testM2() {
		TestCase.assertEquals("YES", Solution.perform(new ByteArrayInputStream("a".getBytes())));
	}

	@Test
	public void testM1() {
		TestCase.assertEquals("NO",
				Solution.perform(new ByteArrayInputStream(
						"tfgyrknpgngtqgjccbyctwdcymwrcjtpoaflkeoxfxijxkngpjoofggsozftkpgxakptmzjxugavazwllxdtrjrrbjmrqwfxaby"
								.getBytes())));
	}

	@Test
	public void test0() {
		TestCase.assertEquals("NO", Solution.perform(new ByteArrayInputStream("aabbcd".getBytes())));
	}

	@Test
	public void test1() {
		TestCase.assertEquals("YES", Solution.perform(new ByteArrayInputStream("aabbcc".getBytes())));
	}

	@Test
	public void test2() {
		TestCase.assertEquals("YES", Solution.perform(new ByteArrayInputStream("abbcc".getBytes())));
	}

	@Test
	public void test3() {
		TestCase.assertEquals("YES", Solution.perform(new ByteArrayInputStream("aabbcc".getBytes())));
	}

	@Test
	public void test4() {
		TestCase.assertEquals("YES", Solution.perform(new ByteArrayInputStream("aabbc".getBytes())));
	}

	@Test
	public void test5() {
		TestCase.assertEquals("NO", Solution.perform(new ByteArrayInputStream("abbbcc".getBytes())));
	}
}
