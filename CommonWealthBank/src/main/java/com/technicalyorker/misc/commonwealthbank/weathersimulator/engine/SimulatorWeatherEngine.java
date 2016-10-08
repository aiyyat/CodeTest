package com.technicalyorker.misc.commonwealthbank.weathersimulator.engine;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.WeatherDataEmitter;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.MockWeatherService;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.service.WeatherService;

/**
 * This is a Simulation implement of a Weather Engine. Typically it could read
 * from a Weather Webservice endpoint or from Database based on co-ordinates and
 * time.
 * 
 * @author achuth
 *
 */
public class SimulatorWeatherEngine extends AbstractWeatherEngine {
	private static final WeatherService service = new MockWeatherService();

	public SimulatorWeatherEngine(WeatherDataEmitter emitter) {
		super(emitter);
	}

	@Override
	public void sampleData() {
		Weather weather = service.readNext();
		emit(weather);
	}
}
