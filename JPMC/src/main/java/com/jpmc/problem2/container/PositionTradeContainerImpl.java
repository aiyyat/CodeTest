package com.jpmc.problem2.container;

import java.util.HashMap;
import java.util.LinkedList;
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
public class PositionTradeContainerImpl implements PositionTradeContainer {
	private final Map<PositionIdentifier, Position> positions = new HashMap<PositionIdentifier, Position>();
	private final Map<Integer, Trade> trades = new HashMap<Integer, Trade>();

	public Map<Integer, Trade> getAllTrades() {
		return trades;
	}

	public Trade getTradeById(Integer tradeId) {
		return trades.get(tradeId);
	}

	public void addTrade(Trade trade) {
		trades.put(trade.getTradeId(), trade);
	}

	public void addPosition(Position position) {
		positions.put(position.getId(), position);
	}

	public Position getPositionById(PositionIdentifier id) {
		return positions.get(id);
	}

	public Map<PositionIdentifier, Position> getAllPositions() {
		return positions;
	}

	public List<Position> getPositionsList() {
		return new LinkedList<Position>(positions.values());
	}

	public Position getPositionByTrade(Trade trade) {
		return positions.get(new PositionIdentifier(trade.getAccountNumber(),
				trade.getSecurityId()));
	}

	public Position getPositionByTradeNewIfNotExists(Trade trade) {
		Position p = getPositionByTrade(trade);
		if (null == p) {
			PositionIdentifier id = new PositionIdentifier(
					trade.getAccountNumber(), trade.getSecurityId());
			positions.put(id, p = new Position(id));
		}
		return p;
	}
}
