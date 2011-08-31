package de.skillprofil.aud.pm;

import de.saxsys.dojo.skillprofile.business.SkillLevel;

public class Skill {

	private String name;
	private SkillLevel level;
	
	public void setLevel(SkillLevel level) {
		this.level = level;
	}
	public SkillLevel getLevel() {
		return level;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
