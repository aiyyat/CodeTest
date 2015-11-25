package com.thoughtworks;

import java.math.BigDecimal;

import org.junit.Test;

import com.thoughtworks.problem2.Util;

import junit.framework.TestCase;

public class UtilTest {

	@Test
	public void test1() {
		TestCase.assertEquals("10.25", Util.roundTo5Cents(new BigDecimal("10.22")).toPlainString());
	}

	@Test
	public void test2() {
		TestCase.assertEquals("10.25", Util.roundTo5Cents(new BigDecimal("10.25")).toPlainString());
	}

	@Test
	public void test3() {
		TestCase.assertEquals("10.2", Util.roundTo5Cents(new BigDecimal("10.20")).toPlainString());
	}

	@Test
	public void test4() {
		TestCase.assertEquals("10", Util.roundTo5Cents(new BigDecimal("10.00")).toPlainString());
	}

	@Test
	public void test5() {
		TestCase.assertEquals("10.05", Util.roundTo5Cents(new BigDecimal("10.01")).toPlainString());
	}

	@Test
	public void test6() {
		TestCase.assertEquals("10.05", Util.roundTo5Cents(new BigDecimal("10.011")).toPlainString());
	}

}
