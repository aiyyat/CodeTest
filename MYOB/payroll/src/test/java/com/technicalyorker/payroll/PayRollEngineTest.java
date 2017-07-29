package com.technicalyorker.payroll;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import junit.framework.TestCase;

import org.junit.Test;

import com.technicalyorker.payroll.exception.InputSourceReadException;

public class PayRollEngineTest {

	@Test
	public void optimisticScenario() {
		Reader reader = new StringReader(
				"Input (first name, last name, annual salary, super rate (%), payment start date):\nDavid,Rudd,60050,9%,01 March – 31 March\nRyan,Chen,120000,10%,01 March – 31 March");
		Writer writer = new StringWriter();
		new PayRollEngine().processPayRolls(reader, writer);
		TestCase.assertEquals(
				"Name,Gross,Income Tax,Net Income,Super\nDavid Rudd,5004,922,4082,450\nRyan Chen,10000,2696,7304,1000\n",
				writer.toString());
	}

	@Test
	public void noValueScenario() {
		Reader reader = new StringReader(
				"Input (first name, last name, annual salary, super rate (%), payment start date)");
		Writer writer = new StringWriter();
		new PayRollEngine().processPayRolls(reader, writer);
		TestCase.assertEquals("Name,Gross,Income Tax,Net Income,Super\n",
				writer.toString());
	}

	@Test(expected = InputSourceReadException.class)
	public void pessimisticScenario() {
		Reader reader = new StringReader(
				"Input (first name, last name, annual salary, super rate (%), payment start date):\nDavid,Rudd,60050test,9%,01 March – 31 March\nRyan,Chen,120000test,10%,01 March – 31 March");
		Writer writer = new StringWriter();
		new PayRollEngine().processPayRolls(reader, writer);
	}
}
