package com.technicalyorker.payroll.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.technicalyorker.payroll.domain.SalaryEntry;
import com.technicalyorker.payroll.exception.InputSourceReadException;

/**
 * Utility to support PayRoll.
 * 
 * @author achuth
 *
 */
public class PayRollUtil {

	private PayRollUtil() {
		super();
	}

	/**
	 * Method to parse a line, to extract a SalaryEntry
	 * 
	 * @param line
	 * @return
	 */
	public static SalaryEntry parse(String line) {
		try {
			String[] split = line.split(",");
			if (split.length < 4) {
				throw new InputSourceReadException("Input CSV invalid at line:"
						+ line);
			}
			return new SalaryEntry.SalaryEntryBuilder().firstName(split[0])
					.lastName(split[1]).annualSalary(split[2])
					.paymentStartDate(split[4]).superRate(split[3]).build();
		} catch (Exception e) {
			throw new InputSourceReadException(e);
		}
	}

	/**
	 * Method to Calculate the income Tax for any given salary
	 * 
	 * @param income
	 * @return
	 */
	public static BigDecimal incomeTax(BigDecimal income) {
		BigDecimal PERC = null;
		BigDecimal SUM = null;
		BigDecimal MAX = null;
		BigDecimal _18200 = new BigDecimal(18200);
		BigDecimal _37000 = new BigDecimal(37000);
		BigDecimal _80000 = new BigDecimal(80000);
		BigDecimal _180000 = new BigDecimal(180000);
		if (income.compareTo(_180000) > 0) {
			PERC = new BigDecimal(45);
			MAX = _180000;
			SUM = new BigDecimal(54547);
		} else if (income.compareTo(_80000) > 0) {
			PERC = new BigDecimal(37);
			MAX = _80000;
			SUM = new BigDecimal(17547);
		} else if (income.compareTo(_37000) > 0) {
			PERC = new BigDecimal(32.5);
			MAX = _37000;
			SUM = new BigDecimal(3572);
		} else if (income.compareTo(_18200) > 0) {
			PERC = new BigDecimal(19);
			MAX = _18200;
			SUM = BigDecimal.ZERO;
		} else {
			PERC = BigDecimal.ZERO;
			MAX = BigDecimal.ZERO;
			SUM = BigDecimal.ZERO;
		}
		return ((PERC.divide(new BigDecimal(100))
				.multiply(income.subtract(MAX))).add(SUM)).divide(
				new BigDecimal(12), 0, RoundingMode.HALF_UP);
	}

	/**
	 * method to Calculate Gross Income
	 * 
	 * @param income
	 * @return
	 */
	public static BigDecimal grossIncome(BigDecimal income) {
		return income.divide(new BigDecimal(12), 0, RoundingMode.HALF_UP);
	}

	/**
	 * method to Calculate net Income
	 * 
	 * @param grossIncome
	 * @param incomeTax
	 * @return
	 */
	public static BigDecimal netIncome(BigDecimal grossIncome,
			BigDecimal incomeTax) {
		return grossIncome.subtract(incomeTax);
	}

	/**
	 * Method to calculate super Annuation
	 * 
	 * @param grossIncome
	 * @param superRate
	 * @return
	 */
	public static BigDecimal superAnn(BigDecimal grossIncome,
			BigDecimal superRate) {
		return grossIncome.multiply(superRate).divide(new BigDecimal(100), 0,
				RoundingMode.HALF_UP);
	}
}
