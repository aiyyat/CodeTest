package com.technicalyorker.misc.commonwealthbank.weathersimulator.observers;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.junit.Test;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.mock.DataMockUtility;

import junit.framework.TestCase;

/**
 * Testing Observer
 * 
 * @author achuth
 *
 */
public class ObserverTest {

	@Test
	public void notifyChange() {
		AtomicInteger i = new AtomicInteger();
		WeatherDataSubject s = new WeatherDataSubject() {

		};
		WeatherObserver w = new WeatherObserver() {

			@Override
			public void updateChange(Weather w) {
				i.incrementAndGet();
			}

			@Override
			public void display() {

			}
		};
		s.registerObserver(w);
		System.out.printf("Notifying");
		IntStream.range(1, 5).forEach(f -> {
			System.out.printf(".");
			s.updateChange(DataMockUtility.aWeatherSample());
		});
		System.out.println();
		TestCase.assertEquals(i.get(), 4);
	}
}
