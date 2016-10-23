package com.crossover.trial.journals.builders;

import java.io.InputStream;

public class MailMessage {
	private String to;
	private String body;
	private String subject;
	private byte[] attachment;

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

	public MailMessageBuilder builder() {
		return new MailMessageBuilder();
	}

	class MailMessageBuilder {
		MailMessage message;

		MailMessageBuilder body(String body) {
			message.setBody(body);
			return this;
		}

		MailMessageBuilder subject(String subject) {
			message.setSubject(subject);
			return this;
		}

		MailMessageBuilder to(String to) {
			message.setTo(to);
			return this;
		}

		MailMessageBuilder attachment(byte[] attachment) {
			message.setAttachment(attachment);
			return this;
		}

		public MailMessage build() {
			return message;
		}
	}
}
