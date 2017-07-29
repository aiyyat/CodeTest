package com.technicalyorker.deal.emailservice.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.technicalyorker.deal.domain.Email;

/**
 * Builder generates the json body in a format required by SendGrid
 * 
 * @author achuth
 *
 */
public class SendGridEmailBuilder {
	SendGridEmail sge = new SendGridEmail();

	public SendGridEmailBuilder email(Email email) {
		Personalization personalization = new Personalization();
		personalization.addTo(new EmailId(email.getTo()));
		personalization.addCc(new EmailId(email.getFrom()));
		personalization.addBcc(new EmailId(email.getAdminEmail()));
		sge.addPersonalization(personalization);
		sge.setFrom(new EmailId(email.getFrom()));
		sge.setSubject(email.getSubject());
		Content content = new Content();
		content.setType("text/plain");
		content.setValue(email.getMessage() + "\n sent using SendGrid");
		sge.addContent(content);
		return this;
	}

	public SendGridEmail build() {
		return sge;
	}

	@JsonPropertyOrder({ "personalizations", "from", "subject", "content" })
	public class SendGridEmail implements Serializable {
		private static final long serialVersionUID = 2542774744370601593L;
		@JsonProperty("personalizations")
		private List<Personalization> personalization = new ArrayList<>();
		private EmailId from;
		private String subject;
		private List<Content> content = new ArrayList<>();

		public List<Personalization> getPersonalization() {
			return personalization;
		}

		public void setPersonalization(List<Personalization> personalization) {
			this.personalization = personalization;
		}

		public void addPersonalization(Personalization personalization) {
			this.personalization.add(personalization);
		}

		public EmailId getFrom() {
			return from;
		}

		public void setFrom(EmailId from) {
			this.from = from;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public List<Content> getContent() {
			return content;
		}

		public void setContent(List<Content> content) {
			this.content = content;
		}

		public void addContent(Content content) {
			this.content.add(content);
		}

	}

	class Personalization {
		private List<EmailId> to = new ArrayList<>();
		private List<EmailId> cc = new ArrayList<>();
		private List<EmailId> bcc = new ArrayList<>();

		public Personalization() {
		}

		public List<EmailId> getTo() {
			return to;
		}

		public void setTo(List<EmailId> to) {
			this.to = to;
		}

		public void addTo(EmailId to) {
			this.to.add(to);
		}

		public List<EmailId> getCc() {
			return cc;
		}

		public void setCc(List<EmailId> cc) {
			this.cc = cc;
		}

		public void addCc(EmailId cc) {
			this.cc.add(cc);
		}

		public List<EmailId> getBcc() {
			return bcc;
		}

		public void setBcc(List<EmailId> bcc) {
			this.bcc = bcc;
		}

		public void addBcc(EmailId bcc) {
			this.bcc.add(bcc);
		}
	}

	class EmailId {
		@JsonProperty("email")
		private String email;

		public EmailId(String to) {
			this.email = to;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}

	class Content {
		private String type;
		private String value;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

	}
}
