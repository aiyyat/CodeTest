package com.crossover.trial.journals.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.crossover.trial.journals.Application;
import com.crossover.trial.journals.model.Category;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class CategoryRepositoryTest {

	@Autowired
	CategoryRepository repository;

	@Test
	public void testCRUD() throws Exception {
		Category n = new Category();
		n.setName("Test Category");
		repository.save(n);

		n.setName("Test 1 Category");
		repository.save(n);
		TestCase.assertEquals("Test 1 Category", repository.findOne(n.getId()).getName());

		TestCase.assertNotNull(n.getId());
		Category c1 = new Category();
		c1.setId(n.getId());
		repository.delete(c1);
		TestCase.assertNull(repository.findOne(n.getId()));
	}
}
