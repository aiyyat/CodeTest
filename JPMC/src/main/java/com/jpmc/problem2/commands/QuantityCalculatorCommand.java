package com.jpmc.problem2.commands;

import com.jpmc.problem2.domain.Position;
import com.jpmc.problem2.domain.Trade;

/**
 * 
 * @author achuth
 *
 */
public interface QuantityCalculatorCommand {
	public Integer performCalculation(Position position, Trade trade);
}
