package com.technicalyorker.payroll.reader;

import com.technicalyorker.payroll.domain.SalaryEntry;

/**
 * Salary Entry Reader Contract
 * 
 * @author achuth
 *
 */
public interface SalaryEntryReader {
	public SalaryEntry readNext();
}
