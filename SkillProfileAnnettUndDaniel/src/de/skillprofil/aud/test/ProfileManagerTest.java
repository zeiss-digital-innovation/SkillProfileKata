package de.skillprofil.aud.test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static junit.framework.Assert.*;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.SkillLevel;
import de.skillprofil.aud.pm.ProfileImpl;
import de.skillprofil.aud.pm.DaProfileManagerImpl;

public class ProfileManagerTest {
	
	DaProfileManagerImpl pmi;
	final static String VALID_PROFILE_DIR = "d:\\Workspace\\Sandbox\\SkillProfilAnnettUndDaniel\\profiles\\";
	final static String INVALID_PROFILE_DIR = "someDir";
	final static int[] VALID_EMPLOYEE_IDS = new int[] {1815,815};
	final static int[] INVALID_EMPLOYEE_IDS = new int[] {42,23};
	
	@Before
	public void setup() {
		pmi = new DaProfileManagerImpl();
		pmi.setProfileDir(VALID_PROFILE_DIR);
		pmi.parseProfiles();
	}
	
	@Test
	public void setDirectoryTest() {
		pmi.setProfileDir(INVALID_PROFILE_DIR);
		assertEquals(INVALID_PROFILE_DIR + "\\", pmi.getProfileDir());
	}
	
	@Test
	public void canNotReadInvalidDirectory() {
		pmi.setProfileDir(INVALID_PROFILE_DIR);
		File profileDir = new File(pmi.getProfileDir());
		assertFalse(profileDir.exists());
	}
	
	@Test
	public void canReadValidDirectory() {
		File profileDir = new File(pmi.getProfileDir());
		assertTrue(profileDir.exists());
	}

	@Test
	public void existsProfile(){
		List<File> files = pmi.getProfileFiles();

		assertNotNull(files);
		assertFalse(files.isEmpty());
		assertTrue(files.size() > 0);
	}
	
	@Test
	public void getProfilesById() {
		for(int id : VALID_EMPLOYEE_IDS) {
			Profile profile = pmi.getProfilesForID(id);
			assertNotNull(profile);
			assertEquals(id, profile.getEmployeeId());
		}
	}

	@Test
	public void getNullByInvalidId() {
		for(int id : INVALID_EMPLOYEE_IDS) {
			Profile profile = pmi.getProfilesForID(id);
			assertNull(profile);
		}
	}
	
	@Test
	public void getNameInProfileForValidID() {
		Profile profile = pmi.getProfilesForID(815);
		ProfileImpl profileImpl = (ProfileImpl) profile;
		assertNotNull(profileImpl.getMitarbeiterName());
		assertEquals("Stefan Mustermann", profileImpl.getMitarbeiterName());
	}
	
	@Test
	public void getProfilesByName() {
		Collection<Profile> profiles = pmi.getProfilesForName("Stefan Mustermann");
		assertNotNull(profiles);
		assertEquals(profiles.size(),1);
		
		for (Profile profile : profiles) {
			assertEquals("Stefan Mustermann", ((ProfileImpl) profile).getMitarbeiterName());
		}
	}
	
	@Test
	public void getSkill(){
		String skill = "UML";
		
		Collection<Profile> profiles = pmi.getProfilesForSkill(skill, SkillLevel.INTERMEDIATE);
		assertNotNull(profiles);
		
		assertEquals(profiles.size(), 2);
	}
	
	@Test
	public void getProfilesByProjects() throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date projectDate = sdf.parse("31.01.2011");
		
		Collection<Profile> profiles = pmi.getProfilesForProjectAndDate("KV Sachsen", "Mitgliederportal", projectDate);
		assertNotNull(profiles);
		assertEquals(2, profiles.size());
		
	}
	
	@Test
	public void getProjectsForCustomer() {
		Collection<String> projects = pmi.getProjectsForCustomer("VMWare Inc.");
		for (String projectName : projects) {
			assertEquals("Spring Roo", projectName);
		}
	}
	
	@Test
	public void isProfileValid() {
		Profile profile = pmi.getProfilesForID(815);
		assertTrue(pmi.validateProfile(profile));
		
		profile = pmi.getProfilesForID(1815);
		assertFalse(pmi.validateProfile(profile));
	}
	
}
