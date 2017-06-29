package com.technicalyorker.misc.commonwealthbank.weathersimulator.service;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * Weather Service reads data from a DataSource, typically from database or
 * Webservice endpoint interface. This would take position and localtime as an
 * input to fetch the Weather data in real time.
 * 
 * @author achuth
 *
 */
public interface WeatherService<T> {
	Weather readNextSample(T t);
}
