package com.crossover.trial.journals.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

import com.crossover.trial.journals.utility.TemporalUtil;

import junit.framework.TestCase;

public class TemporalUtilTest {

	@Test
	public void test() {
		Date date = new Date();
		LocalDateTime l = TemporalUtil.toLocalDateTime(date);
		TestCase.assertEquals(date, TemporalUtil.toDate(l));
	}

	@Test
	public void testFormat() throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2016-11-10 11:10:10");
		TestCase.assertEquals("2016-11-10 11:10:10", TemporalUtil.asString(date));
	}
}
