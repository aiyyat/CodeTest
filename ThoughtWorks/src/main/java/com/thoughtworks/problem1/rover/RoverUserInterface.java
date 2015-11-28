package com.thoughtworks.problem1.rover;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RoverUserInterface {
	private Rover rover;

	public static void main(String[] args) {
		new RoverUserInterface().perform(new ByteArrayInputStream("5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM\n".getBytes()));
	}

	public void perform(InputStream is) {
		Scanner s = new Scanner(is);
		Plateau plateau = new Plateau(new Coordinates(s.nextInt(), s.nextInt()));
		String firstLineInput = null;
		s.nextLine();
		try {
			while (null != (firstLineInput = s.nextLine())) {
				String[] splitFirstLineInput = firstLineInput.split("\\s");
				int initialX = Integer.parseInt(splitFirstLineInput[0]);
				int initialY = Integer.parseInt(splitFirstLineInput[1]);

				rover = new Rover();
				rover.setPlateau(plateau);
				rover.setCurrentCoordinates(new Coordinates(initialX, initialY));
				rover.setFacingDirection(splitFirstLineInput[2].charAt(0));
				rover.acceptInput(s.nextLine());

				System.out.println(rover.getCurrentCoordinates() + " " + rover.getFacingDirection());
			}
		} catch (NoSuchElementException e) {
		}
		s.close();
	}
}
