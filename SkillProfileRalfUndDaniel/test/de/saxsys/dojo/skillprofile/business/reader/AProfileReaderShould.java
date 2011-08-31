package de.saxsys.dojo.skillprofile.business.reader;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.saxsys.dojo.skillprofile.rd.business.reader.ProfileReader;

public class AProfileReaderShould {
	
	private ProfileReader reader;

	@Before
	public void before() {
		reader = new ProfileReader("D:/Profiles/");
	}
	
	@Test
	public void readCorrectNumberOfProfiles() {
		Assert.assertEquals(3, reader.readProfiles().size());
	}

}
