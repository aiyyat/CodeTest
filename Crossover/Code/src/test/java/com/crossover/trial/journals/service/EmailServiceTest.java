package com.crossover.trial.journals.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crossover.trial.journals.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class EmailServiceTest {

	@Before
	public void setup() throws Exception {
	}

	@Autowired
	EmailService emailService;

	@Test
	public void sendEmail() throws Exception {
		emailService.sendMessage("Hello World!");
	}
}
