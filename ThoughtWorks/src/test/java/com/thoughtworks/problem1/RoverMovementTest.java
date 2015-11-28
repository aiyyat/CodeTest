package com.thoughtworks.problem1;

import org.junit.Test;

import com.thoughtworks.problem1.rover.Coordinates;
import com.thoughtworks.problem1.rover.Plateau;
import com.thoughtworks.problem1.rover.Rover;

import junit.framework.TestCase;

public class RoverMovementTest {
	@Test
	public void testNorthMovementTest() {
		Rover rover = new Rover();
		rover.setPlateau(new Plateau(new Coordinates(5, 5)));
		rover.setCurrentCoordinates(new Coordinates(1, 1));
		rover.setFacingDirection('N');
		rover.acceptInput("M");
		TestCase.assertEquals("1 2", rover.getCurrentCoordinates().toString());
		rover.acceptInput("B");
		TestCase.assertEquals("1 1", rover.getCurrentCoordinates().toString());
	}

	@Test
	public void testSouthMovementTest() {
		Rover rover = new Rover();
		rover.setPlateau(new Plateau(new Coordinates(5, 5)));
		rover.setCurrentCoordinates(new Coordinates(1, 1));
		rover.setFacingDirection('S');
		rover.acceptInput("M");
		TestCase.assertEquals("1 0", rover.getCurrentCoordinates().toString());
		rover.acceptInput("B");
		TestCase.assertEquals("1 1", rover.getCurrentCoordinates().toString());
	}

	@Test
	public void testWestMovementTest() {
		Rover rover = new Rover();
		rover.setPlateau(new Plateau(new Coordinates(5, 5)));
		rover.setCurrentCoordinates(new Coordinates(1, 1));
		rover.setFacingDirection('W');
		rover.acceptInput("M");
		TestCase.assertEquals("0 1", rover.getCurrentCoordinates().toString());
		rover.acceptInput("B");
		TestCase.assertEquals("1 1", rover.getCurrentCoordinates().toString());
	}

	@Test
	public void testEasthMovementTest() {
		Rover rover = new Rover();
		rover.setPlateau(new Plateau(new Coordinates(5, 5)));
		rover.setCurrentCoordinates(new Coordinates(1, 1));
		rover.setFacingDirection('E');
		rover.acceptInput("M");
		TestCase.assertEquals("2 1", rover.getCurrentCoordinates().toString());
		rover.acceptInput("B");
		TestCase.assertEquals("1 1", rover.getCurrentCoordinates().toString());
	}
}
