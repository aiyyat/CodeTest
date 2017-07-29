package com.technicalyorker.payroll.reader;

import java.io.IOException;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.junit.Test;

import com.technicalyorker.payroll.domain.SalaryEntry;

public class CSVSalaryEntryReaderTest {
	@Test
	public void optimisticScenario() {
		try (CSVSalaryEntryReader reader = new CSVSalaryEntryReader(
				new InputStreamReader(this.getClass().getClassLoader()
						.getResourceAsStream("problem_scenario.txt")))) {
			SalaryEntry expected = SalaryEntry.builder().firstName("David")
					.lastName("Rudd").annualSalary("60050").superRate("9")
					.paymentStartDate("01 March – 31 March").build();
			TestCase.assertEquals(expected, reader.readNext());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void optimisticScenario2() {
		try (CSVSalaryEntryReader reader = new CSVSalaryEntryReader(
				new InputStreamReader(this.getClass().getClassLoader()
						.getResourceAsStream("problem_scenario.txt")))) {
			SalaryEntry expected = SalaryEntry.builder().firstName("Ryan")
					.lastName("Chen").annualSalary("120000").superRate("10")
					.paymentStartDate("01 March – 31 March").build();
			reader.readNext();
			TestCase.assertEquals(expected, reader.readNext());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void pessimisticScenario() {
		try (CSVSalaryEntryReader reader = new CSVSalaryEntryReader(
				new InputStreamReader(this.getClass().getClassLoader()
						.getResourceAsStream("no_data_scenario.txt")))) {
			TestCase.assertEquals(null, reader.readNext());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
