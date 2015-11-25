package com.jpmc.problem2;

import com.jpmc.problem2.container.PositionTradeContainer;
import com.jpmc.problem2.container.PositionTradeContainerImpl;
import com.jpmc.problem2.domain.Position;
import com.jpmc.problem2.domain.Trade;

/**
 * 
 * @author achuth
 *
 */
public class PositionAggregator {
	private final PositionTradeContainer container = new PositionTradeContainerImpl();

	public void addTrade(Trade newTrade) {
		Position position = container
				.getPositionByTradeNewIfNotExists(newTrade);
		Trade existingTrade = container.getAllTrades().get(
				newTrade.getTradeId());
		if (null != existingTrade) {
			if (newTrade.getTradeVersion() >= existingTrade.getTradeVersion()) {
				Position existingPosition = container
						.getPositionByTrade(existingTrade);
				existingPosition.setQuantity(QuantityCalculatorCommandUtil.deriveTheReversalCommandOfTrade(
						existingTrade).performCalculation(existingPosition,
						existingTrade));
			} else {
				return;
			}
		}
		position.setQuantity(QuantityCalculatorCommandUtil.derviceTheCommandOfTrade(newTrade)
				.performCalculation(position, newTrade));
		position.addTrade(newTrade);
		container.addTrade(newTrade);
	}

	public PositionTradeContainer getContainer() {
		return container;
	}
}
