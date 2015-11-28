package com.thoughtworks.problem1.rover.direction;

import com.thoughtworks.problem1.rover.Rover;

public class WestFacingState implements DirectionFacingState {
	Rover rover;

	public WestFacingState(Rover rover) {
		this.rover = rover;
	}

	@Override
	public void moveForward() {
		rover.getCurrentCoordinates().decrementX();
	}

	@Override
	public void moveBackward() {
		rover.getCurrentCoordinates().incrementX();
	}

	@Override
	public void turnLeft() {
		rover.setSouthFacing();

	}

	@Override
	public void turnRight() {
		rover.setNorthFacing();
	}

	@Override
	public String toString() {
		return "W";
	}
}
