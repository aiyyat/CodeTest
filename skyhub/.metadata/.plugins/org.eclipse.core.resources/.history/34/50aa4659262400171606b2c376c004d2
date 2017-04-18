package com.thoughtworks.problem2.items;

import static com.thoughtworks.problem2.Util.roundTo5Cents;
import static com.thoughtworks.problem2.Util.roundToTwoDigits;

import java.math.BigDecimal;

/**
 * Describes any Good that a shop can sell.
 * 
 * Getting and Setting Price should only be called by Items, never by a client.
 * So do not change the access modifier from default.
 * 
 * Calculation of Sales Tax Amount and Total Price must be at one place, never
 * to be overridden hence do not change from final.
 * 
 * @author achuth
 *
 */
public abstract class Good {
	private BigDecimal price;

	final BigDecimal getPrice() {
		return price;
	}

	final void setPrice(BigDecimal price) {
		this.price = price;
	}

	final void setPrice(double price) {
		setPrice(new BigDecimal(price));
	}

	public String getDescription() {
		return getClass().getSimpleName();
	}

	abstract BigDecimal getSalesTaxAmount();

	public final BigDecimal getRoundedSalesTaxAmount() {
		return roundTo5Cents(getSalesTaxAmount());
	}

	public final BigDecimal getTotalPrice() {
		return roundToTwoDigits(getPrice().add(getRoundedSalesTaxAmount()));
	}

	@Override
	public String toString() {
		return "Good [getDescription()=" + getDescription() + ", getPrice()=" + getPrice()
				+ ", getRoundedSalesTaxAmount()=" + getRoundedSalesTaxAmount() + ", getSalesTaxAmount()="
				+ getSalesTaxAmount() + ", getTotalRoundedPrice()=" + getTotalPrice() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
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
		Good other = (Good) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
}
