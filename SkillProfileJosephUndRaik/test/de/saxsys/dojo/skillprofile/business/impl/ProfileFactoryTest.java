package de.saxsys.dojo.skillprofile.business.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import de.saxsys.dojo.skillprofile.business.SkillLevel;
import de.saxsys.dojo.skillprofile.business.impl.domain.Project;
import de.saxsys.dojo.skillprofile.business.impl.domain.Skill;
import de.saxsys.dojo.skillprofile.business.impl.domain.Technology;

public class ProfileFactoryTest {

	private ProfileFactory pf;

	@Before
	public void setUp() {
		this.pf = new ProfileFactory();
	}

	@Test
	public void createProfileFactory() throws Exception {
		new ProfileFactory();
	}

	@Test
	public void parsePeriod() throws Exception {

		assertEquals("5/2010#1/2011", pf.parsePeriod("05/10 - 01/11")
				.toString());
		assertEquals("5/2010#1/2011", pf.parsePeriod("05/10 -  01/11")
				.toString());
		assertEquals("5/2010#1/2011", pf.parsePeriod("05/10-01/11").toString());

		assertEquals("invalid Period leads to null-result", null,
				pf.parsePeriod("5/10-1/11"));
		assertEquals("invalid Period leads to null-result", null,
				pf.parsePeriod("5/2010-1/2011"));
	}

	@Test
	public void parseTechnologies() throws Exception {

		assertEquals(getTechs("ABC", "DEF"), pf.parseTechnologies("ABC, DEF"));
		assertEquals(getTechs("ABX", "DEF"), pf.parseTechnologies("ABX, DEF"));
		assertEquals(getTechs("ABX", "DEF"), pf.parseTechnologies("ABX, , DEF"));
		assertEquals(getTechs(), pf.parseTechnologies(""));

	}

	@Test
	public void parseProject() throws Exception {
		assertEquals(
				getProject("myProject", "Nikolaus", "05/10-01/11", "ABX", "DEF"),
				pf.parseProject("Nikolaus\nmyProject\n05/10 - 01/11\nABX, DEF"));
		assertEquals(
				getProject("myOtherProject", "CustomerX", "06/10-01/12",
						"tech1", "tech2"),
				pf.parseProject("CustomerX\nmyOtherProject\n06/10 - 01/12\ntech1, tech2"));

		assertEquals(null, pf.parseProject("no Project"));
	}

	@Test
	public void parseSkills() throws Exception {
		assertEquals(getSkills("P", "Java", "B", "UML"),
				pf.parseSkills("P Java\nB UML"));
		assertEquals(getSkills("P", "Java", "F", "C++"),
				pf.parseSkills("P Java\nF C++"));
		assertEquals(null, pf.parseSkills("P Java\nInvalid"));
	}

	// @Test
	// public void parseProfil() throws Exception {
	// Profil valid = getProfil("0815","Stefan Mustermann", new
	// String[]{"P Java", "E C++"},
	// //TODO
	// assertEquals())
	// }

	private Collection<Skill> getSkills(String... strings) {
		Collection<Skill> result = new ArrayList<Skill>(strings.length / 2);
		for (int i = 0; i < strings.length; i += 2) {
			result.add(new Skill(getSkillLevel(strings[i]), new Technology(
					strings[i + 1])));
		}
		return result;
	}

	private SkillLevel getSkillLevel(String level) {
		if (level.equals("B"))
			return SkillLevel.BEGINNER;
		else if (level.equals("E"))
			return SkillLevel.EXPERIENCED;
		else if (level.equals("F"))
			return SkillLevel.INTERMEDIATE;
		else if (level.equals("P"))
			return SkillLevel.PROFESSIONAL;
		return null;
	}

	private Project getProject(String projectName, String customerName,
			String period, String... techs) {
		return new Project(projectName, customerName, pf.parsePeriod(period),
				getTechs(techs));
	}

	private Collection<Technology> getTechs(String... strings) {

		Collection<Technology> result = new ArrayList<Technology>(
				strings.length);
		for (String s : strings) {
			result.add(new Technology(s));
		}

		return result;
	}
}
