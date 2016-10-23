package com.crossover.trial.journals.repository;

import static com.crossover.trial.journals.model.EmailStatus.NOT_SENT;
import static com.crossover.trial.journals.utility.TemporalUtil.format;
import static java.util.Calendar.DAY_OF_MONTH;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crossover.trial.journals.Application;
import com.crossover.trial.journals.model.EmailStatus;
import com.crossover.trial.journals.model.Notification;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class NotificationRepositoryTest {
	@Autowired
	NotificationRepository n;

	@Test
	public void test() throws Exception {
		Calendar earlier = Calendar.getInstance();
		Calendar later = Calendar.getInstance();
		later.add(10, DAY_OF_MONTH);

		Notification ns = new Notification();
		ns.setLastTrigger(later.getTime());
		ns.setEmailSent(NOT_SENT);
		n.save(ns);

		ns = new Notification();
		ns.setLastTrigger(earlier.getTime());
		ns.setEmailSent(NOT_SENT);
		n.save(ns);

		TestCase.assertEquals(format(later.getTime()), format(n.findTopByOrderByLastTriggerDesc().getLastTrigger()));
	}
}
