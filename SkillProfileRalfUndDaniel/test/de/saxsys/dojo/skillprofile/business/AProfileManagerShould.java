package de.saxsys.dojo.skillprofile.business;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.saxsys.dojo.skillprofile.rd.business.RdProfileManagerImpl;

public class AProfileManagerShould {
	
	private RdProfileManagerImpl manager;

	@Before
	public void before() { 
		manager = new RdProfileManagerImpl();
		manager.setProfileDir("D:/Profiles/");
	}

	@Test
	public void provideProfileDir() {
		assertEquals("D:/Profiles/", manager.getProfileDir());
	}
	
	@Test
	public void provideAProfileForId() {
		assertEquals(815, manager.getProfilesForID(815).getEmployeeId());
	}

	@Test
	public void provideProfilesForName() {
		assertEquals(2, manager.getProfilesForName("Stefan Mustermann").size());
	}
	
	@Test
	public void provideProfilesForCustomer() {
		assertEquals(1, manager.getProjectsForCustomer("KV Sachsen").size());
	}
	
	@Test
	public void provideProfilesForSkill() {
		assertEquals(2, manager.getProfilesForSkill("Java", SkillLevel.INTERMEDIATE).size());
	}
}
