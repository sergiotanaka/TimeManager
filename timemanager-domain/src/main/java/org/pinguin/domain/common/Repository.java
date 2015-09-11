package org.pinguin.domain.common;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

public interface Repository<T, I> {

	public void create(T entity);

	public void update(T entity);

	public void delete(T entity);

	public T retrieveById(I id);

	public List<T> retrieveAll();
	
	public List<T> retrieveByCriteria(CriteriaQuery<T> query);

}
