package com.technicalyorker.dbs.gorden.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technicalyorker.dbs.gorden.algorithm.EateryUtility;
import com.technicalyorker.dbs.gorden.exception.InvalidInputSourceException;

@Service
public class EateryService {
	@Autowired
	EateryUtility eateryUtility;

	public int perform(String fileName) {
		try (InputStream is = new FileInputStream(new File(fileName))) {
			return eateryUtility.perform(is);
		} catch (IOException e) {
			throw new InvalidInputSourceException(e);
		}
	}
}
