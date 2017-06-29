package com.technicalyorker.misc.commonwealthbank.weathersimulator.trigger;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Before;
import org.junit.Test;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter.WeatherDataEmitter;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.engine.AbstractWeatherEngine;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.engine.WeatherEngine;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.mock.DataMockUtility;

/**
 * Unit Testing Triggers!
 * 
 * @author achuth
 *
 */
public class DateTimeBasedMultiThreadTriggerTest {

	private static final int NO_OF_TRIGGERS = 3;
	private final AtomicInteger a = new AtomicInteger();
	private WeatherEngine<LocalDateTime> e = null;

	@Before
	public void init() {
		e = new AbstractWeatherEngine<LocalDateTime>(new WeatherDataEmitter() {
			public void emit(Weather weather) {
			}
		}) {
			public Weather sampleData(LocalDateTime t) {
				return DataMockUtility.aWeatherSample();
			}

			public void notifyEmitter(Weather weather) {
				a.incrementAndGet();
			}
		};
	}

	@Test
	public void test() {
		LocalDateTime l = LocalDateTime.now();
		new DateTimeBasedMultiThreadTrigger(e, l, NO_OF_TRIGGERS * 30).start();
		assertEquals(a.get(), NO_OF_TRIGGERS);
	}
}
