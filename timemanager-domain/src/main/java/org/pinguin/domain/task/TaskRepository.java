package org.pinguin.domain.task;

import org.pinguin.domain.common.BasicRepositoryImpl;

/**
 * Repositorio para a entidade {@link Task}.
 */
public class TaskRepository extends BasicRepositoryImpl<Task, Long> {

	@Override
	protected Class<Task> getEntityType() {
		return Task.class;
	}

}
