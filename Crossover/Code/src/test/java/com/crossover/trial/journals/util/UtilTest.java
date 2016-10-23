package com.crossover.trial.journals.util;

import java.util.Date;

import org.junit.Test;

import junit.framework.TestCase;

public class UtilTest {

	@Test
	public void test() {
		Date date = new Date();
		TestCase.assertEquals(date, Util.to(Util.to(date)));
	}
}
