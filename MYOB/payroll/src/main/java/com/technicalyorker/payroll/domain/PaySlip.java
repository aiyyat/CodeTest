package com.technicalyorker.payroll.domain;

/**
 * This class represents a PaySlip Entry.
 * 
 * @author achuth
 *
 */
public class PaySlip {
	private String name;
	private String payPeriod;
	private Integer gross;
	private Integer incomeTax;
	private Integer netIncome;
	private Integer superAnn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}

	public Integer getGross() {
		return gross;
	}

	public void setGross(Integer gross) {
		this.gross = gross;
	}

	public Integer getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(Integer incomeTax) {
		this.incomeTax = incomeTax;
	}

	public Integer getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(Integer netIncome) {
		this.netIncome = netIncome;
	}

	public Integer getSuperAnn() {
		return superAnn;
	}

	public void setSuperAnn(Integer superAnn) {
		this.superAnn = superAnn;
	}

	@Override
	public String toString() {
		return "Payslip [name=" + name + ", payPeriod=" + payPeriod
				+ ", gross=" + gross + ", incomeTax=" + incomeTax
				+ ", netIncome=" + netIncome + ", superAnn=" + superAnn + "]";
	}

	public String asCSV() {
		return name + "," + payPeriod + "," + gross + "," + incomeTax + ","
				+ netIncome + "," + superAnn;
	}
}
