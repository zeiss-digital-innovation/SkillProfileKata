package de.saxsys.dojo.skillprofile.business.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.ProfileManager;
import de.saxsys.dojo.skillprofile.business.SkillLevel;
import de.saxsys.dojo.skillprofile.mm.business.MmProfileManagerImpl;

public class ProfileManagerTest {

	private static final String TEST_FOLDER = "D:\\Projekte\\Coding Dojo\\workspace\\SkillProfileMihailUndMichael";

	ProfileManager manager;

	@Before
	public void init() {
		manager = new MmProfileManagerImpl();
		manager.setProfileDir(TEST_FOLDER);
	}

	@Test
	public void checkProfileDir() {
		Assert.assertEquals(TEST_FOLDER, manager.getProfileDir());
	}

	@Test
	public void getProfileForID() {
		int profileId = 2;
		Profile p = manager.getProfilesForID(profileId);
		Assert.assertNotNull(p);
		Assert.assertEquals(profileId, p.getEmployeeId());
	}

	@Test
	public void getProfileForName_SingleResult() {
		Collection<Profile> c = manager.getProfilesForName("Max Meier");
		Assert.assertNotNull(c);
		Assert.assertEquals(1, c.size());
		Assert.assertEquals(1, c.iterator().next().getEmployeeId());
	}

	@Test
	public void getProfileForName_MultipleResult() {
		Collection<Profile> c = manager.getProfilesForName("Stefan Mustermann");
		Assert.assertNotNull(c);
		Assert.assertEquals(2, c.size());
		Iterator<Profile> it = c.iterator();
		Profile p = it.next();
		Collection<Integer> expValues = new ArrayList<Integer>(Arrays.asList(2,
				4));
		Assert.assertTrue(expValues.contains(p.getEmployeeId()));
		expValues.remove(p.getEmployeeId());
		p = it.next();
		Assert.assertTrue(expValues.contains(p.getEmployeeId()));
	}

