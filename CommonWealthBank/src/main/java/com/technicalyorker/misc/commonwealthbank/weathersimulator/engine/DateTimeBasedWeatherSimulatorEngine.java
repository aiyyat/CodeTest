package com.technicalyorker.misc.commonwealthbank.weathersimulator.engine;

import java.time.LocalDateTime;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.WeatherDataEmitter;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.DateTimeBasedWeatherService;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.WeatherService;

/**
 * This is a Simulation implement of a Weather Engine. Typically it could read
 * from a Weather Webservice endpoint or from Database based on co-ordinates and
 * time.
 * 
 * @author achuth
 *
 */
public class DateTimeBasedWeatherSimulatorEngine extends AbstractWeatherEngine<LocalDateTime> {
	private static final WeatherService<LocalDateTime> service = new DateTimeBasedWeatherService();

	public DateTimeBasedWeatherSimulatorEngine(WeatherDataEmitter emitter) {
		super(emitter);
	}

	@Override
	public Weather sampleData(LocalDateTime t) {
		return service.readNextSample(t);
	}
}
