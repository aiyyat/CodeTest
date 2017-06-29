package com.technicalyorker.misc.commonwealthbank.weathersimulator.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.technicalyorker.misc.commonwealthbank.weathersimulator.constants.WeatherSimulatorConstants;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.exception.WeatherDataSampleException;
import com.technicalyorker.misc.commonwealthbank.weathersimulator.util.WeatherSimulatorUtil;

/**
 * This domain model class encapsulates the attributes of a Weather entity.
 * 
 * @author achuth
 *
 */
public class Weather {
	private String location;
	private Position position;
	private LocalDateTime localTime;
	private Condition condition;
	private BigDecimal temperature;
	private BigDecimal pressure;
	private Integer humidity;

	private Weather() {
	}

	public String getLocation() {
		return location;
	}

	public Position getPosition() {
		return position;
	}

	public String getLocalTimeAsString() {
		return WeatherSimulatorUtil.format(localTime);
	}

	public LocalDateTime getLocalTime() {
		return localTime;
	}

	public Condition getCondition() {
		return condition;
	}

	public BigDecimal getTemperature() {
		return temperature;
	}

	public BigDecimal getPressure() {
		return pressure;
	}

	public Integer getHumidity() {
		return humidity;
	}

	private void setLocation(String location) {
		this.location = location;
	}

	private void setPosition(Position position) {
		this.position = position;
	}

	private void setLocalTime(LocalDateTime localTime) {
		this.localTime = localTime;
	}

	private void setCondition(Condition condition) {
		this.condition = condition;
	}

	private void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}

	private void setPressure(BigDecimal pressure) {
		this.pressure = pressure;
	}

	private void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public static WeatherBuilder builder() {
		return new Weather().new WeatherBuilder();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((localTime == null) ? 0 : localTime.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weather other = (Weather) obj;
		if (localTime == null) {
			if (other.localTime != null)
				return false;
		} else if (!localTime.equals(other.localTime))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append(location != null ? location : "").append(WeatherSimulatorConstants.SEPERATOR).append(position)
				.append(WeatherSimulatorConstants.SEPERATOR).append(getLocalTimeAsString())
				.append(WeatherSimulatorConstants.SEPERATOR).append(condition.name())
				.append(WeatherSimulatorConstants.SEPERATOR).append(temperature)
				.append(WeatherSimulatorConstants.SEPERATOR).append(pressure)
				.append(WeatherSimulatorConstants.SEPERATOR).append(humidity);
		return sb.toString();
	}

	public class WeatherBuilder {
		Weather weather = new Weather();

		public WeatherBuilder location(String location) {
			weather.setLocation(location);
			return this;
		}

		public WeatherBuilder condition(Condition condition) {
			if (null == condition) {
				throw new WeatherDataSampleException("Condition cannot be set to null");
			}
			weather.setCondition(condition);
			return this;
		}

		public WeatherBuilder localtime(LocalDateTime localtime) {
			if (null == localtime) {
				throw new WeatherDataSampleException("Localtime cannot be set to null");
			}
			weather.setLocalTime(localtime);
			return this;
		}

		public WeatherBuilder temperature(BigDecimal temperature) {
			if (null == temperature) {
				throw new WeatherDataSampleException("Temperature cannot be set to null");
			}
			weather.setTemperature(temperature);
			return this;
		}

		public WeatherBuilder pressure(BigDecimal pressure) {
			if (null == pressure) {
				throw new WeatherDataSampleException("Pressure cannot be set to null");
			}
			weather.setPressure(pressure);
			return this;
		}

		public WeatherBuilder humidity(Integer humidity) {
			if (null == humidity) {
				throw new WeatherDataSampleException("Humidity cannot be set to null");
			}
			weather.setHumidity(humidity);
			return this;
		}

		public WeatherBuilder position(Position position) {
			if (null == position) {
				throw new WeatherDataSampleException("Position cannot be set to null");
			}
			weather.setPosition(position);
			return this;
		}

		public Weather build() {
			if (null == weather.position || null == weather.localTime || null == weather.condition
					|| null == weather.temperature || null == weather.pressure || null == weather.humidity) {
				throw new WeatherDataSampleException("Only Location is an optional to build a Weather");
			}
			return weather;
		}
	}
}
