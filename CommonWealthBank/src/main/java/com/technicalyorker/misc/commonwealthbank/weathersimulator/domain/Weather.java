package com.technicalyorker.misc.commonwealthbank.weathersimulator.domain;

import java.util.Calendar;

/**
 * @author achuth
 *
 */
public class Weather {
	private String location;
	private Position position;
	private Calendar localTime;
	private Condition condition;
	private String temperature;
	private String pressure;
	private Integer humidity;

	private Weather() {
	}

	public String getLocation() {
		return location;
	}

	public Position getPosition() {
		return position;
	}

	public Calendar getLocalTime() {
		return localTime;
	}

	public Condition getCondition() {
		return condition;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getPressure() {
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

	private void setLocalTime(Calendar localTime) {
		this.localTime = localTime;
	}

	private void setCondition(Condition condition) {
		this.condition = condition;
	}

	private void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	private void setPressure(String pressure) {
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
		return "Weather [location=" + location + ", position=" + position + ", localTime=" + localTime + ", condition="
				+ condition + ", temperature=" + temperature + ", pressure=" + pressure + ", humidity=" + humidity
				+ "]";
	}

	public class WeatherBuilder {
		Weather weather = new Weather();

		public WeatherBuilder location(String location) {
			weather.setLocation(location);
			return this;
		}

		public WeatherBuilder condition(Condition condition) {
			weather.setCondition(condition);
			return this;
		}

		public WeatherBuilder localtime(Calendar localtime) {
			weather.setLocalTime(localtime);
			return this;
		}

		public WeatherBuilder temperature(String temperature) {
			weather.setTemperature(temperature);
			return this;
		}

		public WeatherBuilder pressure(String pressure) {
			weather.setPressure(pressure);
			return this;
		}

		public WeatherBuilder humidity(Integer humidity) {
			weather.setHumidity(humidity);
			return this;
		}

		public WeatherBuilder position(Position position) {
			weather.setPosition(position);
			return this;
		}

		public Weather build() {
			return weather;
		}
	}
}
