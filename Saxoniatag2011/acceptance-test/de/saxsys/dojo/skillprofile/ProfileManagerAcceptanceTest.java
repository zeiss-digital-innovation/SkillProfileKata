package de.saxsys.dojo.skillprofile;

import static de.saxsys.dojo.skillprofile.machters.ProfileMatchers.withEmployeeId;
import static java.util.Calendar.AUGUST;
import static java.util.Calendar.JUNE;
import static java.util.Calendar.OCTOBER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.junit.matchers.JUnitMatchers.either;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.ProfileManager;
import de.saxsys.dojo.skillprofile.business.SkillLevel;
import de.skillprofil.aud.pm.DaProfileManagerImpl;

public class ProfileManagerAcceptanceTest {

	private String testDirectory = "D:/Projekte/Coding Dojo/workspace/Saxoniatag2011/acceptance-test/profiles";

	private ProfileManager manager;

	@Before
	public void setUp() throws Exception {
		manager = getProfileManager();
	}

	@Test
	public void getProfileDir_returnsDirectorySetBySetter() throws Exception {
		manager.setProfileDir(testDirectory);

		assertThat(
				manager.getProfileDir(),
				either(is(testDirectory)).or(
						is(pathWithBackslashes(testDirectory))));
	}

	@Test
	public void getProfilesForID_returnsExistingProfile() throws Exception {
		manager.setProfileDir(testDirectory);
		Profile profile = manager.getProfilesForID(287);

		assertThat(profile.getEmployeeId(), is(287));
	}

	@Test
	public void getProfilesForID_returnsNullIfProfileDoesNotExist()
			throws Exception {
		manager.setProfileDir(testDirectory);
		Profile profile = manager.getProfilesForID(1);

		assertThat(profile, is(nullValue()));
	}

	@Test
	public void getProfilesForName_returnsProfileForRitaFiebig()
			throws Exception {
		manager.setProfileDir(testDirectory);
		Collection<Profile> profiles = manager
				.getProfilesForName("Rita Fiebig");

		assertThat(profiles.size(), is(1));
		assertThat(profiles, hasItem(withEmployeeId(2800)));
	}

	@Test
	public void getProfilesForName_returnsTwoProfilesForOlafTischer()
			throws Exception {
		manager.setProfileDir(testDirectory);
		Collection<Profile> profiles = manager
				.getProfilesForName("Olaf Tischer");

		assertThat(profiles.size(), is(2));
		assertThat(profiles, hasItem(withEmployeeId(4824)));
		assertThat(profiles, hasItem(withEmployeeId(9996)));
	}

	@Test
	public void getProfilesForProjectAndDate_returnsOneMatchingProfile()
			throws Exception {
		manager.setProfileDir(testDirectory);
		Collection<Profile> profiles = manager.getProfilesForProjectAndDate(
				"Saxonia Systems", "Atlantis", date(1998, AUGUST, 30));

		assertThat(profiles.size(), is(1));
		assertThat(profiles, hasItem(withEmployeeId(9947)));
	}

	@Test
	public void getProfilesForProjectAndDate_returnsEmptyCollectionIfNoMatchingProfilesFound()
			throws Exception {
		manager.setProfileDir(testDirectory);
		Collection<Profile> profiles = manager.getProfilesForProjectAndDate(
				"Saxonia Systems", "Atlantis", date(2004, JUNE, 1));

		assertThat(profiles.size(), is(1));
		assertThat(profiles, hasItem(withEmployeeId(9947)));
	}

	@Test
	public void getProfilesForProjectAndDate_returnsEmptyCollectionIfNoMatchingProjectFound()
			throws Exception {
		manager.setProfileDir(testDirectory);
		Collection<Profile> profiles = manager.getProfilesForProjectAndDate(
				"Australia", "Fruit Picking", date(2011, OCTOBER, 5));

		assertThat(profiles.size(), is(0));
	}

	@Test
	public void getProfilesForSkill_returnsEmptyCollectionIfNoMatchingSkillFound()
			throws Exception {
		manager.setProfileDir(testDirectory);
		Collection<Profile> profiles = manager.getProfilesForSkill(
				"Tabledancing", SkillLevel.BEGINNER);

		assertThat(profiles.size(), is(0));
	}

