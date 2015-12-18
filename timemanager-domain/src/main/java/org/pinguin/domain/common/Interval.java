package org.pinguin.domain.common;

import java.util.Date;

/**
 * Representa um intervalo de tempo.
 */
public interface Interval {

	public Date getStart();

	public Date getEnd();

	public boolean isIn(Date instant);

	public boolean intersect(Interval interval);
}
