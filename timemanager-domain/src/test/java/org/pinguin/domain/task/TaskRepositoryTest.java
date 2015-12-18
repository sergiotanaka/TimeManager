package org.pinguin.domain.task;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pinguin.domain.common.Repository.Param;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

public class TaskRepositoryTest {

	private Injector injector;

	@Before
	public void before() {

		if (injector == null) {
			injector = Guice.createInjector(new JpaPersistModule("unitTest"));
			injector.getInstance(PersistService.class).start();
		}

		TaskRepository repo = injector.getInstance(TaskRepository.class);
		List<Task> all = repo.retrieveAll();
		for (Task item : all) {
			repo.delete(item);
		}
	}

	@Test
	public void testPersistence() {

		TaskRepository repo = injector.getInstance(TaskRepository.class);

		Task task = new Task("task1", "description", Category.CIRCUNSTANCIAL, 10L);
		repo.create(task);

		List<Task> all = repo.retrieveAll();

		Assert.assertEquals(1, all.size());
	}

	@Test
	public void testRetrieveByCriteria() {

		TaskRepository repo = injector.getInstance(TaskRepository.class);

		Task task1 = new Task("task1", "description", Category.CIRCUNSTANCIAL, 10L);
		Task task2 = new Task("task2", "description", Category.IMPORTANT, 5L);
		Task task3 = new Task("task3", "description", Category.URGENT, 2L);
		repo.create(task1);
		repo.create(task2);
		repo.create(task3);

		List<Task> all = repo.retrieveAll();
		Assert.assertEquals(3, all.size());

		List<Task> retrieved = repo.retrieveByCondition("name = 'task2'");

		Assert.assertEquals(1, retrieved.size());
		Assert.assertEquals("task2", retrieved.get(0).getName());

		List<Task> retrieved2 = repo.retrieveByCondition("name = :name and duration = :duration",
				new Param("name", "task3"), new Param("duration", 2L));

		Assert.assertEquals(1, retrieved2.size());
		Assert.assertEquals("task3", retrieved2.get(0).getName());

	}

}
