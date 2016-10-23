package com.crossover.trial.journals.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crossover.trial.journals.Application;
import com.crossover.trial.journals.model.Category;
import com.crossover.trial.journals.model.Role;
import com.crossover.trial.journals.model.Subscription;
import com.crossover.trial.journals.model.User;
import com.crossover.trial.journals.repository.CategoryRepository;
import com.crossover.trial.journals.repository.SubscriptionRepository;
import com.crossover.trial.journals.repository.UserRepository;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserServiceTest {
	private static final String NAME = "name";
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository uRepository;
	@Autowired
	private CategoryRepository cRepository;
	@Autowired
	private SubscriptionRepository sRepository;
	User u = new User();
	Category c1 = new Category();
	Category c2 = new Category();
	Subscription s1 = new Subscription();
	Subscription s2 = new Subscription();
	List<Subscription> ss = new ArrayList<>();

	@Before
	public void setup() {
		u.setEmailId("test@gmail.com");
		u.setLoginName(NAME);
		u.setPwd("secret");
		u.setRole(Role.PUBLISHER);
		u.setEnabled(Boolean.TRUE);
		uRepository.save(u);

		c1.setName("Category 1");
		cRepository.save(c1);

		c2.setName("Category 2");
		cRepository.save(c2);

		s1.setCategory(c1);
		s1.setUser(u);
		sRepository.save(s1);

		s2.setCategory(c2);
		s2.setUser(u);
		sRepository.save(s2);

		ss.add(s1);
		ss.add(s2);
		u.setSubscriptions(ss);
		uRepository.save(u);
	}

	@After
	public void remove() {
		sRepository.delete(s1);
		sRepository.delete(s2);
		uRepository.delete(u);
		cRepository.delete(c1);
		cRepository.delete(c2);
	}

	@Test
	public void testUserByLoginName() {
		TestCase.assertEquals(u.getId(), uRepository.findByLoginName(NAME).getId());
	}

	@Test
	public void testUserByNonExistantLoginName() {
		TestCase.assertNull(uRepository.findByLoginName("non existant name"));
	}

	@Test
	public void testsubscribe() {
		sRepository.findByCategory(c1).forEach(s -> TestCase.assertEquals(s.getCategory().getId(), c1.getId()));
		sRepository.findByCategory(c2).forEach(s -> TestCase.assertEquals(s.getCategory().getId(), c2.getId()));
	}

	@Test
	public void testFindById() {
		TestCase.assertEquals(uRepository.findOne(u.getId()).getId(), u.getId());
	}
}
