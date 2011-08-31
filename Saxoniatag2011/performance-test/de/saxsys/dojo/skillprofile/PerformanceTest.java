package de.saxsys.dojo.skillprofile;

import org.junit.Before;
import org.junit.Test;

import de.saxsys.dojo.skillprofile.business.ProfileManager;
import de.saxsys.dojo.skillprofile.util.Timer;

public class PerformanceTest {
	private String testDirectory = "D:/Projekte/Coding Dojo/workspace/Saxoniatag2011/performance-test/profiles";

	private ProfileManager manager;
	private Timer timer;

	@Before
	public void setUp() throws Exception {
		manager = getProfileManager();
		timer = new Timer();
	}

	@Test
	public void measureTime() throws Exception {
		manager.setProfileDir(testDirectory);
		timer.start();
		manager.getProfilesForName("Katrin Ocke");
		System.out.println(timer.next());
		manager.getProfilesForName("Max Mustermann");
		System.out.println(timer.next());
	}

	private ProfileManager getProfileManager() throws Exception {
		return new ProfileManagerAcceptanceTest().getProfileManager();
	}
}
