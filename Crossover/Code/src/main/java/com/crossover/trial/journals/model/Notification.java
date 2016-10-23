package com.crossover.trial.journals.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "notification")
public class Notification {

	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_trigger")
	private Date lastTrigger;
	private String message;
	@Column(name = "email_status")
	@Enumerated(EnumType.STRING)
	private EmailStatus emailSent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLastTrigger() {
		return lastTrigger;
	}

	public void setLastTrigger(Date lastTrigger) {
		this.lastTrigger = lastTrigger;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EmailStatus getEmailSent() {
		return emailSent;
	}

	public void setEmailSent(EmailStatus emailSent) {
		this.emailSent = emailSent;
	}
}
