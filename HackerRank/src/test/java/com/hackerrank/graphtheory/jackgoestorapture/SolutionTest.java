package com.hackerrank.graphtheory.jackgoestorapture;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class SolutionTest {
	@Test
	public void test1() {
		String str = "10 45\n1 2 6337\n1 3 1594\n1 4 3766\n1 5 3645\n1 6 75\n1 7 5877\n1 8 8561\n1 9 242\n1 10 6386\n2 3 3331\n2 4 4194\n2 5 8069\n2 6 3934\n2 7 101\n2 8 8536\n2 9 6963\n2 10 9303\n3 4 7639\n3 5 8512\n3 6 1330\n3 7 6458\n3 8 1180\n3 9 3913\n3 10 1565\n4 5 9488\n4 6 1369\n4 7 8066\n4 8 9439\n4 9 7510\n4 10 6833\n5 6 4215\n5 7 194\n5 8 4774\n5 9 4328\n5 10 187\n6 7 1196\n6 8 200\n6 9 8743\n6 10 1433\n7 8 2933\n7 9 2069\n7 10 1974\n8 9 7349\n8 10 2351\n9 10 8423\n";
		Solution s = new Solution();
		s.perform(new ByteArrayInputStream(str.getBytes()));
	}

	@Test
	public void defaultTest() {
		new Solution().perform(new ByteArrayInputStream("5 5\n1 2 60\n3 5 70\n1 4 120\n4 5 150\n2 3 80".getBytes()));
	}

	@Test
	public void myTest() {
		new Solution().perform(new ByteArrayInputStream(
				"7 9\n1 2 4\n2 4 1\n4 7 1\n1 3 1\n3 5 1 \n5 7 7\n6 1 1\n6 2 1\n6 7 4".getBytes()));
	}
}
