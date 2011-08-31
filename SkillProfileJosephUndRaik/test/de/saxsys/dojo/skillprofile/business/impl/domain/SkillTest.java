package de.saxsys.dojo.skillprofile.business.impl.domain;

import org.junit.Test;

public class SkillTest {

	@Test
	public void createClearSkill() {
		new Skill();
	}

	@Test
	public void skillHasMethods() {

		Skill s = new Skill();
		s.getTechnology();
		s.getSkillLevel();
	}
}