	@Test
	public void getProfileForName_EmptyResult() {
		Collection<Profile> c = manager.getProfilesForName("Anton Fischer");
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.size());

	}

	@Test
	public void getProfileForSkill_SingleResult() {
		Collection<Profile> c = manager.getProfilesForSkill("Java",
				SkillLevel.BEGINNER);
		Assert.assertNotNull(c);
		Assert.assertEquals(1, c.size());
		Assert.assertEquals(2, c.iterator().next().getEmployeeId());
	}

	@Test
	public void getProfileForSkill_MultipleResult() {
		Collection<Profile> c = manager.getProfilesForSkill("Java",
				SkillLevel.PROFESSIONAL);
		Assert.assertNotNull(c);
		Assert.assertEquals(2, c.size());
		Iterator<Profile> it = c.iterator();
		Profile p = it.next();
		Collection<Integer> expValues = new ArrayList<Integer>(Arrays.asList(1,
				4));
		Assert.assertTrue(expValues.contains(p.getEmployeeId()));
		expValues.remove(p.getEmployeeId());
		p = it.next();
		Assert.assertTrue(expValues.contains(p.getEmployeeId()));
	}

	@Test
	public void getProfileForSkill_EmptyResult() {
		Collection<Profile> c = manager.getProfilesForSkill("Java",
				SkillLevel.EXPERIENCED);
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.size());
	}

	@Test
	public void getProfileForSkill_Single2() {
		Collection<Profile> c = manager.getProfilesForSkill("UML2",
				SkillLevel.INTERMEDIATE);
		Assert.assertNotNull(c);
		Assert.assertEquals(1, c.size());
		Assert.assertEquals(1, c.iterator().next().getEmployeeId());
	}

	@Test
	public void getProfileForSkill_Multiple2() {
		Collection<Profile> c = manager.getProfilesForSkill("UML",
				SkillLevel.PROFESSIONAL);
		Assert.assertNotNull(c);
		Assert.assertEquals(2, c.size());
		Iterator<Profile> it = c.iterator();
		Profile p = it.next();
		Collection<Integer> expValues = new ArrayList<Integer>(Arrays.asList(2,
				4));
		Assert.assertTrue(expValues.contains(p.getEmployeeId()));
		expValues.remove(p.getEmployeeId());
		p = it.next();
		Assert.assertTrue(expValues.contains(p.getEmployeeId()));
	}

	@Test
	public void getProfilesForProjectAndDate() {
		doGetProfilesForProjectAndDate("Microsoft Inc.", "Windows 7 Phone",
				new GregorianCalendar(2009, 0, 1), Collections.EMPTY_LIST);
		doGetProfilesForProjectAndDate("KV Sachsen", "Mitgliederportal",
				new GregorianCalendar(2009, 0, 1), Collections.EMPTY_LIST);
		doGetProfilesForProjectAndDate("Microsoft Inc.", "Windows 7 Phone",
				new GregorianCalendar(2009, 2, 1), Arrays.asList(2));
		doGetProfilesForProjectAndDate("KV Sachsen", "Mitgliederportal",
				new GregorianCalendar(2009, 2, 1), Collections.EMPTY_LIST);
		doGetProfilesForProjectAndDate("Microsoft Inc.", "Windows 7 Phone",
				new GregorianCalendar(2009, 4, 1), Arrays.asList(2, 4));
		doGetProfilesForProjectAndDate("Microsoft Inc.", "Windows 7 Phone",
				new GregorianCalendar(2009, 6, 1), Arrays.asList(1, 2, 4));
		doGetProfilesForProjectAndDate("Microsoft Inc.", "Windows 7 Phone",
				new GregorianCalendar(2009, 8, 1), Arrays.asList(1, 2, 3, 4));
		doGetProfilesForProjectAndDate("Microsoft Inc.", "Windows 7 Phone",
				new GregorianCalendar(2010, 0, 1), Arrays.asList(4));
		doGetProfilesForProjectAndDate("KV Sachsen", "Mitgliederportal",
				new GregorianCalendar(2010, 0, 1), Arrays.asList(1, 3));
		doGetProfilesForProjectAndDate("Microsoft Inc.", "Windows 7 Phone",
				new GregorianCalendar(2010, 1, 15), Arrays.asList(2, 4));
		doGetProfilesForProjectAndDate("KV Sachsen", "Mitgliederportal",
				new GregorianCalendar(2010, 1, 15), Arrays.asList(1, 3));
		doGetProfilesForProjectAndDate("Microsoft Inc.", "Windows 7 Phone",
				new GregorianCalendar(2010, 4, 31), Arrays.asList(2, 4));
	}

	private void doGetProfilesForProjectAndDate(String customer,
			String project, Calendar calendar, Collection<Integer> idList) {
		Collection<Profile> c = manager.getProfilesForProjectAndDate(customer,
				project, calendar.getTime());
		Assert.assertNotNull(c);
		Assert.assertEquals(idList.size(), c.size());

		idList = new ArrayList<Integer>(idList);

		for (Profile p : c) {
			Assert.assertTrue(idList.contains(p.getEmployeeId()));
			idList.remove(p.getEmployeeId());
		}
		Assert.assertEquals(0, idList.size());
	}

	@Test
	public void getProjectsForCustomer_SingleResult() {
		Collection<String> c = manager.getProjectsForCustomer("KV Sachsen");
		Assert.assertNotNull(c);
		Assert.assertEquals(1, c.size());
		Assert.assertEquals("Mitgliederportal", c.iterator().next());
	}

	@Test
	public void getProjectsForCustomer_MultiResult() {
		Collection<String> c = manager.getProjectsForCustomer("Microsoft Inc.");
		Assert.assertNotNull(c);
		Assert.assertEquals(2, c.size());
		Iterator<String> it = c.iterator();
		String p = it.next();
		Collection<String> expValues = new ArrayList<String>(Arrays.asList(
				"Windows 7 Phone", "Windows XP"));
		Assert.assertTrue(expValues.contains(p));
		expValues.remove(p);
		p = it.next();
		Assert.assertTrue(expValues.contains(p));
	}

	@Test
	public void getProjectsForCustomer_EmptyResult() {
		Collection<String> c = manager.getProjectsForCustomer("Oracle");
		Assert.assertNotNull(c);
		Assert.assertEquals(0, c.size());
	}

	@Test
	public void validateProfile_valid() {
		Profile p = manager.getProfilesForID(2);
		Assert.assertEquals(true, manager.validateProfile(p));
	}

	@Test
	public void validateProfile_invalid() {
		Profile p = manager.getProfilesForID(1);
		Assert.assertEquals(false, manager.validateProfile(p));
	}

	// Datum ANfang
	// Datum Ende
	// Datum Mitte
	// Datum ausserhalb

}
