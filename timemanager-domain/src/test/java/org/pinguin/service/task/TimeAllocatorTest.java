package org.pinguin.service.task;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.pinguin.domain.task.Task;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class TimeAllocatorTest {

	private Injector injector;

	@Before
	public void before() {
		if (injector == null) {
			injector = Guice.createInjector();
		}
	}

	@Test
	public void testAllocation() {

		TimeAllocator allocator = injector.getInstance(TimeAllocator.class);

		Set<Task> tasks = new HashSet<>(
				Arrays.asList(new Task("task1", 2L, 60L), new Task("task2", 3L, 90L), new Task("task3", 1L, 45L)));

		List<AllocatedTask> allocated = allocator.alloc(tasks);

		Assert.assertEquals(3, allocated.size());
		Assert.assertEquals("task3", allocated.get(0).getName());
		Assert.assertEquals("task1", allocated.get(1).getName());
		Assert.assertEquals("task2", allocated.get(2).getName());
	}

}
