package com.technicalyorker.misc.commonwealthbank.weathersimulator;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger.MultiThreadedWeatherTrigger;

public class WeatherSimulatorApplication {
	public static void main(String[] args) {
		new MultiThreadedWeatherTrigger().perform();
	}
}
