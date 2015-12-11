package org.pinguin.domain.common;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

/**
 * Servico responsavel pela persistencia de entidades.
 * 
 * @param <T>
 *            Tipo da entidade.
 * @param <I>
 *            Tipo do identificador da entidade.
 */
public interface Repository<T, I> {

	public void create(T entity);

	public void update(T entity);

	public void delete(T entity);

	public T retrieveById(I id);

	public List<T> retrieveAll();

	public CqWrapper<T> getCriteriaQuery();

	public List<T> retrieveByCriteria(CriteriaQuery<T> cq);

}
