package com.thoughtworks.problem2.items;

import java.math.BigDecimal;
import static com.thoughtworks.problem2.Constants.ZERO_POINT_ZERO_ONE;

public abstract class BasicSalesTaxGood extends Good {
	@Override
	BigDecimal getSalesTaxAmount() {
		return getPrice().multiply(ZERO_POINT_ZERO_ONE);
	}
}
