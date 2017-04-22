package com.technicalyorker.codetest.skyhubdigital.salestax.basket;

import java.math.BigDecimal;
import java.util.LinkedList;

import com.technicalyorker.codetest.skyhubdigital.salestax.items.Good;

/**
 * 
 * @author achuth
 *
 */
public class Basket extends LinkedList<Good> {
	private static final long serialVersionUID = -8528383503757563554L;
	BigDecimal totalSalesTax = Constants.ZERO;
	BigDecimal totalPrice = Constants.ZERO;

	public void printBill() {
		for (Good good : this) {
			System.out.println("1 " + good.getDescription() + " : " + good.getTotalPrice());
		}
		System.out.println("Sales Tax:" + totalSalesTax);
		System.out.println("Total:" + totalPrice);
	}

	public void finalizeShopping() {
		for (Good good : this) {
			totalSalesTax = totalSalesTax.add(good.getRoundedSalesTaxAmount());
			totalPrice = totalPrice.add(good.getTotalPrice());
		}
	}

	public BigDecimal getTotalSalesTax() {
		validate();
		return totalSalesTax;
	}

	public BigDecimal getTotalPrice() {
		validate();
		return totalPrice;
	}

	@Override
	public String toString() {
		return "Basket [totalSalesTax=" + totalSalesTax + ", totalPrice=" + totalPrice + "]";
	}

	private void validate() {
		if (totalSalesTax.compareTo(Constants.ZERO) == 0) {
			throw new IllegalStateException("Please conclude the shopping before getting the SalesTax or Total Price");
		}
	}
}
