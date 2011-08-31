package de.saxsys.dojo.skillprofile.business.impl.domain;

import org.junit.Test;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.impl.domain.ProfileImpl;


public class ProfileTest {
	
	@Test
	public void createClearProfile(){
		
		ProfileImpl p = new ProfileImpl();
		Profile p2 = p;
		
	}

	@Test
	public void profileHasMethods() {
		
		ProfileImpl p = new ProfileImpl();
		p.getEmployeeId();
		p.getEmployeeName();
		p.getEmployeeSkills();
		p.getEmployeeProjects();
	}

}
