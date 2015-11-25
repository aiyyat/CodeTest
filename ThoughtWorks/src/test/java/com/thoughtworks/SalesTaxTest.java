package com.thoughtworks;

import java.math.BigDecimal;

import org.junit.Test;

import com.thoughtworks.problem2.Basket;
import com.thoughtworks.problem2.items.Book;
import com.thoughtworks.problem2.items.CD;
import com.thoughtworks.problem2.items.Chocolate;
import com.thoughtworks.problem2.items.HeadAchePills;
import com.thoughtworks.problem2.items.ImportedGood;
import com.thoughtworks.problem2.items.Perfume;

import junit.framework.TestCase;

public class SalesTaxTest {

	@Test
	public void testInput0() {
		Basket basket = new Basket();
		basket.add(new CD(new BigDecimal("14.99")));
		basket.finalizeShopping();
		basket.printBill();
		TestCase.assertEquals("16.49", basket.getTotalPrice().toPlainString());
	}

	@Test
	public void testInput1() {
		Basket basket = new Basket();
		basket.add(new Book(new BigDecimal("12.49")));
		basket.add(new CD(new BigDecimal("14.99")));
		basket.add(new Chocolate(new BigDecimal("0.85")));
		basket.finalizeShopping();
		basket.printBill();
		TestCase.assertEquals("29.83", basket.getTotalPrice().toPlainString());
		TestCase.assertEquals("1.5", basket.getTotalSalesTax().toPlainString());
	}

	@Test
	public void testInput2() {
		Basket basket = new Basket();
		basket.add(new ImportedGood(new Chocolate(new BigDecimal("10.00"))));
		basket.add(new ImportedGood(new Perfume(new BigDecimal("47.50"))));
		basket.finalizeShopping();
		basket.printBill();
		TestCase.assertEquals("65.15", basket.getTotalPrice().toPlainString());
		TestCase.assertEquals("7.65", basket.getTotalSalesTax().toPlainString());
	}

	@Test
	public void testInput3() {
		Basket basket = new Basket();
		basket.add(new ImportedGood(new Perfume(new BigDecimal("27.99"))));
		basket.add(new Perfume(new BigDecimal("18.99")));
		basket.add(new HeadAchePills(new BigDecimal("9.75")));
		basket.add(new ImportedGood(new Chocolate(new BigDecimal("11.25"))));
		basket.finalizeShopping();
		basket.printBill();

		TestCase.assertEquals("6.7", basket.getTotalSalesTax().toPlainString());
		TestCase.assertEquals("74.68", basket.getTotalPrice().toPlainString());
	}

	@Test
	public void test() {
		Basket basket = new Basket();
		basket.add(new Perfume(new BigDecimal("18.99")));
		basket.finalizeShopping();
		basket.printBill();
	}
}
