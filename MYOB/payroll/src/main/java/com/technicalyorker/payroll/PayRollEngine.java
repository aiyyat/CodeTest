package com.technicalyorker.payroll;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.technicalyorker.payroll.domain.PaySlip;
import com.technicalyorker.payroll.domain.SalaryEntry;
import com.technicalyorker.payroll.exception.OuputSourceWriteException;
import com.technicalyorker.payroll.reader.CSVSalaryEntryReader;
import com.technicalyorker.payroll.util.PayRollUtil;

/**
 * Workflow to calculate the Pay Roll Engine
 * 
 * @author achuth
 *
 */
public class PayRollEngine {
	/**
	 * Read from source.
	 * 
	 * @param iReader
	 * @return
	 */
	private List<SalaryEntry> doRead(Reader iReader) {
		List<SalaryEntry> output = new ArrayList<>();
		try (CSVSalaryEntryReader reader = new CSVSalaryEntryReader(iReader)) {
			SalaryEntry entry = null;
			while (null != (entry = reader.readNext())) {
				output.add(entry);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return output;
	}

	/**
	 * Convert salary entries to payslip entries
	 * 
	 * @param entries
	 * @return
	 */
	private List<PaySlip> doProcess(List<SalaryEntry> entries) {
		final List<PaySlip> paySlips = new ArrayList<>();
		entries.forEach(entry -> {
			BigDecimal annualSalary = entry.getAnnualSalary();
			BigDecimal grossIncome = PayRollUtil.grossIncome(annualSalary);
			BigDecimal incomeTax = PayRollUtil.incomeTax(annualSalary);
			PaySlip paySlip = new PaySlip();
			paySlip.setName(entry.getFirstName() + " " + entry.getLastName());
			paySlip.setPayPeriod(entry.getPaymentStartDate());
			paySlip.setIncomeTax(incomeTax.intValue());
			paySlip.setSuperAnn(PayRollUtil.superAnn(grossIncome,
					entry.getSuperRate()).intValue());
			paySlip.setGross(grossIncome.intValue());
			paySlip.setNetIncome(PayRollUtil.netIncome(grossIncome, incomeTax)
					.intValue());
			paySlips.add(paySlip);
		});
		return paySlips;
	}

	/**
	 * Writing the paySlips to a writer
	 * 
	 * @param paySlips
	 * @param writer
	 */
	private void doWrite(List<PaySlip> paySlips, Writer writer) {
		try {
			CSVWriter csvWriter = new CSVWriter(writer, ',', '\0');
			List<String[]> records = new ArrayList<String[]>();
			records.add(new String[] { "Name", "Gross", "Income Tax",
					"Net Income", "Super" });
			paySlips.forEach(record -> {
				records.add(new String[] { record.getName(),
						record.getGross().toString(),
						record.getIncomeTax().toString(),
						record.getNetIncome().toString(),
						record.getSuperAnn().toString() });
			});
			csvWriter.writeAll(records);
			csvWriter.close();
		} catch (Exception e) {
			throw new OuputSourceWriteException(e);
		}
	}

	/**
	 * Triggers a PayRoll Workflow
	 * 
	 * @param reader
	 * @param writer
	 */
	public void processPayRolls(Reader reader, Writer writer) {
		List<SalaryEntry> entries = doRead(reader);
		List<PaySlip> paySlips = doProcess(entries);
		doWrite(paySlips, writer);
	}
}
