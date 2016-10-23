package com.crossover.trial.journals.util;

import java.util.Date;

import org.junit.Test;

import com.crossover.trial.journals.exception.IllegalDateFormatException;
import com.crossover.trial.journals.utility.TemporalUtil;

import junit.framework.TestCase;

public class TemporalUtilTest {

	@Test
	public void test() {
		Date date = new Date();
		TestCase.assertEquals(date, TemporalUtil.toDate(TemporalUtil.toLocalDateTime(date)));
	}

	@Test
	public void testFormat() {
		Date date = TemporalUtil.asDate("2016-11-10 11:10:10");
		TestCase.assertEquals("2016-11-10 11:10:10", TemporalUtil.asString(date));
	}

	@Test(expected = IllegalDateFormatException.class)
	public void testBadFormat() {
		TemporalUtil.asDate("Hello");
	}
}
