package org.pinguin.domain.common;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.google.inject.persist.Transactional;

/**
 * Implementacao das operacoes basicas de um repositorio.
 * 
 * @author stanaka
 * 
 * @param <T>
 * @param <I>
 */
@Transactional
public abstract class BasicRepositoryImpl<T, I> implements Repository<T, I> {

	// @PersistenceContext(unitName = "main")
	@Inject
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
		return entityManager.find(getEntityType(), id);
	}

	@Override
	public List<T> retrieveAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityType());
		Root<T> root = cq.from(getEntityType());
		cq.select(root);
		TypedQuery<T> query = entityManager.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public List<T> retrieveByCondition(String conditionalExpression, Param... params) {
		if (conditionalExpression.isEmpty()) {
			// TODO tratar
		}

		String jpql = String.format("SELECT e FROM %s e WHERE %s", getEntityType().getName(), conditionalExpression);

		TypedQuery<T> query = entityManager.createQuery(jpql, getEntityType());
		for (Param param : params) {
			query.setParameter(param.getName(), param.getValue());
		}

		List<T> results = query.getResultList();
		return results;
	}

	protected abstract Class<T> getEntityType();

}
