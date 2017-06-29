package com.technicalyorker.codetest.skyhubdigital.salestax.items;

import java.math.BigDecimal;

import com.technicalyorker.codetest.skyhubdigital.salestax.basket.Constants;

/**
 * 
 * @author achuth
 *
 */
public abstract class NoBasicSalesTaxGood extends Good {
	@Override
	BigDecimal getSalesTaxAmount() {
		return Constants.ZERO;
	}
}
