package de.saxsys.dojo.skillprofile.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author marco.dierenfeldt
 */
public class ProfileManagerImplTest {
	private static final String dirPath = "D:\\Projekte\\Coding Dojo\\workspace\\SkillProfileKata\\test\\de\\saxsys\\dojo\\skillprofile\\util";
	private ProfileManagerImpl instance;

	public ProfileManagerImplTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
		instance = new ProfileManagerImpl(dirPath);
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of getProjectsForCustomer method, of class ProfileManagerImpl.
	 */
	@Test
	public void testGetProjectsForCustomer() {
		System.out.println("getProjectsForCustomer");
		String customerName = "Microsoft";

		Collection<String> result = instance
				.getProjectsForCustomer(customerName);
		assertEquals(1, result.size());

		String expected = "Windows9 Core";
		assertTrue(result.contains(expected));

		result = instance.getProjectsForCustomer("no customer");
		assertEquals(0, result.size());

	}

	/**
	 * Test of getProfilesForName method, of class ProfileManagerImpl.
	 */
	@Test
	public void testGetProfilesForName() {
		System.out.println("getProfilesForName");
		String employeeName = "hellchicken metalrocker";

		Collection<Profile> result = instance.getProfilesForName(employeeName);
		assertEquals(1, result.size());

		result = instance.getProfilesForName("no employee");
		assertEquals(0, result.size());

	}

	/**
	 * Test of setProfileDir method, of class ProfileManagerImpl.
	 */
	@Test
	public void testSetProfileDir_and_getProfileDir() {
		System.out.println("setProfileDir");
		String directoryName = "D:\\work\\00_temp\\profiles";

		instance.setProfileDir(directoryName);
		String result = instance.getProfileDir();

		assertEquals(directoryName, result);
	}

	/**
	 * Test of getProfilesForID method, of class ProfileManagerImpl.
	 */
	@Test
	public void testGetProfileForID() {
		System.out.println("getProfilesForID");
		int employeeId = 666;

		Profile result = instance.getProfilesForID(employeeId);
		assertEquals(employeeId, result.getEmployeeId());

		employeeId = 815;
		result = instance.getProfilesForID(employeeId);
		assertNull(result);
	}

	/**
	 * Test of getProfilesForSkill method, of class ProfileManagerImpl.
	 */
	@Test
	public void testGetProfilesForSkill() {
		System.out.println("getProfilesForSkill");
		String skill = "Agile";
		SkillLevel level = SkillLevel.PROFESSIONAL;
		Collection result = instance.getProfilesForSkill(skill, level);
		assertEquals(0, result.size());

		skill = "stricken";
		level = SkillLevel.EXPERIENCED;
		result = instance.getProfilesForSkill(skill, level);
		assertEquals(1, result.size());
	}

	/**
	 * Test of getProfilesForCustomerProjectAndDate method, of class
	 * ProfileManagerImpl.
	 */
	@Test
	public void testGetProfilesForProjectAndDate() {
		System.out.println("getProfilesForProjectAndDate");
		String projectName = "";
		String customerName = "";
		Date date = null;
		ProfileManagerImpl instance = null;
		Collection expResult = null;
		Collection result = instance.getProfilesForProjectAndDate(customerName,
				projectName, date);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of validateProfile method, of class ProfileManagerImpl.
	 */
	@Test
	public void testValidateProfile() {
		System.out.println("validateProfile");
		Profile profile = instance.getProfilesForID(666);
		boolean expResult = true;
		boolean result = instance.validateProfile(profile);
		assertEquals(expResult, result);
	}
}
