package com.crossover.trial.journals.service;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crossover.trial.journals.Application;
import com.crossover.trial.journals.model.User;
import com.crossover.trial.journals.repository.UserRepository;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository uRepository;

	@Test
	public void testUserByLoginName() {
		User u = uRepository.findOne(1L);
		TestCase.assertEquals(u.getId(), userService.getUserByLoginName(u.getLoginName()).get().getId());
	}

	@Test(expected = NoSuchElementException.class)
	public void testUserByNonExistantLoginName() {
		userService.getUserByLoginName("non existant name");
	}

	@Test
	public void testSubscribe() {
		final AtomicBoolean b = new AtomicBoolean(false);
		User u = uRepository.findOne(1L);
		userService.subscribe(u, 1L);
		userService.findById(1L).getSubscriptions().forEach(s -> {
			if (s.getUser().getId() == u.getId()) {
				b.set(true);
			}
		});
		TestCase.assertTrue(b.get());
	}

	@Test
	public void testFindById() {
		User u = uRepository.findOne(1L);
		TestCase.assertEquals(userService.findById(u.getId()).getId(), u.getId());
	}
}
