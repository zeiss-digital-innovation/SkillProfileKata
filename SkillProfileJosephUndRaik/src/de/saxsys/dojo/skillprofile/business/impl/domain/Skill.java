package de.saxsys.dojo.skillprofile.business.impl.domain;

import de.saxsys.dojo.skillprofile.business.SkillLevel;

public class Skill {

	private SkillLevel level;
	private Technology tech;

	public Skill(SkillLevel level, Technology tech) {
		this.level = level;
		this.tech = tech;
	}

	public Skill() {
		// TODO Auto-generated constructor stub
	}

	public SkillLevel getSkillLevel() {
		return level;
	}

	public Technology getTechnology() {
		return tech;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((tech == null) ? 0 : tech.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		if (level != other.level)
			return false;
		if (tech == null) {
			if (other.tech != null)
				return false;
		} else if (!tech.equals(other.tech))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Skill [level=" + level + ", tech=" + tech + "]";
	}

}
