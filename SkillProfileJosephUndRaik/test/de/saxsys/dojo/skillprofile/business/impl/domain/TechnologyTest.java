package de.saxsys.dojo.skillprofile.business.impl.domain;

import org.junit.Test;

import de.saxsys.dojo.skillprofile.business.impl.domain.Technology;

public class TechnologyTest {
	@Test
	public void createClearTechnology() {
		new Technology();
	}

	@Test
	public void technologyHasMethods() {
		new Technology().getName();
	}
}
