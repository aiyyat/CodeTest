package com.technicalyorker.codetest.skyhubdigital.salestax.basket;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 
 * @author achuth
 *
 */
public class Util {
	public static BigDecimal roundTo5Cents(BigDecimal amount) {
		long amt = amount.multiply(Constants.HUNDRED).longValue();
		long mod = amt % 5;
		if (mod != 0) {
			amt = amt + (5 - mod);
		}
		return new BigDecimal(amt).divide(Constants.HUNDRED);
	}

	public static BigDecimal roundToTwoDigits(BigDecimal amount) {
		return amount.setScale(2, RoundingMode.HALF_UP);
	}
}
