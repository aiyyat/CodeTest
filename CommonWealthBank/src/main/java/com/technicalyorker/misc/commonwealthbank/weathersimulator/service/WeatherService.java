package com.technicalyorker.misc.commonwealthbank.weathersimulator.service;

import java.util.Calendar;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Position;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

public interface WeatherService {
	Weather read(Position location, Calendar time);
}
