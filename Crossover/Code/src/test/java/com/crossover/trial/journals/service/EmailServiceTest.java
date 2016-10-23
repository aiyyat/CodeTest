package com.crossover.trial.journals.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crossover.trial.journals.Application;
import com.crossover.trial.journals.exception.IllegalMailMessageException;
import com.crossover.trial.journals.model.MailMessage;
import com.crossover.trial.journals.service.helpers.EmailService;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class EmailServiceTest {
	@Autowired
	EmailService notificationService;

	@Test(expected = IllegalMailMessageException.class)
	public void testEmailServiceCall() {
		MailMessage m = MailMessage.builder().body("test").subject("subject").build();
		notificationService.sendMessage(m);
		TestCase.assertEquals(m.getBody(), "test");
	}

	@Test
	public void testBadEmailServiceCall() {
		MailMessage m = MailMessage.builder().to("test@test.com").body("test").subject("subject").build();
		notificationService.sendMessage(m);
		TestCase.assertEquals(m.getBody(), "test");
	}
}
