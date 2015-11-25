package com.jpmc.problem2;

import static com.jpmc.problem2.constants.Constants.COMMAND_FILE;
import static com.jpmc.problem2.constants.Constants.REVERSAL;
import static com.jpmc.problem2.constants.Constants.SEPERATOR;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.jpmc.problem2.commands.QuantityCalculatorCommand;
import com.jpmc.problem2.domain.Trade;
import com.jpmc.problem2.exception.CommandException;

/**
 * 
 * @author achuth
 *
 */
public class QuantityCalculatorCommandUtil {
	private static final ResourceBundle bundle = ResourceBundle
			.getBundle(COMMAND_FILE);
	private static final Map<String, QuantityCalculatorCommand> cache = new HashMap<String, QuantityCalculatorCommand>();

	public static final QuantityCalculatorCommand derviceTheCommandOfTrade(
			Trade trade) {
		return getCommand(deriveSelectionKeyOfTrade(trade));
	}

	public static final QuantityCalculatorCommand deriveTheReversalCommandOfTrade(
			Trade trade) {
		return getCommand(deriveTheReversalSelectionKeyOfTrade(trade));
	}

	private static String deriveSelectionKeyOfTrade(Trade trade) {
		return trade.getDirection().name() + SEPERATOR
				+ trade.getOperation().name();
	}

	private static String deriveTheReversalSelectionKeyOfTrade(Trade trade) {
		return deriveSelectionKeyOfTrade(trade) + SEPERATOR + REVERSAL;
	}

	public static QuantityCalculatorCommand getCommand(String command) {
		String commandClassName = readQualifiedClassName(command);
		QuantityCalculatorCommand commandClass = cache.get(commandClassName);
		if (null == commandClass) {
			commandClass = createQuantityCommand(commandClassName);
			cache.put(commandClassName, commandClass);
		}
		return commandClass;
	}

	private static String readQualifiedClassName(String key) {
		try {
			return bundle.getString("PACKAGE") + bundle.getString(key);
		} catch (MissingResourceException e) {
			throw new CommandException("Command String " + key
					+ " not defined in " + COMMAND_FILE);
		}
	}

	private static QuantityCalculatorCommand createQuantityCommand(
			String commandClassName) {
		try {
			return (QuantityCalculatorCommand) (Class.forName(commandClassName)
					.newInstance());
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			throw new CommandException(
					"Class Definition Not Found for Command Class"
							+ commandClassName + " as defined in "
							+ COMMAND_FILE);
		}
	}
}
