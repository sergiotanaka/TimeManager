package org.pinguin.domain.common;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Implementacao das operacoes basicas de um repositorio.
 * 
 * @author stanaka
 * 
 * @param <T>
 * @param <I>
 */
public abstract class BasicRepositoryImpl<T, I> implements Repository<T, I> {

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
		return entityManager.find(retrieveEntityType(), id);
	}

	@Override
	public List<T> retrieveAll() {
		Class<T> entityType = retrieveEntityType();

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> q = cb.createQuery(entityType);
		Root<T> c = q.from(entityType);
		q.select(c);
		TypedQuery<T> query = entityManager.createQuery(q);
		List<T> results = query.getResultList();
		return results;
	}

	@Override
	public List<T> retrieveByCriteria(CriteriaQuery<T> query) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	private Class<T> retrieveEntityType() {
		Class<T> type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return type;
	}

}
