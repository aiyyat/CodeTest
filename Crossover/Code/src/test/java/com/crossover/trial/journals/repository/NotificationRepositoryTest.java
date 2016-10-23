package com.crossover.trial.journals.repository;

import static com.crossover.trial.journals.model.NotificationStatus.BEGIN_POLLING;
import static com.crossover.trial.journals.model.NotificationStatus.SENT;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crossover.trial.journals.Application;
import com.crossover.trial.journals.model.Notification;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class NotificationRepositoryTest {

	@Autowired
	NotificationRepository repository;

	@Test
	public void testCRUD() throws Exception {
		Notification n = new Notification();
		n.setLastTrigger(new Date());
		n.setNotificationSent(BEGIN_POLLING);
		Long id = n.getId();
		TestCase.assertNull(id);
		repository.save(n);

		n.setNotificationSent(SENT);
		repository.save(n);
		TestCase.assertEquals(SENT, repository.findOne(n.getId()).getNotificationStatus());

		TestCase.assertNotNull(n.getId());
		repository.delete(n);
		TestCase.assertFalse(repository.exists(n.getId()));
	}
}
