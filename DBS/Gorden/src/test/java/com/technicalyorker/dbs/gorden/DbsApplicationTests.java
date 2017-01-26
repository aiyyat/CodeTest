package com.technicalyorker.dbs.gorden;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.technicalyorker.dbs.gorden.exception.InvalidInputSourceException;
import com.technicalyorker.dbs.gorden.service.EateryService;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbsApplicationTests {
	@Autowired
	EateryService service;
	static String absolutePath = null;

	@BeforeClass
	public static void init() {
		absolutePath = DbsApplicationTests.class.getClassLoader().getResource("input.txt").getFile();
	}

	@Test
	public void testReadRefFilePath() {
		TestCase.assertEquals(new Integer(2493893), service.performWithRefPath("input.txt"));
	}

	@Test
	public void testReadFile() {
		TestCase.assertEquals(new Integer(2493893), service.perform(new File(absolutePath)));
	}

	@Test
	public void testReadAbsoluteFilePath() {
		TestCase.assertEquals(new Integer(2493893), service.performWithAbsolutePath(absolutePath));
	}

	@Test(expected = InvalidInputSourceException.class)
	public void testAbsoluteFileDoesnotExist() {
		service.performWithAbsolutePath("fileThatDoesNotExist.txt");
	}

	@Test(expected = InvalidInputSourceException.class)
	public void testRefFilePathNotExist() {
		service.performWithRefPath("fileThatDoesNotExist.txt");
	}

	@Test(expected = InvalidInputSourceException.class)
	public void testAbsoluteFilePathNotExist() {
		service.performWithAbsolutePath("fileThatDoesNotExist.txt");
	}

}
