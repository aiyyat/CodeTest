package com.technicalyorker.misc.commonwealthbank.weathersimulator.observers;

import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * This observer registers the sum of all humidity and temperature samples and
 * then on call of display outputs the average values.
 * 
 * @author achuth
 *
 */
public class DateTimeBasedAverageSampleObserver implements WeatherObserver {
	private static final Logger logger = LoggerFactory.getLogger(DateTimeBasedAverageSampleObserver.class);
	BigDecimal sumTemperature = BigDecimal.ZERO;
	Integer sumHumidiy = new Integer(0);
	int sampleCount = 1;

	@Override
	public void updateChange(Weather w) {
		sumTemperature = sumTemperature.add(w.getTemperature());
		sumHumidiy += w.getHumidity();
		sampleCount++;
	}

	public void display() {
		BigDecimal divisor = new BigDecimal(sampleCount);
		logger.info(
				String.format("\nAverage Temperture Recording: %s\u00B0c\nAverage Relative Humidity Recording: %s%%",
						sumTemperature.divide(divisor, HALF_UP), new BigDecimal(sumHumidiy).divide(divisor, HALF_UP)));
	}
}
