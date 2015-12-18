package org.pinguin.service.task;

import java.util.Date;

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
}
