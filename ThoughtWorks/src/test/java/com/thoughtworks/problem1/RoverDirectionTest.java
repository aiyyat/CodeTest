package com.thoughtworks.problem1;

import org.junit.Test;

import com.thoughtworks.problem1.rover.Coordinates;
import com.thoughtworks.problem1.rover.Plateau;
import com.thoughtworks.problem1.rover.Rover;

import junit.framework.TestCase;

public class RoverDirectionTest {

	@Test
	public void testLeftTest() {
		Rover rover = new Rover();
		rover.setPlateau(new Plateau(new Coordinates(5, 5)));
		rover.setCurrentCoordinates(new Coordinates(0, 0));
		rover.setFacingDirection('N');
		rover.acceptInput("L");
		TestCase.assertEquals("W", rover.getFacingDirection().toString());
		rover.acceptInput("L");
		TestCase.assertEquals("S", rover.getFacingDirection().toString());
		rover.acceptInput("L");
		TestCase.assertEquals("E", rover.getFacingDirection().toString());
		rover.acceptInput("L");
		TestCase.assertEquals("N", rover.getFacingDirection().toString());
	}

	@Test
	public void testRightTest() {
		Rover rover = new Rover();
		rover.setPlateau(new Plateau(new Coordinates(5, 5)));
		rover.setCurrentCoordinates(new Coordinates(0, 0));
		rover.setFacingDirection('N');
		rover.acceptInput("R");
		TestCase.assertEquals("E", rover.getFacingDirection().toString());
		rover.acceptInput("R");
		TestCase.assertEquals("S", rover.getFacingDirection().toString());
		rover.acceptInput("R");
		TestCase.assertEquals("W", rover.getFacingDirection().toString());
		rover.acceptInput("R");
		TestCase.assertEquals("N", rover.getFacingDirection().toString());
	}
}
