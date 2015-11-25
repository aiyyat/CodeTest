package com.hackerrank.implementation.encryption;

import org.junit.Test;

import junit.framework.TestCase;

public class SolutionTest {
	Solution s = new Solution();

	@Test
	public void test1() {
		char[][] ar = new char[3][4];
		ar[0][0] = 'h';
		ar[0][1] = 'a';
		ar[0][2] = 'v';
		ar[0][3] = 'e';
		ar[1][0] = 'a';
		ar[1][1] = 'n';
		ar[1][2] = 'i';
		ar[1][3] = 'c';
		ar[2][0] = 'e';
		ar[2][1] = 'd';
		ar[2][2] = 'a';
		ar[2][3] = 'y';
		TestCase.assertEquals("hae and via ecy", s.printableString(ar));
	}

	@Test
	public void test2() {
		TestCase.assertEquals("hae and via ecy", s.encrypt("haveaniceday"));
	}

	@Test
	public void test3() {
		TestCase.assertEquals("fto ehg ee dd", s.encrypt("feedthedog"));
	}

	@Test
	public void test4() {
		TestCase.assertEquals("clu hlt io", s.encrypt("chillout"));
	}

	@Test
	public void test5() {
		TestCase.assertEquals("imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau",
				s.encrypt("if man was meant to stay on the ground god would have given us roots"));
	}

	@Test
	public void test6() {
		TestCase.assertEquals(
				"wmgjpnull cyjqlejgi lyhhdzbui wctlsqsbm fxeoxmsvv ovxjeirfm zadysxbhn nxkkbffpn bawobphfy",
				s.encrypt("wclwfoznbmyycxvaxagjhtexdkwjqhlojykopldsxesbbnezqmixfpujbssrbfhlgubvfhpfliimvmnny"));
	}
}
