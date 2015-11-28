package com.hackerrank.implementation.encryption;

import org.junit.Test;

import junit.framework.TestCase;

public class SolutionTest {
	Solution s = new Solution();
	String testCase = "10" + "27984 1402" + "619246 615589 247954" + "95677 39394" + "86983 311224 588538"
			+ "68406 12718" + "532909 315341 201009" + "15242 95521" + "712721 628729 396706" + "21988 67781"
			+ "645748 353796 333199" + "22952 80129" + "502975 175136 340236" + "38577 3120" + "541637 657823 735060"
			+ "5943 69851" + "674453 392925 381074" + "62990 61330" + "310144 312251 93023" + "11152 43844"
			+ "788543 223872 972572";

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
