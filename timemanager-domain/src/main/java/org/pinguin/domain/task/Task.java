package org.pinguin.domain.task;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Atividade. Entidade principal do sistema.
 */
@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	/** Indice para definir a prioridade da tarefa. */
	private Long priority;
	@NotNull
	private String name;
	private String description;
	@Enumerated(EnumType.STRING)
	private Category category;
	@Enumerated(EnumType.STRING)
	private TaskState state = TaskState.INCOMPLETE;
	/** Duracao, em minutos. */
	private Long duration;
	private Date dueDate;
	private String assignee;

	// CONSTRUTORES //

	public Task() {
	}

	public Task(String name, String description, Category category, Long duration, Date dueDate) {
		this();
		this.name = name;
		this.description = description;
		this.category = category;
		this.duration = duration;
		this.dueDate = dueDate;
	}

	public Task(String name, String description, Category category, Long duration) {
		this(name, description, category, duration, null);
	}

	public Task(String name, Long priority, Long duration) {
		this.name = name;
		this.priority = priority;
		this.duration = duration;
	}

	// GETTERS e SETTERS //

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public TaskState getState() {
		return state;
	}

	public void setState(TaskState state) {
		this.state = state;
	}

	/**
	 * @return {@link #duration}.
	 */
	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

}
