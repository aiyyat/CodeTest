package com.thoughtworks.problem1.rover;

import com.thoughtworks.problem1.rover.direction.DirectionFacingState;
import com.thoughtworks.problem1.rover.direction.EastFacingState;
import com.thoughtworks.problem1.rover.direction.NorthFacingState;
import com.thoughtworks.problem1.rover.direction.SouthFacingState;
import com.thoughtworks.problem1.rover.direction.WestFacingState;
import com.thoughtworks.problem1.rover.exception.MissingInitializationParameterException;

public class Rover {
	Plateau plateau;
	Coordinates currentCoordinates;
	private DirectionFacingState NORTH = new NorthFacingState(this);
	private DirectionFacingState SOUTH = new SouthFacingState(this);
	private DirectionFacingState WEST = new WestFacingState(this);
	private DirectionFacingState EAST = new EastFacingState(this);
	private DirectionFacingState facingDirection;

	public Rover() {
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public Coordinates getCurrentCoordinates() {
		return currentCoordinates;
	}

	public void setCurrentCoordinates(Coordinates currentCoordinates) {
		this.currentCoordinates = currentCoordinates;
	}

	public void acceptInput(String input) {
		if (currentCoordinates == null || plateau == null || facingDirection == null) {
			throw new MissingInitializationParameterException(
					"A Rover must have Initial Coordinates,Plateu and Initial Direction Parameters to accept Inputs");
		}
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			// System.out.print(ch + " ");
			switch (ch) {
			case 'L':
				facingDirection.turnLeft();
				break;
			case 'R':
				facingDirection.turnRight();
				break;
			case 'M':
				facingDirection.moveForward();
				break;
			case 'B':
				facingDirection.moveBackward();
				break;
			}
			// System.out.println(currentCoordinates + " " + facingDirection);
		}
	}

	public void setFacingDirection(char direction) {
		switch (direction) {
		case 'N':
			this.facingDirection = NORTH;
			break;
		case 'E':
			this.facingDirection = EAST;
			break;
		case 'W':
			this.facingDirection = WEST;
			break;
		case 'S':
			this.facingDirection = SOUTH;
			break;
		}
	}

	public DirectionFacingState getFacingDirection() {
		return facingDirection;
	}

	public void setNorthFacing() {
		this.facingDirection = NORTH;
	}

	public void setSouthFacing() {
		this.facingDirection = SOUTH;
	}

	public void setEastFacing() {
		this.facingDirection = EAST;
	}

	public void setWestFacing() {
		this.facingDirection = WEST;
	}
}
