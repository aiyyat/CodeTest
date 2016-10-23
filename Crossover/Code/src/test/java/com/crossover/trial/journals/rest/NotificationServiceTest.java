package com.crossover.trial.journals.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.crossover.trial.journals.model.Category;
import com.crossover.trial.journals.model.Journal;
import com.crossover.trial.journals.model.Publisher;
import com.crossover.trial.journals.model.Role;
import com.crossover.trial.journals.model.User;

public class NotificationServiceTest {

	@Before
	public void setup() throws Exception {
	}

	@Test
	public void getCategories() throws Exception {
		List<User> us = new ArrayList<>();
		User u = new User();
		u.setLoginName("achuth");
		u.setPwd("password");
		u.setRole(Role.PUBLISHER);
		u.setEmailId("technicalyorker@gmail.com");
		us.add(u);

		Category c = new Category();
		c.setName("Immunology");

		Publisher p = new Publisher();
		List<Journal> js = new ArrayList<>();
		Journal j = new Journal();
		j.setName("Journal");
		j.setPublisher(p);
		j.setCategory(c);
		js.add(j);

	}
}
