package com.technicalyorker.misc.commonwealthbank.weathersimulator.observers;

import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.INTERVAL_IN_MINUTES;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.PROJECTION_OPENING_MSG;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants.PROJECTION_TABLE_HEADER;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.util.TimeSeriesExtrapolation.extrapolate;
import static com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil.hiphenForNull;
import static java.lang.String.format;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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
public class DateTimeBasedPreditionAnalyticsObserver implements WeatherObserver {
	private static final int POINTS_TO_EXTRAPOLATE = 48;
	private final List<Weather> samples = new LinkedList<>();
	private int season;
	private static final Logger logger = LoggerFactory.getLogger(DateTimeBasedAverageSampleObserver.class);

	public DateTimeBasedPreditionAnalyticsObserver(int season) {
		this.season = season;
	}

	@Override
	public void updateChange(Weather w) {
		samples.add(w);
	}

	public void display() {
		logger.info(PROJECTION_OPENING_MSG);
		BigDecimal[][] tempData = new BigDecimal[samples.size() + POINTS_TO_EXTRAPOLATE][2];
		BigDecimal[][] humidityData = new BigDecimal[samples.size() + POINTS_TO_EXTRAPOLATE][2];
		init(tempData, humidityData);

		BigDecimal[] tempOutput = extrapolate(tempData, season, samples.size() - 1);
		BigDecimal[] humidityOutput = extrapolate(humidityData, season, samples.size() - 1);

		LocalDateTime lastSample = null;
		logger.info(PROJECTION_TABLE_HEADER);
		for (int i = 0; i < tempOutput.length; i++) {
			int relativeHumidity = Math.min(humidityOutput[i].toBigInteger().intValue(), 100);
			logger.info(format("\n%s\t%s\t\t\t%s\t\t%s\t\t\t%s",
					(lastSample = i < samples.size() ? (samples.get(i).getLocalTime())
							: (lastSample.plusMinutes(INTERVAL_IN_MINUTES))),
					hiphenForNull(tempData[i][1]), tempOutput[i].toPlainString(), hiphenForNull(humidityData[i][1]),
					relativeHumidity + (100 == relativeHumidity && i > samples.size() ? "*" : "")));
		}
		logger.info("\n* Rain - Relative Humidity = 100%\n");
	}

	private void init(BigDecimal[][] tempData, BigDecimal[][] humidityData) {
		for (int i = 0; i < samples.size(); i++) {
			tempData[i][0] = humidityData[i][0] = new BigDecimal(i + 1);
			Weather weather = samples.get(i);
			tempData[i][1] = weather.getTemperature();
			humidityData[i][1] = new BigDecimal(weather.getHumidity());
		}
		for (int i = samples.size(); i < samples.size() + POINTS_TO_EXTRAPOLATE; i++) {
			tempData[i][0] = humidityData[i][0] = new BigDecimal(samples.size() + i);
		}
	}
}
