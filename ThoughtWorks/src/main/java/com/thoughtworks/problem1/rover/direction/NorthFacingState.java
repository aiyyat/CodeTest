package com.thoughtworks.problem1.rover.direction;

import com.thoughtworks.problem1.rover.Rover;

public class NorthFacingState implements DirectionFacingState {
	Rover rover;

	public NorthFacingState(Rover rover) {
		this.rover = rover;
	}

	@Override
	public void moveForward() {
		rover.getCurrentCoordinates().incrementY();
	}

	@Override
	public void moveBackward() {
		rover.getCurrentCoordinates().decrementY();
	}

	@Override
	public void turnLeft() {
		rover.setWestFacing();

	}

	@Override
	public void turnRight() {
		rover.setEastFacing();
	}

	@Override
	public String toString() {
		return "N";
	}
}
