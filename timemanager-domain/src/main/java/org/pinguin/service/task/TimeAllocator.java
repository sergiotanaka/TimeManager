package org.pinguin.service.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.pinguin.domain.common.Interval;
import org.pinguin.domain.common.SimpleInterval;
import org.pinguin.domain.task.Task;

/**
 * Servico responsavel por alocar as tarefas no tempo.
 */
public class TimeAllocator {

	public List<AllocatedTask> alloc(Collection<Task> tasks, Date initialTime, Interval... restrictions) {

		IntervalSet restrictionSet = new IntervalSet(restrictions);

		// Fazendo uma copia
		List<Task> copy = new ArrayList<>(tasks);

		// Ordenando por prioridade
		sortByPriority(copy);

		List<AllocatedTask> result = new ArrayList<>();

		// Instante que vai avancar durante as alocacoes das tarefas
		Date instant = new Date(initialTime.getTime());

		// Cada iteracao deve consumir uma task.
		for (Task task : copy) {

			// Tempo restante da atividade
			long remain = task.getDuration();

			// Cria uma alocacao de tarefa no tempo
			AllocatedTask allocated = create(task, instant, remain);

			// Verificar se tem restricao para a alocacao
			Interval restriction;
			while ((restriction = restrictionSet.retrieveIntersect(allocated)) != null) {

				// Existe restricao. Necessario quebrar a atividade.
				// Guardar o tempo restante (em min) da atividade
				remain = subtract(allocated.getEnd(), restriction.getStart());

				// Termino esta alocacao no inicio da restricao
				allocated.setEnd(restriction.getStart());

				result.add(allocated);

				// Avancar o instante ate o fim da restricao
				instant = restriction.getEnd();

				// Iniciar o pedaco restante
				allocated = create(task, instant, remain);
			}

			result.add(allocated);

			// Avanco
			progress(instant, remain);
		}

		return result;
	}

	/**
	 * Cria uma alocacao baseado na {@link Task}.
	 * 
	 * @param task
	 * @param start
	 * @param remain
	 * @return
	 */
	private AllocatedTask create(Task task, Date start, long remain) {
		return new AllocatedTask(task.getId(), task.getName(), copy(start), add(start, remain));
	}

	/**
	 * Faz a diferenca, retornando em minutos.
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	private long subtract(Date date1, Date date2) {
		long diffInMilisec = date1.getTime() - date2.getTime();
		return diffInMilisec / (1000L * 60L);
	}

	private Date add(Date date, long minutes) {
		return new Date(date.getTime() + (minutes * 1000L * 60L));
	}

	private void sortByPriority(List<Task> aux) {
		Collections.sort(aux, new Comparator<Task>() {
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

	// CLASSES DE APOIO //

	/**
	 * Representa um conjunto de intervalos.
	 */
	private static class IntervalSet {

		private final Set<Interval> intervals = new HashSet<>();

		public IntervalSet(Interval... interval) {
			intervals.addAll(Arrays.asList(interval));
		}

		public Interval retrieveIntersect(Interval interval) {
			Set<Interval> intersects = new HashSet<>();
			for (Interval candidate : intervals) {
				if (candidate.intersect(interval)) {
					intersects.add(candidate);
				}
			}
			if (intersects.isEmpty()) {
				return null;
			}
			return resume(intersects);
		}

		public Interval resume(Collection<Interval> intervals) {
			Date start = null;
			Date end = null;
			for (Interval interval : intervals) {
				if (start == null || interval.getStart().before(start)) {
					start = interval.getStart();
				}
				if (end == null || interval.getEnd().after(end)) {
					end = interval.getEnd();
				}
			}
			return new SimpleInterval(start, end);
		}
	}
}
