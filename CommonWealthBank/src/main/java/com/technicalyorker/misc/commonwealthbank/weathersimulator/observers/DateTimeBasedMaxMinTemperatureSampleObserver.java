package com.technicalyorker.misc.commonwealthbank.weathersimulator.observers;

import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.FLAKE;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.SUN;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * This simulates an indicator of an increase or decrease in the greatest
 * weather of the day.
 * 
 * @author achuth
 *
 */
public class DateTimeBasedMaxMinTemperatureSampleObserver implements WeatherObserver {
	private static final Logger logger = LoggerFactory.getLogger(DateTimeBasedAverageSampleObserver.class);
	private BigDecimal hotest = new BigDecimal(Integer.MIN_VALUE);
	private BigDecimal coldest = new BigDecimal(Integer.MAX_VALUE);

	@Override
	public void updateChange(Weather w) {
		if (w.getTemperature().compareTo(hotest) > 0) {
			hotest = w.getTemperature();
		}
		if (w.getTemperature().compareTo(coldest) < 0) {
			coldest = w.getTemperature();
		}
	}

	public void display() {
		logger.info(String.format("\nMax Temperture %c Recording: %s\u00B0c\nMin Temperture %c Recording: %s\u00B0c\n",
				SUN, hotest, FLAKE, coldest));
	}
}
