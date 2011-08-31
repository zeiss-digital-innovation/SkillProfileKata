package de.saxsys.dojo.skillprofile.business.impl.domain;

import java.util.Date;

import org.junit.Test;

public class PeriodTest {
	@Test
	public void createClearPeriod() {
		new Period();
	}

	@Test
	public void projectHasMethods() {
		Period p = new Period();
		assert p.getStart() instanceof Date;
		assert p.getEnd() instanceof Date;
	}
}
