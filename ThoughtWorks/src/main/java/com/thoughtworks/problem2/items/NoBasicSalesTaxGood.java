package com.thoughtworks.problem2.items;

import java.math.BigDecimal;

import com.thoughtworks.problem2.Constants;

public abstract class NoBasicSalesTaxGood extends Good {
	@Override
	BigDecimal getSalesTaxAmount() {
		return Constants.ZERO;
	}
}
