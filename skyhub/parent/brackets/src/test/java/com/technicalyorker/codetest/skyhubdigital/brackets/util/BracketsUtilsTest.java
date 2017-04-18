package com.technicalyorker.codetest.skyhubdigital.brackets.util;

import org.junit.Test;

import junit.framework.TestCase;

public class BracketsUtilsTest {
	@Test
	public void case1() {
		TestCase.assertTrue(BracketsUtil.checkBracket("{ [ ] ( ) }"));
	}

	@Test
	public void case2() {
		TestCase.assertFalse(BracketsUtil.checkBracket("{ [ ( ] ) }"));
	}

	@Test
	public void case3() {
		TestCase.assertFalse(BracketsUtil.checkBracket("{ [ }"));
	}
}
