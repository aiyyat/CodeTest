package com.technicalyorker.deal.dealservice.vo;

import java.util.ArrayList;

public class QuoteRequest {
	private String username;
	private String emailId;
	private String purpose;
	private ArrayList<Long> productIds;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public ArrayList<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(ArrayList<Long> productIds) {
		this.productIds = productIds;
	}

	@Override
	public String toString() {
		return "Inputs [username=" + username + ", emailId=" + emailId + ", purpose=" + purpose + ", productIds="
				+ productIds + "]";
	}
}