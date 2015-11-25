package com.jpmc.problem2.container;

import java.util.List;
import java.util.Map;

import com.jpmc.problem2.domain.Position;
import com.jpmc.problem2.domain.PositionIdentifier;
import com.jpmc.problem2.domain.Trade;

/**
 * 
 * @author achuth
 *
 */
public interface PositionTradeContainer {
	public Map<Integer, Trade> getAllTrades();

	public Trade getTradeById(Integer tradeId);

	public void addTrade(Trade trade);

	public void addPosition(Position position);

	public Position getPositionById(PositionIdentifier id);

	public Map<PositionIdentifier, Position> getAllPositions();

	public List<Position> getPositionsList();

	public Position getPositionByTrade(Trade trade);

	public Position getPositionByTradeNewIfNotExists(Trade trade);
}
