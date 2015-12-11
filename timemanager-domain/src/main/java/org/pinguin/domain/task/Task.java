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
	@NotNull
	private String name;
	private String description;
	@Enumerated(EnumType.STRING)
	private Category category;
	/** Duracao, em minutos. */
	private Long duration;
	private Date dueDate;

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

}
