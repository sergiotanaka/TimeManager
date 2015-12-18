package org.pinguin.service.task;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	private Calendar calendar = GregorianCalendar.getInstance();

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

		// Setando 01/01/2015 as 9:00
		List<AllocatedTask> allocated = allocator.alloc(tasks, getTimestamp(2015, 01, 01, 9, 0, 0));

		Assert.assertEquals(3, allocated.size());
		Assert.assertEquals("task3", allocated.get(0).getName());
		Assert.assertEquals("task1", allocated.get(1).getName());
		Assert.assertEquals("task2", allocated.get(2).getName());

		// Esperado que, a task3 inicie as 9h00 e termine as 9h45
		Assert.assertEquals(getTimestamp(2015, 01, 01, 9, 00, 0), allocated.get(0).getStart());
		Assert.assertEquals(getTimestamp(2015, 01, 01, 9, 45, 0), allocated.get(0).getEnd());
		// Esperado que, a task1 inicie as 9h40 e termine as 10h45
		Assert.assertEquals(getTimestamp(2015, 01, 01, 9, 45, 0), allocated.get(1).getStart());
		Assert.assertEquals(getTimestamp(2015, 01, 01, 10, 45, 0), allocated.get(1).getEnd());
		// Esperado que, a task2 inicie as 10h45 e termine as 12h15
		Assert.assertEquals(getTimestamp(2015, 01, 01, 10, 45, 0), allocated.get(2).getStart());
		Assert.assertEquals(getTimestamp(2015, 01, 01, 12, 15, 0), allocated.get(2).getEnd());
	}

	private Date getTimestamp(int year, int month, int date, int hourOfDay, int minute, int second) {
		calendar.set(year, month, date, hourOfDay, minute, second);
		return calendar.getTime();
	}

}
