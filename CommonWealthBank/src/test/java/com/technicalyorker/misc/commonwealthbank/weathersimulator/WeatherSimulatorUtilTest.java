package com.technicalyorker.misc.commonwealthbank.weathersimulator;

import java.time.LocalDateTime;

import org.junit.Test;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil;

import junit.framework.TestCase;

/**
 * @author achuth
 *
 */
public class WeatherSimulatorUtilTest {
	@Test
	public void fromString() {
		LocalDateTime local = WeatherSimulatorUtil.format("2016-10-14T19:30:00Z");
		TestCase.assertEquals("2016-10-14T19:30:00Z", WeatherSimulatorUtil.format(local));
	}
}
