package com.technicalyorker.misc.commonwealthbank.weathersimulator.service;

import org.junit.Test;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil;

import junit.framework.TestCase;

/**
 * Test case to test service.
 * 
 * @author achuth
 *
 */
public class DateTimeBasedWeatherServiceTest {

	@Test
	public void test() {
		DateTimeBasedWeatherService d = new DateTimeBasedWeatherService();
		TestCase.assertEquals("Sydney|-34.92,138.62,48|2016-10-15T19:30:00Z|CLOUDY|20|1016.8|38",
				d.readNextSample(WeatherSimulatorUtil.format("2016-10-15T19:30:00Z")).toString());
	}
}
