package org.pinguin.service.task;

import java.util.Date;

/**
 * Tarefa alocada no tempo.
 */
public class AllocatedTask {

	private Long taskId;
	private String name;
	private Date start;

	public AllocatedTask() {
	}

	public AllocatedTask(Long taskId, String name, Date start) {
		this.taskId = taskId;
		this.name = name;
		this.start = start;
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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

}
