package com.thoughtworks.problem2.items;

import static com.thoughtworks.problem2.Constants.ZERO_POINT_ZERO_FIVE;

import java.math.BigDecimal;

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
		return good.getSalesTaxAmount().add(good.getPrice().multiply(ZERO_POINT_ZERO_FIVE));
	}

	@Override
	public String getDescription() {
		return "Imported " + good.getDescription();
	}
}
