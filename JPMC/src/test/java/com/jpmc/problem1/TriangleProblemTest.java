package com.jpmc.problem1;

import static com.jpmc.problem1.constants.Constants.LINE_SEPERATOR;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author achuth
 *
 */
public class TriangleProblemTest {
	private String input = 
			   "1" + LINE_SEPERATOR + 
			  "2 3" + LINE_SEPERATOR+ 
			 "4 5 6" + LINE_SEPERATOR + 
			"7 8 9 0" + LINE_SEPERATOR;

	private Cell cell1;
	private Cell cell5;
	private Cell cell8;

	private TriangleProblem defaultInput;

	@Before
	public void init() {
		defaultInput = new TriangleProblem(input);
		cell1 = defaultInput.getCells()[0][0];
		cell5 = defaultInput.getCells()[2][1];
		cell8 = defaultInput.getCells()[3][1];
	}

	@Test
	public void testAddRows() {
		TestCase.assertEquals(input, defaultInput.toString());
	}

	@Test
	public void testAdjuscentCells() {
		TestCase.assertEquals("2 3",
				cell1.getAdjacentCellsBelowInPrettyPrint());
		TestCase.assertEquals("8 9",
				cell5.getAdjacentCellsBelowInPrettyPrint());
		TestCase.assertEquals(0, cell8.getAdjacentCellsBelow().size());
	}

	@Test
	public void testOutputOneElement() {
		TestCase.assertEquals(new Integer(1),
				new TriangleProblem("1").getMaxSum());
	}

	@Test
	public void testOutputDefaultInput() {
		TestCase.assertEquals(new Integer(19), defaultInput.getMaxSum());
	}

	@Test
	public void testInvalidCharacterScenario() {
		input =  "a" + LINE_SEPERATOR + 
				"2 3" + LINE_SEPERATOR;
		try {
			new TriangleProblem(input).getMaxSum();
			throw new AssertionError("An Invalid Case passes.");
		} catch (Exception e) {
			TestCase.assertEquals(
					"Illegal Argument 'a' found in line number 1 of input",
					e.getMessage());
		}
	}
	
	@Test
	public void testInvalidElementCount() {
		input =  
                   "1" + LINE_SEPERATOR + 
                  "2 3" + LINE_SEPERATOR+ 
                 "4 5 6" + LINE_SEPERATOR + 
                 "7 8 9" + LINE_SEPERATOR;
		try {
			new TriangleProblem(input).getMaxSum();
			throw new AssertionError("An Invalid Case passes.");
		} catch (Exception e) {
			TestCase.assertEquals("Elements in line 3, Expected: 4 Actual: 3",
					e.getMessage());
		}
	}
	
	@Test
	public void testJPMCQuestion() {
		input =  
                     "3" + LINE_SEPERATOR + 
                    "7 4" + LINE_SEPERATOR + 
                   "2 4 6" + LINE_SEPERATOR + 
                  "8 5 9 3" + LINE_SEPERATOR;
		TestCase.assertEquals(new Integer(23),
				new TriangleProblem(input).getMaxSum());
	}
}
