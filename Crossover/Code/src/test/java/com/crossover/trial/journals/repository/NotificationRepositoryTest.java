package com.crossover.trial.journals.repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crossover.trial.journals.Application;
import com.crossover.trial.journals.model.Notification;
import com.crossover.trial.journals.repository.NotificationRepository;

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
		later.add(10, Calendar.DAY_OF_MONTH);

		Notification ns = new Notification();
		ns.setLastTrigger(later.getTime());
		n.save(ns);

		ns = new Notification();
		ns.setLastTrigger(earlier.getTime());
		n.save(ns);
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		TestCase.assertEquals(s.format(later.getTime()),
				s.format(n.findTopByOrderByLastTriggerDesc().getLastTrigger()));
	}
}
