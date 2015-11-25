package com.jpmc.problem2.domain;

/**
 * 
 * @author achuth
 *
 */
public class Trade {
	private Integer tradeId;
	private Integer tradeVersion;
	private String securityId;
	private Integer quantity;
	private String accountNumber;
	private Direction direction;
	private Operation operation;

	public Trade(Integer tradeId, Integer tradeVersion) {
		super();
		this.tradeId = tradeId;
		this.tradeVersion = tradeVersion;
	}

	public Integer getTradeId() {
		return tradeId;
	}

	public Integer getTradeVersion() {
		return tradeVersion;
	}

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tradeId == null) ? 0 : tradeId.hashCode());
		result = prime * result
				+ ((tradeVersion == null) ? 0 : tradeVersion.hashCode());
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
		Trade other = (Trade) obj;
		if (tradeId == null) {
			if (other.tradeId != null)
				return false;
		} else if (!tradeId.equals(other.tradeId))
			return false;
		if (tradeVersion == null) {
			if (other.tradeVersion != null)
				return false;
		} else if (!tradeVersion.equals(other.tradeVersion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", tradeVersion=" + tradeVersion
				+ ", securityId=" + securityId + ", qty=" + quantity
				+ ", accountNumber=" + accountNumber + ", direction="
				+ direction + ", operation=" + operation + "]";
	}
}
