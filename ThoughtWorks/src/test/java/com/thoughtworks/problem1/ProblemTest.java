package com.thoughtworks.problem1;

import org.junit.Test;

import com.thoughtworks.problem1.rover.Coordinates;
import com.thoughtworks.problem1.rover.Plateau;
import com.thoughtworks.problem1.rover.Rover;

import junit.framework.TestCase;

public class ProblemTest {

	@Test
	public void testCase1() {
		Rover rover = new Rover();
		rover.setPlateau(new Plateau(new Coordinates(5, 5)));
		rover.setCurrentCoordinates(new Coordinates(1, 2));
		rover.setFacingDirection('N');
		rover.acceptInput("LMLMLMLMM");
		TestCase.assertEquals("1 3 N", rover.getCurrentCoordinates() + " " + rover.getFacingDirection());
	}

	@Test
	public void testCase2() {
		Rover rover = new Rover();
		rover.setPlateau(new Plateau(new Coordinates(5, 5)));
		rover.setCurrentCoordinates(new Coordinates(3, 3));
		rover.setFacingDirection('E');
		rover.acceptInput("MMRMMRMRRM");
		TestCase.assertEquals("5 1 E", rover.getCurrentCoordinates() + " " + rover.getFacingDirection());
	}

}
