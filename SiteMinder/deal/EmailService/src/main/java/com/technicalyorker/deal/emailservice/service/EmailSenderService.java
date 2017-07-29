package com.technicalyorker.deal.emailservice.service;

import com.technicalyorker.deal.domain.Email;
import com.technicalyorker.deal.domain.SendStatus;

public interface EmailSenderService {
	public SendStatus send(Email email);
}
