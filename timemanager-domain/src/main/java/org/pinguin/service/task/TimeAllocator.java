package org.pinguin.service.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.plaf.synth.SynthSeparatorUI;

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

		Date instant = new Date(initialTime.getTime());
		// Cada iteracao deve consumir uma task.
		for (Task task : copy) {
			// Cria uma alocacao de tarefa no tempo
			AllocatedTask allocated = new AllocatedTask(task.getId(), task.getName(), copy(instant),
					add(instant, task.getDuration()));

			// Verificar se tem restricao para a alocacao
			Interval restriction;
			while ((restriction = restrictionSet.retrieveIntersect(allocated)) != null) {
				// Existe restricao. Necessario quebrar a atividade.
				// Guardar o tempo restante (em min) da atividade
				long remain = subtract(allocated.getEnd(), restriction.getStart());
				allocated.setEnd(restriction.getStart());
				result.add(allocated);

				// Mover o instante para o final da restricao
				instant = restriction.getEnd();

				// Iniciar o pedaco restante
				allocated = new AllocatedTask(task.getId(), task.getName(), instant, add(instant, remain));
			}

			// Calcular o avanco
			progress(instant, task.getDuration());

			allocated.setEnd(copy(instant));

			result.add(allocated);
		}

		return result;
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

	private static class IntervalSet {

		private final Set<Interval> intervals = new HashSet<>();

		public IntervalSet() {
		}

		public IntervalSet(Interval... restrictions) {
			add(restrictions);
		}

		public void add(Interval... interval) {
			intervals.addAll(Arrays.asList(interval));
		}

		public void remove(Interval interval) {
			intervals.remove(interval);
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

		public Interval retrieveIntersect(Date instant) {
			Set<Interval> betweens = new HashSet<>();
			for (Interval candidate : intervals) {
				if (candidate.isIn(instant)) {
					betweens.add(candidate);
				}
			}
			return resume(betweens);
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

		public boolean isIn(Date instant) {
			for (Interval interval : intervals) {
				if (interval.isIn(instant)) {
					return true;
				}
			}
			return false;
		}

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
