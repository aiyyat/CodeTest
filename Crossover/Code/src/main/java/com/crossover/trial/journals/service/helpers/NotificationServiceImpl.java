package com.crossover.trial.journals.service.helpers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crossover.trial.journals.builders.MailMessage;

@Service
public class NotificationServiceImpl implements NotificationService {
	private final static Logger log = Logger.getLogger(NotificationServiceImpl.class);
	@Autowired
	EmailService emailService;

	@Override
	public void emailNotification(MailMessage message) {
		emailService.sendMessage(message);
	}
}
