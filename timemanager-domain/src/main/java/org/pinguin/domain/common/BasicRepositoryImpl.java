package org.pinguin.domain.common;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Implementacao das operacoes basicas de um repositorio.
 * 
 * @author stanaka
 * 
 * @param <T>
 * @param <I>
 */
public class BasicRepositoryImpl<T, I> implements Repository<T, I> {

	@PersistenceContext(unitName = "main")
	private EntityManager entityManager;

	@Override
	public void create(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(T entity) {
		entityManager.merge(entity);
		entityManager.remove(entity);
	}

	@Override
	public T retrieveById(I id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> retrieveAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> retrieveByCriteria(CriteriaQuery<T> query) {
		// TODO Auto-generated method stub
		return null;
	}

}
