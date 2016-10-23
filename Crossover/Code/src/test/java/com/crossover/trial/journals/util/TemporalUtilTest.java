package com.crossover.trial.journals.util;

import java.util.Date;

import org.junit.Test;

import com.crossover.trial.journals.utility.TemporalUtil;

import junit.framework.TestCase;

public class TemporalUtilTest {

	@Test
	public void test() {
		Date date = new Date();
		TestCase.assertEquals(date, TemporalUtil.toDate(TemporalUtil.toLocalDateTime(date)));
	}
}
