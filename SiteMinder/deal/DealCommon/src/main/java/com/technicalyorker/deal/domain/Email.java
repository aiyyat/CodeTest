package com.technicalyorker.deal.domain;

import java.util.ArrayList;
import java.util.List;

import com.technicalyorker.deal.constants.Constants;

public class Email {
	public Email() {
	}

	private String from;
	private String to;
	private static final String adminEmail = Constants.ADMIN_EMAIL;
	private List<String> cc = new ArrayList<>();
	private List<String> bcc = new ArrayList<>();
	private String subject;
	private String message;

	public String getAdminEmail() {
		return adminEmail;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getCc() {
		return cc;
	}

	private void addCc(String cc) {
		this.cc.add(cc);
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public List<String> getBcc() {
		return bcc;
	}

	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Email [from=" + from + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", subject=" + subject
				+ ", message=" + message + "]";
	}
}
