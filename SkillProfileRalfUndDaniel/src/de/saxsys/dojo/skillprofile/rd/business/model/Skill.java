package de.saxsys.dojo.skillprofile.rd.business.model;

import de.saxsys.dojo.skillprofile.business.SkillLevel;

public class Skill {

	private SkillLevel level;
	private String name;
	
	public SkillLevel getLevel() {
		return level;
	}
	
	public void setLevel(SkillLevel level) {
		this.level = level;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
