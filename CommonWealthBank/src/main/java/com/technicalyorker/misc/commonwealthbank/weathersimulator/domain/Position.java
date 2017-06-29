package com.technicalyorker.misc.commonwealthbank.weathersimulator.domain;

import java.math.BigDecimal;

/**
 * Position describes the lat,long,alt from where the sample was collected.
 * 
 * A Position class is immuatable and encapsulates the attributes of a position
 * entity. The position class most of the time would be used to query data from
 * the Weather Service and hence the input parameter.
 * 
 * @author achuth
 *
 */
public class Position {
	private BigDecimal latitude;
	private BigDecimal longitude;
	private Integer altitude;

	public Position(BigDecimal latitude, BigDecimal longitude, Integer altitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public Integer getAltitude() {
		return altitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altitude == null) ? 0 : altitude.hashCode());
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
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
		Position other = (Position) obj;
		if (altitude == null) {
			if (other.altitude != null)
				return false;
		} else if (!altitude.equals(other.altitude))
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return latitude + "," + longitude + "," + altitude;
	}
}
