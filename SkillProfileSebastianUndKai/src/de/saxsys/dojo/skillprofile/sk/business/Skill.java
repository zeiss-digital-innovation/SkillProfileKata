package de.saxsys.dojo.skillprofile.sk.business;

import de.saxsys.dojo.skillprofile.business.SkillLevel;

public class Skill {

    private final SkillLevel level;

    private final String name;

    public Skill(final SkillLevel level, final String name) {
        super();
        this.level = level;
        this.name = name;
    }

    public SkillLevel getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

}
