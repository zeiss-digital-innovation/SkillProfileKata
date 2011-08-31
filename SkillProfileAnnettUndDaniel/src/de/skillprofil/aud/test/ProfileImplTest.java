package de.skillprofil.aud.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static junit.framework.Assert.*;
import de.skillprofil.aud.pm.ProfileImpl;
import de.skillprofil.aud.pm.Skill;

public class ProfileImplTest {
	
	@Test
	public void addValidSkills() {
		String[] validSkillLines = new String[] {
			"P Java",
			"E UML",
			"F Eclipse",
			"B Windows XP"
		};
		
		ProfileImpl profile = new ProfileImpl();
		
		for(String skill : validSkillLines){
			profile.addSkill(skill);
		}
		
		assertEquals(4,profile.getSkills().size());
		
		for(Skill skill : profile.getSkills()){
			switch (skill.getLevel()) {
			case PROFESSIONAL:
				assertEquals("Java", skill.getName());
				break;
			case INTERMEDIATE:
				assertEquals("Eclipse", skill.getName());
				break;
			case BEGINNER:
				assertEquals("Windows XP", skill.getName());
				break;
			case EXPERIENCED:
				assertEquals("UML", skill.getName());
				break;
			}
		}
		
	}

	@Test
	public void doNotAddInvalidSkills() {
		String[] invalidSkillLines = new String[] {
			"ABC",
			"X UML",
		};
		
		ProfileImpl profile = new ProfileImpl();
		
		for(String skill : invalidSkillLines){
			profile.addSkill(skill);
		}
		
		assertEquals(0, profile.getSkills().size());
	}
	
	
	
	@Test
	public void parseProjectNameAndCustomer() {
		List<String> projectLines = new ArrayList<String>();
		projectLines.add("KV Sachsen");
		projectLines.add("Mitgliederportal");
		projectLines.add("05/10 - 01/11");
		projectLines.add("Java, Eclipse");
		
		ProfileImpl profile = new ProfileImpl();
		profile.addProject(projectLines);
	}
}
