package com.jpmc.problem2.domain;

/**
 * 
 * @author achuth
 *
 */
public class PositionIdentifier {
	private String accountNumber;
	private String instrument;

	public PositionIdentifier(String accountNumber, String instrument) {
		super();
		this.accountNumber = accountNumber;
		this.instrument = instrument;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getInstrument() {
		return instrument;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result
				+ ((instrument == null) ? 0 : instrument.hashCode());
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
		PositionIdentifier other = (PositionIdentifier) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (instrument == null) {
			if (other.instrument != null)
				return false;
		} else if (!instrument.equals(other.instrument))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PositionIdentifier [accountNumber=" + accountNumber
				+ ", instrument=" + instrument + "]";
	}
}
