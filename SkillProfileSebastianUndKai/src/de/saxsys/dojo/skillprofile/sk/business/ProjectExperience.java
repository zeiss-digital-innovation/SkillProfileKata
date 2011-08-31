package de.saxsys.dojo.skillprofile.sk.business;

import java.util.Collection;

public class ProjectExperience {

    private final String company;
    private final String project;
    private final String fromDate;
    private final String tillDate;
    private final Collection<String> usedSkillNames;

    public ProjectExperience(final String company, final String project, final String fromDate,
                    final String tillDate, final Collection<String> usedSkills) {
        super();
        this.company = company;
        this.project = project;
        this.fromDate = fromDate;
        this.tillDate = tillDate;
        usedSkillNames = usedSkills;
    }

    public String getCompany() {
        return company;
    }

    public String getProject() {
        return project;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getTillDate() {
        return tillDate;
    }

    public Collection<String> getUsedSkillNames() {
        return usedSkillNames;
    }

}
