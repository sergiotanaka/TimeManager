package org.pinguin.service.task;

import java.util.Date;

/**
 * Representa um intervalo de tempo.
 */
public interface Interval {

	public Date getStart();

	public Date getEnd();
}
