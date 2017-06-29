package com.technicalyorker.misc.commonwealthbank.weathersimulator.observers;

import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.DEGREE;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.LOWERTHRESHOLD;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.SKULL;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.UPPERTHRESHOLD;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

/**
 * This simulates a warning indicator if the temperature exceeds Defined
 * Thresholds
 * 
 * @author achuth
 *
 */
public class DateTimeBasedTemperatureThresholdWarningSimulatorObserver implements WeatherObserver {
	private static final Logger logger = LoggerFactory.getLogger(DateTimeBasedAverageSampleObserver.class);

	@Override
	public void updateChange(Weather w) {
		if (w.getTemperature().compareTo(UPPERTHRESHOLD) > 0) {
			logger.info(String.format(" (>%s%c%c)", UPPERTHRESHOLD.toEngineeringString(), DEGREE, SKULL));
		}
		if (w.getTemperature().compareTo(LOWERTHRESHOLD) < 0) {
			logger.info(String.format(" (<%s%c%c)", LOWERTHRESHOLD.toEngineeringString(), DEGREE, SKULL));
		}
	}

	public void display() {
		logger.info(String.format("\nUpper Threshold: %s\u00B0c\nLower Threshold: %s\u00B0c\n", UPPERTHRESHOLD,
				LOWERTHRESHOLD));
	}
}
