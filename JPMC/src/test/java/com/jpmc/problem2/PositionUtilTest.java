package com.jpmc.problem2;

import junit.framework.TestCase;

import org.junit.Test;

import com.jpmc.problem2.constants.Constants;
import com.jpmc.problem2.domain.Direction;
import com.jpmc.problem2.domain.Operation;
import com.jpmc.problem2.exception.CommandException;

import static com.jpmc.problem2.constants.Constants.COMMAND_FILE;
import static com.jpmc.problem2.constants.Constants.REVERSAL;
import static com.jpmc.problem2.constants.Constants.SEPERATOR;

;
public class PositionUtilTest {
	@Test
	public void testPackage() {
		TestCase.assertEquals(
				"com.jpmc.problem2.commands.SellAmendCommand",
				QuantityCalculatorCommandUtil
						.getCommand(
								Direction.SELL.name() + SEPERATOR
										+ Operation.AMEND.name()).getClass()
						.getName());
	}

	@Test
	public void testInvalidCommand() {
		String invalidCommand = "InvalidCommand";
		try {
			QuantityCalculatorCommandUtil.getCommand(invalidCommand);
			throw new AssertionError("Invalid Case Passes!!");
		} catch (CommandException e) {
			TestCase.assertEquals("Command String " + invalidCommand
					+ " not defined in " + COMMAND_FILE, e.getMessage());
		}
	}

	@Test
	public void testBuildPosition() {
		TestCase.assertEquals(
				"BuyNewCommand",
				QuantityCalculatorCommandUtil
						.getCommand(
								Direction.BUY.name() + SEPERATOR
										+ Operation.NEW.name()).getClass()
						.getSimpleName());
		TestCase.assertEquals(
				"SellCancelCommand",
				QuantityCalculatorCommandUtil
						.getCommand(
								Direction.SELL.name() + SEPERATOR
										+ Operation.CANCEL.name()).getClass()
						.getSimpleName());
	}

	@Test
	public void testReversalBuildPosition() {
		TestCase.assertEquals(
				"SellNewReversalCommand",
				QuantityCalculatorCommandUtil
						.getCommand(
								Direction.SELL.name() + Constants.SEPERATOR
										+ Operation.NEW.name() + SEPERATOR
										+ REVERSAL).getClass().getSimpleName());
	}
}
