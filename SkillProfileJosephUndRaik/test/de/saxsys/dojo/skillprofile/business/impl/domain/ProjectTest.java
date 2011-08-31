package de.saxsys.dojo.skillprofile.business.impl.domain;

import java.util.Collection;

import org.junit.Test;

public class ProjectTest {
	@Test
	public void createClearProject() {
		new Project();
	}

	@Test
	public void projectHasMethods() {
		Project p = new Project();

		assert p.getProjectName() instanceof String;
		assert p.getCustomerName() instanceof String;
		assert p.getProjectPeriod() instanceof Period;
		assert p.getTechnologies() instanceof Collection;
	}
}