	@Test
	public void getProfilesForSkill_returnsFiveProfilesForBeginnerJava()
			throws Exception {
		manager.setProfileDir(testDirectory);
		Collection<Profile> profiles = manager.getProfilesForSkill("Java",
				SkillLevel.BEGINNER);

		assertThat(profiles.size(), is(5));
		assertThat(profiles, hasItem(withEmployeeId(1753)));
		assertThat(profiles, hasItem(withEmployeeId(1981)));
		assertThat(profiles, hasItem(withEmployeeId(2218)));
		assertThat(profiles, hasItem(withEmployeeId(4992)));
		assertThat(profiles, hasItem(withEmployeeId(5695)));
	}

	@Test
	public void getProfilesForSkill_returnsFiveProfilesForIntermediateJava()
			throws Exception {
		manager.setProfileDir(testDirectory);
		Collection<Profile> profiles = manager.getProfilesForSkill("Java",
				SkillLevel.INTERMEDIATE);

		assertThat(profiles.size(), is(3));
		assertThat(profiles, hasItem(withEmployeeId(1753)));
		assertThat(profiles, hasItem(withEmployeeId(1981)));
		assertThat(profiles, hasItem(withEmployeeId(5695)));
	}

	@Test
	public void getProjectsForCustomer_returnsThreeProjectsForCustomerMVV()
			throws Exception {
		manager.setProfileDir(testDirectory);
		Collection<String> projects = manager.getProjectsForCustomer("MVV");

		assertThat(projects.size(), is(3));
		assertThat(projects, hasItem(containsString("Platzhalter")));
		assertThat(projects, hasItem(containsString("Atlantis")));
		assertThat(projects,
				hasItem(containsString("Prototype Perpetuum mobile")));
	}

	@Test
	public void getProjectsForCustomer_returnsEmptyCollectionIfCustomerNotFound()
			throws Exception {
		manager.setProfileDir(testDirectory);
		Collection<String> projects = manager
				.getProjectsForCustomer("Bavaria Systems");

		assertThat(projects.size(), is(0));
	}

	@Test
	public void validateProfile_returnsTrueForValidProfile() throws Exception {
		manager.setProfileDir(testDirectory);
		Profile validProfile = manager.getProfilesForID(94);

		assertThat(manager.validateProfile(validProfile), is(true));
	}

	@Test
	public void validateProfile_returnsFalseForInvalidProfile()
			throws Exception {
		manager.setProfileDir(testDirectory);
		Profile validProfile = manager.getProfilesForID(287);

		assertThat(manager.validateProfile(validProfile), is(false));
	}

	@Test
	public void returnsCorrectResultsAfterChangingProfileDir() throws Exception {
		manager.setProfileDir(testDirectory);
		Collection<Profile> profiles = manager.getProfilesForSkill("Java",
				SkillLevel.BEGINNER);

		assertThat(profiles.size(), is(5));
		assertThat(profiles, hasItem(withEmployeeId(1753)));
		assertThat(profiles, hasItem(withEmployeeId(1981)));
		assertThat(profiles, hasItem(withEmployeeId(2218)));
		assertThat(profiles, hasItem(withEmployeeId(4992)));
		assertThat(profiles, hasItem(withEmployeeId(5695)));
		manager.setProfileDir("D:/Projekte/Coding Dojo/workspace/Saxoniatag2011/performance-test/profiles");
		assertThat(profiles.size(), is(116));
	}

	private Date date(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, date);
		return cal.getTime();
	}

	private static String pathWithBackslashes(String filepath) {
		return new File(filepath).getAbsolutePath();
	}

	ProfileManager getProfileManager() throws Exception {
		ProfileManager mgr = null;
		// return new ProfileManagerImpl(testDirectory);

		// Daniel Röder, Annett Buder
		mgr = new DaProfileManagerImpl(); // XXX parseProfiles() nötig

		// Sebastian Schmeck, Kai Zickmann
		// mgr = new BavariaSystemsProfileManager();

		// Joseph Elsner, Raik Bieniek
		// mgr = null; // XXX keine Implementierung

		// Ralf Roßmüller, Daniel Grawunder
		// mgr = new RdProfileManagerImpl();

		// Mihail Panev, Michael Schletter
		// mgr = new MmProfileManagerImpl();
		return mgr;
	}
}
