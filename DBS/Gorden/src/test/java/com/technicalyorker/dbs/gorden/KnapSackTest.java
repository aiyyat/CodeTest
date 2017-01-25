package com.technicalyorker.dbs.gorden;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

import com.technicalyorker.dbs.gorden.algorithm.EateryUtility;
import com.technicalyorker.dbs.gorden.exception.InvalidInputFormatException;

import junit.framework.TestCase;

public class KnapSackTest {
	@Test
	public void testCase1() {
		String exampleString = "50 3\n60 10\n100 20\n120 30";
		try (InputStream stream = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8))) {
			TestCase.assertEquals(new Integer(220), new EateryUtility().perform(stream));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadFile() {
		try (InputStream stream = new FileInputStream(getClass().getClassLoader().getResource("input.txt").getFile())) {
			TestCase.assertEquals(new Integer(2493893), new EateryUtility().perform(stream));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = InvalidInputFormatException.class)
	public void negativeCase() {
		String exampleString = "50 4\n60 10\n100 20\n120 30";
		try (InputStream stream = new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8))) {
			new EateryUtility().perform(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
