package de.saxsys.dojo.skillprofile.business.reader;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import de.saxsys.dojo.skillprofile.business.SkillLevel;
import de.saxsys.dojo.skillprofile.rd.business.ProfileImpl;
import de.saxsys.dojo.skillprofile.rd.business.model.Project;
import de.saxsys.dojo.skillprofile.rd.business.model.Skill;
import de.saxsys.dojo.skillprofile.rd.business.reader.ProfileParser;

public class AProfileParserShould {
	
	private ProfileImpl profile;

	@Before
	public void beforeClass() {
		ProfileParser parser = new ProfileParser(new File("D:/Profiles/0815.profile"));
			profile = (ProfileImpl)parser.parseFile();
	}
	
	@Test
	public void readCorrectEmployeeId() {
		assertEquals(815, profile.getEmployeeId());
	}
	
	@Test
	public void readCorrectName() {
		assertEquals("Stefan Mustermann", profile.getName());
	}

	@Test
	public void readCorrectSkills() {
		assertEquals(8, profile.getSkills().size());
		Skill skill = profile.getSkills().get(3);
		assertEquals(SkillLevel.INTERMEDIATE, skill.getLevel());
		assertEquals("Eclipse", skill.getName());
	}
	
	@Test
	public void readCorrectProjects() {
		assertEquals(3, profile.getProjects().size());
		Project project = profile.getProjects().get(1);
		assertEquals("KV Sachsen", project.getCustomerName());
		assertEquals("Mitgliederportal", project.getProjectName());
		assertEquals("05/10 - 01/11", project.getPeriod());
		assertEquals("Java", project.getSkillNames()[0]);
		assertEquals("Eclipse", project.getSkillNames()[1]);
	}
	
}
