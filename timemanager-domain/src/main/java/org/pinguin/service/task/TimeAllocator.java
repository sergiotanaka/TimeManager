package org.pinguin.service.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.pinguin.domain.task.Task;

/**
 * Servico responsavel por alocar as tarefas no tempo.
 */
public class TimeAllocator {

	public List<AllocatedTask> alloc(Collection<Task> tasks) {

		List<Task> aux = new ArrayList<>(tasks);

		// Ordenando por prioridade
		aux.sort(new Comparator<Task>() {
			@Override
			public int compare(Task o1, Task o2) {
				return o1.getPriority().compareTo(o2.getPriority());
			}
		});

		List<AllocatedTask> result = new ArrayList<>();

		for (Task task : aux) {
			result.add(new AllocatedTask(task.getId(), task.getName(), task.getDueDate()));
		}

		return result;
	}

}
