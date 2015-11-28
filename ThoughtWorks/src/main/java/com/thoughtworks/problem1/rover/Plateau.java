package com.thoughtworks.problem1.rover;

import com.thoughtworks.problem1.rover.exception.PlauteuBoundaryExceededException;

public class Plateau {

	private int lowerXLimit = 0;
	private int lowerYLimit = 0;
	private int upperXLimit;
	private int upperYLimit;

	public Plateau(Coordinates limit) {
		super();
		this.upperXLimit = limit.getX();
		this.upperYLimit = limit.getY();
	}

	public int getLowerXLimit() {
		return lowerXLimit;
	}

	public int getLowerYLimit() {
		return lowerYLimit;
	}

	public int getUpperXLimit() {
		return upperXLimit;
	}

	public int getUpperYLimit() {
		return upperYLimit;
	}

	public void validateCoordinates(Coordinates input) {
		validateCoordinates(input.getX(), input.getY());
	}

	public void validateCoordinates(int x, int y) {
		if (x < lowerXLimit || x > upperXLimit || y < lowerYLimit || y > upperYLimit) {
			throw new PlauteuBoundaryExceededException("Coordinates" + x + "," + y + " should not exceed " + toString());
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lowerXLimit;
		result = prime * result + lowerYLimit;
		result = prime * result + upperXLimit;
		result = prime * result + upperYLimit;
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
		Plateau other = (Plateau) obj;
		if (lowerXLimit != other.lowerXLimit)
			return false;
		if (lowerYLimit != other.lowerYLimit)
			return false;
		if (upperXLimit != other.upperXLimit)
			return false;
		if (upperYLimit != other.upperYLimit)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Plateau [lowerXLimit=" + lowerXLimit + ", lowerYLimit=" + lowerYLimit + ", upperXLimit=" + upperXLimit
				+ ", upperYLimit=" + upperYLimit + "]";
	}

}
