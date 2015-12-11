package org.pinguin.service.task;

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

		Set<Task> tasks = new HashSet<>();

		List<AllocatedTask> allocated = allocator.alloc(tasks);

		Assert.assertEquals(6, allocated.size());
	}

}
