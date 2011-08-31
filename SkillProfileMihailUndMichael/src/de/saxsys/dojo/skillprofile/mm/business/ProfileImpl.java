package de.saxsys.dojo.skillprofile.mm.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.SkillLevel;

public class ProfileImpl implements Profile {

	
	private int employeeId;
	private String employeeName;
	private Map<String, SkillLevel> skills = new HashMap<String, SkillLevel>();
	private List<Project> projects;
	
	@Override
	public int getEmployeeId() {
		return employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Map<String, SkillLevel> getSkills() {
		return skills;
	}

	public void setSkills(Map<String, SkillLevel> skills) {
		this.skills = skills;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	

}
