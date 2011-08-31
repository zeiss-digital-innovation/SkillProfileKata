package de.saxsys.dojo.skillprofile.business;

import org.junit.Test;

import de.saxsys.dojo.skillprofile.rd.business.ProfileImpl;

import junit.framework.Assert;

public class AProfileImplShould {

	@Test
	public void testGetEmployeeId() {
		ProfileImpl p = new ProfileImpl();
		p.setEmployeeId(815);
		int id = p.getEmployeeId();
		Assert.assertEquals(815, id);
	}
}
