package de.saxsys.dojo.skillprofile.business.impl.domain;

import java.util.Date;

public class Period {

	private Date start;
	private Date end;

	public Period(Date start, Date end) {
		this.start = start;
		this.end = end;
	}

	public Period() {
		// TODO Auto-generated constructor stub
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@SuppressWarnings("deprecation")
	public String toString() {
		return "" + start.getMonth() + "/" + (1900 + start.getYear()) + "#"
				+ end.getMonth() + "/" + (1900 + end.getYear());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Period other = (Period) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

}
