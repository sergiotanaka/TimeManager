package org.pinguin.service.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.pinguin.domain.common.Interval;
import org.pinguin.domain.task.Task;

/**
 * Servico responsavel por alocar as tarefas no tempo.
 */
public class TimeAllocator {

	public List<AllocatedTask> alloc(Collection<Task> tasks, Date initialTime, Interval... restrictions) {

		List<Task> aux = new ArrayList<>(tasks);

		// Ordenando por prioridade
		sortByPriority(aux);

		List<AllocatedTask> result = new ArrayList<>();

		Date instant = new Date(initialTime.getTime());
		for (Task task : aux) {

			AllocatedTask allocated = new AllocatedTask(task.getId(), task.getName());
			allocated.setStart(copy(instant));
			// Calcular o avanco
			progress(instant, task.getDuration());
			allocated.setEnd(copy(instant));

			result.add(allocated);
		}

		return result;
	}

	private void sortByPriority(List<Task> aux) {
		aux.sort(new Comparator<Task>() {
			@Override
			public int compare(Task o1, Task o2) {
				return o1.getPriority().compareTo(o2.getPriority());
			}
		});
	}

	private Date copy(Date original) {
		return new Date(original.getTime());
	}

	private void progress(Date date, long minutes) {
		date.setTime(date.getTime() + (minutes * 1000L * 60L));
	}
}
