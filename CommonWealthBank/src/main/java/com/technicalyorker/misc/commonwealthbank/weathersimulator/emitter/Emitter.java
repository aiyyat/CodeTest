package com.technicalyorker.misc.commonwealthbank.weathersimulator.emitter;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.domain.Weather;

public interface Emitter {
	public void emit(Weather weather);
}
