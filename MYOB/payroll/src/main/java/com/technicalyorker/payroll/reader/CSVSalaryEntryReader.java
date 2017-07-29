package com.technicalyorker.payroll.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.technicalyorker.payroll.domain.SalaryEntry;
import com.technicalyorker.payroll.exception.InputSourceReadException;
import com.technicalyorker.payroll.util.PayRollUtil;

/**
 * CSV Implementation of Salary Entry Reader.
 * 
 * @author achuth
 *
 */
public class CSVSalaryEntryReader extends BufferedReader implements
		SalaryEntryReader {

	public CSVSalaryEntryReader(Reader reader) {
		super(reader);
		try {
			super.readLine();
		} catch (IOException e) {
			throw new InputSourceReadException(e);
		}
	}

	public SalaryEntry readNext() {
		try {
			String line = super.readLine();
			if (null != line) {
				return PayRollUtil.parse(line);
			}
		} catch (Exception e) {
			throw new InputSourceReadException(e);
		}
		return null;
	}

}
