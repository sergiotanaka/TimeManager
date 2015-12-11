package org.pinguin.domain.common;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CqWrapperImpl<T> implements CqWrapper<T> {

	private CriteriaBuilder builder;
	private CriteriaQuery<T> query;
	private Root<T> root;

	public CqWrapperImpl(CriteriaBuilder builder, CriteriaQuery<T> query, Root<T> root) {
		this.builder = builder;
		this.query = query;
		this.root = root;
	}

	@Override
	public CriteriaBuilder cb() {
		return builder;
	}

	@Override
	public CriteriaQuery<T> cq() {
		return query;
	}

	@Override
	public Root<T> root() {
		return root;
	}

}
