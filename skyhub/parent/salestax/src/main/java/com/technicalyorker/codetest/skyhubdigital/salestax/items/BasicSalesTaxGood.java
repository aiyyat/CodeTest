package com.technicalyorker.codetest.skyhubdigital.salestax.items;

import java.math.BigDecimal;

import com.technicalyorker.codetest.skyhubdigital.salestax.basket.Constants;

public abstract class BasicSalesTaxGood extends Good {
	@Override
	BigDecimal getSalesTaxAmount() {
		return getPrice().multiply(Constants.ZERO_POINT_ONE);
	}
}
