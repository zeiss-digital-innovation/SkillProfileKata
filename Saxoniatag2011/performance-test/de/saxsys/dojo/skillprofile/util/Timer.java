package de.saxsys.dojo.skillprofile.util;

public class Timer {

	private long start;

	public void start() {
		start = System.currentTimeMillis();
	}

	public long next() {
		final long now = System.currentTimeMillis();
		final long diff = now - start;
		start = now;
		return diff;
	}

}
