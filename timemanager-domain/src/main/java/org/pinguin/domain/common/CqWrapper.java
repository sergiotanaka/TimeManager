package org.pinguin.domain.common;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public interface CqWrapper<T> {

	public CriteriaBuilder cb();

	public CriteriaQuery<T> cq();
	
	public Root<T> root();
}
