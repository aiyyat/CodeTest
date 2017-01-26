package com.technicalyorker.dbs.gorden.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technicalyorker.dbs.gorden.algorithm.EateryUtility;
import com.technicalyorker.dbs.gorden.exception.InvalidInputSourceException;

/**
 * Service Implementation of Eatery Utility which is accepts file Input in the
 * form of String or java.io.File
 * 
 * @author achuth
 *
 */
@Service
public class EateryService {
	@Autowired
	EateryUtility eateryUtility;

	public Integer performWithRefPath(String refFilePath) {
		try {
			return performWithAbsolutePath(getClass().getClassLoader().getResource(refFilePath).getFile());
		} catch (NullPointerException e) {
			throw new InvalidInputSourceException(e);
		}
	}

	public Integer performWithAbsolutePath(String absoluteFilePath) {
		return perform(new File(absoluteFilePath));
	}

	public Integer perform(File file) {
		try (InputStream stream = new FileInputStream(file)) {
			return eateryUtility.perform(stream);
		} catch (IOException e) {
			throw new InvalidInputSourceException(e);
		}
	}
}
