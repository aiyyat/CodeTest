package com.technicalyorker.deal.domain;

public class SendStatus {
	private Status status;

	public SendStatus() {
	}

	public SendStatus(Status status) {
		super();
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SendStatus [status=" + status + "]";
	}
}
