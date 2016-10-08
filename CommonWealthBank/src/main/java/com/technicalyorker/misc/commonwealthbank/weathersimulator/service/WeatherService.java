package com.technicalyorker.misc.commonwealthbank.weathersimulator.service;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

public interface WeatherService {
	Weather readNext();
}
