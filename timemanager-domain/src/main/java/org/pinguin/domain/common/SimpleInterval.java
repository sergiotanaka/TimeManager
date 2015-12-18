package org.pinguin.domain.common;

import java.util.Date;

import org.pinguin.domain.common.Interval;

public class SimpleInterval implements Interval {

	private Date start;
	private Date end;

	public SimpleInterval() {
	}

	public SimpleInterval(Date start, Date end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	@Override
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public boolean isIn(Date instant) {
		// O momento e' apos o inicio e antes do fim?
		return instant.after(start) && instant.before(end);
	}

	@Override
	public boolean intersect(Interval interval) {
		return isIn(interval.getStart()) || isIn(interval.getEnd());
	}

	@Override
	public String toString() {
		return "SimpleInterval [start=" + start + ", end=" + end + "]";
	}
}
