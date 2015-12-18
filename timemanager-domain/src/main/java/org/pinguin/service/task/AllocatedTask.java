package org.pinguin.service.task;

import java.util.Date;

import org.pinguin.domain.common.Interval;

/**
 * Tarefa alocada no tempo.
 */
public class AllocatedTask implements Interval {

	private Long taskId;
	private String name;
	private Date start;
	private Date end;

	public AllocatedTask() {
	}

	public AllocatedTask(Long taskId, String name) {
		this.taskId = taskId;
		this.name = name;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	@Override
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "AllocatedTask [taskId=" + taskId + ", name=" + name + ", start=" + start + ", end=" + end + "]";
	}
}
