package com.jpmc.problem2.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author achuth
 *
 */
public class Position {
	private PositionIdentifier id;
	private Integer quantity = 0;
	private Set<Integer> trades = new HashSet<Integer>();

	public Position(PositionIdentifier id) {
		this.id = id;
	}

	public PositionIdentifier getId() {
		return id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void addTrade(Trade trade) {
		trades.add(trade.getTradeId());
	}

	public Set<Integer> getTrades() {
		return trades;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", quantity=" + quantity + ", trades="
				+ trades + "]";
	}
}
