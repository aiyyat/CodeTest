package com.technicalyorker.misc.commonwealthbank.weathersimulator;

import static com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil.anyCountry;

import org.junit.Test;

import junit.framework.TestCase;

public class UtilityTester {
	@Test
	public void testCountry() {
		TestCase.assertFalse(anyCountry().matches(".*\\d+.*"));
	}
}
