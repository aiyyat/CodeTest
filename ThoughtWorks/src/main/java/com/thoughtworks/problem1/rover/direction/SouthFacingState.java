package com.thoughtworks.problem1.rover.direction;

import com.thoughtworks.problem1.rover.Rover;

public class SouthFacingState implements DirectionFacingState {
	Rover rover;

	public SouthFacingState(Rover rover) {
		this.rover = rover;
	}

	@Override
	public void moveForward() {
		rover.getCurrentCoordinates().decrementY();
	}

	@Override
	public void moveBackward() {
		rover.getCurrentCoordinates().incrementY();
	}

	@Override
	public void turnLeft() {
		rover.setEastFacing();

	}

	@Override
	public void turnRight() {
		rover.setWestFacing();
	}

	@Override
	public String toString() {
		return "S";
	}
}
