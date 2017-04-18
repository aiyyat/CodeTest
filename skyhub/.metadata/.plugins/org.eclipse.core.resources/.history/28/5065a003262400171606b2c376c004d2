package com.thoughtworks.problem2;

import static com.thoughtworks.problem2.Constants.HUNDRED;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {
	public static BigDecimal roundTo5Cents(BigDecimal amount) {
		long amt = amount.multiply(Constants.HUNDRED).longValue();
		long mod = amt % 5;
		if (mod != 0) {
			amt = amt + (5 - mod);
		}
		return new BigDecimal(amt).divide(HUNDRED);
	}

	public static BigDecimal roundToTwoDigits(BigDecimal amount) {
		return amount.setScale(2, RoundingMode.HALF_UP);
	}
}
