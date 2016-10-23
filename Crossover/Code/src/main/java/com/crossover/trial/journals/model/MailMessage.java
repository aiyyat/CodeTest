package com.crossover.trial.journals.model;

/**
 * Encapsulates the attributes of an email message.
 * 
 * Immutable Class - create instance using the inherent builder.
 * 
 * @author achuth
 *
 */
public class MailMessage {
	private String to;
	private String body;
	private String subject;
	private byte[] attachment;

	private MailMessage() {
	}

	public String getTo() {
		return to;
	}

	public String getBody() {
		return body;
	}

	public String getSubject() {
		return subject;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	private void setBody(String body) {
		this.body = body;
	}

	private void setTo(String to) {
		this.to = to;
	}

	private void setSubject(String subject) {
		this.subject = subject;
	}

	private void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public static MailMessageBuilder builder() {
		return new MailMessageBuilder();
	}

	public static class MailMessageBuilder {
		private MailMessage message = new MailMessage();

		public MailMessageBuilder body(String body) {
			message.setBody(body);
			return this;
		}

		public MailMessageBuilder subject(String subject) {
			message.setSubject(subject);
			return this;
		}

		public MailMessageBuilder to(String to) {
			message.setTo(to);
			return this;
		}

		public MailMessageBuilder attachment(byte[] attachment) {
			message.setAttachment(attachment);
			return this;
		}

		public MailMessage build() {
			return message;
		}
	}
}
