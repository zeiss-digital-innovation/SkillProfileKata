package de.saxsys.dojo.skillprofile.rd.business;

import java.util.List;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.rd.business.model.Project;
import de.saxsys.dojo.skillprofile.rd.business.model.Skill;

public class ProfileImpl implements Profile {

	private int employeeId;
	private String name;
	private List<Skill> skills;
	private List<Project> projects;

	@Override
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int id) {
		this.employeeId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

}
