package com.thoughtworks.problem1.rover.direction;

import com.thoughtworks.problem1.rover.Rover;

public class EastFacingState implements DirectionFacingState {
	Rover rover;

	public EastFacingState(Rover rover) {
		this.rover = rover;
	}

	@Override
	public void moveForward() {
		rover.getCurrentCoordinates().incrementX();
	}

	@Override
	public void moveBackward() {
		rover.getCurrentCoordinates().decrementX();
	}

	@Override
	public void turnLeft() {
		rover.setNorthFacing();
	}

	@Override
	public void turnRight() {
		rover.setSouthFacing();
	}

	@Override
	public String toString() {
		return "E";
	}

}
