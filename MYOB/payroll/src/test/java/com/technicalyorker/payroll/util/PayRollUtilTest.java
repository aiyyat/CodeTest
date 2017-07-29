package com.technicalyorker.payroll.util;

import java.math.BigDecimal;

import junit.framework.TestCase;

import org.junit.Test;

import com.technicalyorker.payroll.domain.SalaryEntry;
import com.technicalyorker.payroll.exception.InputSourceReadException;

public class PayRollUtilTest {

	@Test
	public void optimisticSalaryScenario() {
		SalaryEntry actual = PayRollUtil
				.parse("David,Rudd,60050,9%,01 March – 31 March");
		SalaryEntry expected = SalaryEntry.builder().firstName("David")
				.lastName("Rudd").annualSalary("60050").superRate("9")
				.paymentStartDate("01 March – 31 March").build();
		TestCase.assertEquals(actual, expected);
	}

	@Test(expected = InputSourceReadException.class)
	public void pessimisticScenario() {
		PayRollUtil.parse("David,Rudd,60050,badvalue%,01 March – 31 March");
	}

	@Test
	public void incomeTaxScenario1() {
		TestCase.assertEquals(new BigDecimal(922),
				PayRollUtil.incomeTax(new BigDecimal(60050)));
	}

	@Test
	public void incomeTaxScenario2() {
		TestCase.assertEquals(new BigDecimal(2696),
				PayRollUtil.incomeTax(new BigDecimal(120000)));
	}

	@Test
	public void grossIncomeScenario1() {
		TestCase.assertEquals(new BigDecimal(5004),
				PayRollUtil.grossIncome(new BigDecimal(60050)));
	}

	@Test
	public void grossIncomeScenario2() {
		TestCase.assertEquals(new BigDecimal(10000),
				PayRollUtil.grossIncome(new BigDecimal(120000)));
	}

	@Test
	public void netIncomeScenario1() {
		TestCase.assertEquals(new BigDecimal(4082), PayRollUtil.netIncome(
				new BigDecimal(5004), new BigDecimal(922)));
	}

	@Test
	public void netIncomeScenario2() {
		TestCase.assertEquals(new BigDecimal(7304), PayRollUtil.netIncome(
				new BigDecimal(10000), new BigDecimal(2696)));
	}
}
