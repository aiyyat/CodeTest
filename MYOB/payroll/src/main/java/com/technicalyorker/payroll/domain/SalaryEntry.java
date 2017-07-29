package com.technicalyorker.payroll.domain;

import java.math.BigDecimal;

/**
 * Represents a Salary Entry.
 * 
 * @author achuth
 *
 */
public final class SalaryEntry {
	private String firstName;
	private String lastName;
	private BigDecimal annualSalary;
	private BigDecimal superRate;
	private String paymentStartDate;

	private SalaryEntry() {

	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public BigDecimal getAnnualSalary() {
		return annualSalary;
	}

	public BigDecimal getSuperRate() {
		return superRate;
	}

	public String getPaymentStartDate() {
		return paymentStartDate;
	}

	private void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private void setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
	}

	private void setSuperRate(BigDecimal superRate) {
		this.superRate = superRate;
	}

	private void setPaymentStartDate(String paymentStartDate) {
		this.paymentStartDate = paymentStartDate;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName
				+ ", annualSalary=" + annualSalary + ", superRate=" + superRate
				+ ", paymentStartDate=" + paymentStartDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((annualSalary == null) ? 0 : annualSalary.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime
				* result
				+ ((paymentStartDate == null) ? 0 : paymentStartDate.hashCode());
		result = prime * result
				+ ((superRate == null) ? 0 : superRate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalaryEntry other = (SalaryEntry) obj;
		if (annualSalary == null) {
			if (other.annualSalary != null)
				return false;
		} else if (!annualSalary.equals(other.annualSalary))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (paymentStartDate == null) {
			if (other.paymentStartDate != null)
				return false;
		} else if (!paymentStartDate.equals(other.paymentStartDate))
			return false;
		if (superRate == null) {
			if (other.superRate != null)
				return false;
		} else if (!superRate.equals(other.superRate))
			return false;
		return true;
	}

	public static final SalaryEntryBuilder builder() {
		return new SalaryEntry.SalaryEntryBuilder();
	}

	public static class SalaryEntryBuilder {
		SalaryEntry employee;

		public SalaryEntryBuilder() {
			super();
			this.employee = new SalaryEntry();
		}

		public SalaryEntryBuilder firstName(String firstName) {
			employee.setFirstName(firstName);
			return this;
		}

		public SalaryEntryBuilder lastName(String lastName) {
			employee.setLastName(lastName);
			return this;
		}

		public SalaryEntryBuilder annualSalary(BigDecimal annualSalary) {
			employee.setAnnualSalary(annualSalary);
			return this;
		}

		public SalaryEntryBuilder annualSalary(String annualSalary) {
			return annualSalary(new BigDecimal(annualSalary));
		}

		public SalaryEntryBuilder superRate(BigDecimal superRate) {
			employee.setSuperRate(superRate);
			return this;
		}

		public SalaryEntryBuilder superRate(String superRate) {
			superRate = superRate.replaceAll("%", "");
			return superRate(new BigDecimal(superRate));
		}

		public SalaryEntryBuilder paymentStartDate(String paymentStartDate) {
			employee.setPaymentStartDate(paymentStartDate);
			return this;
		}

		public SalaryEntry build() {
			return this.employee;
		}
	}
}
