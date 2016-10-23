package com.crossover.trial.journals.service;

import java.util.Calendar;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crossover.trial.journals.Application;
import com.crossover.trial.journals.model.Category;
import com.crossover.trial.journals.model.Journal;
import com.crossover.trial.journals.model.Notification;
import com.crossover.trial.journals.model.Publisher;
import com.crossover.trial.journals.model.Role;
import com.crossover.trial.journals.model.Subscription;
import com.crossover.trial.journals.model.User;
import com.crossover.trial.journals.repository.CategoryRepository;
import com.crossover.trial.journals.repository.JournalRepository;
import com.crossover.trial.journals.repository.PublisherRepository;
import com.crossover.trial.journals.repository.SubscriptionRepository;
import com.crossover.trial.journals.repository.UserRepository;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class NotificationServiceTest {
	@Autowired
	NotificationService notificationService;
	@Autowired
	JournalRepository jRepo;
	@Autowired
	CategoryRepository cRepo;
	@Autowired
	UserRepository uRepo;
	@Autowired
	PublisherRepository pRepo;
	@Autowired
	SubscriptionRepository sRepo;
	Category c;
	Journal j = new Journal();
	User u = new User();
	Subscription s = new Subscription();
	Publisher p = new Publisher();
	Notification n = new Notification();

	@Before
	public void setup() {
		c = cRepo.getOne(1L);
		u.setEmailId("test@gmail.com");
		u.setEnabled(true);
		u.setLoginName("user001");
		u.setPwd("secret");
		u.setRole(Role.PUBLISHER);
		uRepo.save(u);
		s.setDate(Calendar.getInstance().getTime());
		s.setUser(u);
		s.setCategory(c);
		j.setName("New Journal");
		j.setUuid(UUID.randomUUID().toString());
		p.setName("Test Publisher1");
		p.setUser(u);
		pRepo.save(p);
		j.setPublisher(p);
		j.setCategory(c);
		jRepo.save(j);
		Calendar t = Calendar.getInstance();
		t.add(Calendar.SECOND, -10);
		n.setLastTrigger(t.getTime());
	}

	@After
	public void tearDown() {
		Subscription s2 = new Subscription();
		s2.setId(s.getId());
		sRepo.delete(s);
		jRepo.delete(j);
		pRepo.delete(p);
		uRepo.delete(u);

	}

	@Test
	public void testPolling() throws InterruptedException {
		notificationService.poll(-5L, n).forEach(note -> TestCase.assertTrue(note.getBody().indexOf(p.getName()) > -1));
	}

	@Test
	public void testSubscription() throws InterruptedException {
		notificationService.fetchMailMessagesForSubscribers(j)
				.forEach(note -> TestCase.assertTrue(note.getBody().indexOf(p.getName()) > -1));
	}
}
