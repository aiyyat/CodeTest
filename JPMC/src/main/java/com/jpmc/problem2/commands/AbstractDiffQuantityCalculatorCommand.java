package com.jpmc.problem2.commands;

import com.jpmc.problem2.domain.Position;
import com.jpmc.problem2.domain.Trade;

/**
 * 
 * @author achuth
 *
 */
public abstract class AbstractDiffQuantityCalculatorCommand extends
		AbstractQuantityCalculatorCommand {
	public Integer calculate(Position position, Trade trade) {
		return position.getQuantity() - trade.getQuantity();
	}
}
