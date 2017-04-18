package com.technicalyorker.codetest.skyhubdigital.salestax.items;

import java.math.BigDecimal;

public class Book extends NoBasicSalesTaxGood {
	public Book(BigDecimal bigDecimal) {
		super();
		setPrice(bigDecimal);
	}
}
