package org.pinguin.domain.common;

import java.util.List;

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

	/**
	 * @param conditionalExpression
	 *            JQPL, somente o termo depois do WHERE.
	 * @return
	 */
	public List<T> retrieveByCondition(String conditionalExpression, Param... params);

	public static class Param {
		private final String name;
		private final Object value;

		public Param(String name, Object value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public Object getValue() {
			return value;
		}

	}

}
