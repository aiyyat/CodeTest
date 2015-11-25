package com.jpmc.problem2.commands;

import com.jpmc.problem2.domain.Position;
import com.jpmc.problem2.domain.Trade;
import com.jpmc.problem2.exception.TradeValidationException;

/**
 * 
 * @author achuth
 *
 */
public abstract class AbstractQuantityCalculatorCommand implements
		QuantityCalculatorCommand {
	public Integer performCalculation(Position position, Trade trade) {
		validateTrade(trade);
		return calculate(position, trade);
	}

	private void validateTrade(Trade trade) {
		if (null == trade) {
			throw new TradeValidationException("Trade cannot be empty or null");
		}
		if (null == trade.getQuantity() || null == trade.getAccountNumber()
				|| null == trade.getSecurityId()) {
			throw new TradeValidationException(
					"Trade missing one of AccountNumber,SecurityId or Quantity Attributes");
		}
	}

	public abstract Integer calculate(Position position, Trade trade);
}
