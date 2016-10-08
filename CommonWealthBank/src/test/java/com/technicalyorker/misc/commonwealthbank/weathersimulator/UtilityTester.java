package com.technicalyorker.misc.commonwealthbank.weathersimulator;

import org.junit.Test;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil;

import junit.framework.TestCase;

public class UtilityTester {
	@Test
	public void testCountry() {
		TestCase.assertFalse(WeatherSimulatorUtil.anyCountry().matches(".*\\d+.*"));
	}
}
