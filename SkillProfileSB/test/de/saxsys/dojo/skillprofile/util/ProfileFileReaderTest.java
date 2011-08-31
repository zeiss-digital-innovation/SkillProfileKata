/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.saxsys.dojo.skillprofile.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.saxsys.dojo.skillprofile.business.ProfileImpl;

/**
 * 
 * @author marco.dierenfeldt
 */
public class ProfileFileReaderTest {
	String filename = "D:\\Projekte\\Coding Dojo\\workspace\\SkillProfileKata\\test\\de\\saxsys\\dojo\\skillprofile\\util\\testProfile.profile";

	public ProfileFileReaderTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of readProfileFromFile method, of class ProfileFileReader.
	 */
	@Test
	public void testReadProfileFromFile() throws FileNotFoundException,
			IOException {
		System.out.println("readProfileFromFile");
		ProfileFileReader instance = new ProfileFileReader(new File(filename));

		ProfileImpl result = instance.readProfileFromFile();
		assertEquals(666, result.getEmployeeNumber());
		assertEquals("hellchicken metalrocker", result.getEmployeeName());
	}
}
