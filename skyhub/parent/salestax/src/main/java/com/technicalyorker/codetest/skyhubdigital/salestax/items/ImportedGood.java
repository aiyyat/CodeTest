package com.technicalyorker.codetest.skyhubdigital.salestax.items;

import java.math.BigDecimal;

import com.technicalyorker.codetest.skyhubdigital.salestax.basket.Constants;

/**
 * 
 * @author achuth
 *
 */
public class ImportedGood extends Good {
	private Good good;

	public ImportedGood(Good good) {
		super();
		if (good instanceof ImportedGood) {
			throw new IllegalStateException("Goods once imported cannot be imported again");
		}
		this.good = good;
		super.setPrice(good.getPrice());
	}

	@Override
	BigDecimal getSalesTaxAmount() {
		return good.getSalesTaxAmount().add(good.getPrice().multiply(Constants.ZERO_POINT_ZERO_FIVE));
	}

	@Override
	public String getDescription() {
		return "Imported " + good.getDescription();
	}
}
