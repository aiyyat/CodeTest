package com.thoughtworks.problem1.rover.direction;

public interface DirectionFacingState {
	public void moveForward();

	public void moveBackward();

	public void turnLeft();

	public void turnRight();
}
