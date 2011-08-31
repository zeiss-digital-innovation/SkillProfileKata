package de.saxsys.dojo.skillprofile.sk.business;

import java.util.Collection;

import de.saxsys.dojo.skillprofile.business.Profile;

public class BavariaSystemsProfile implements Profile {

    private final int employeeId;
    private final String employeeName;
    private final Collection<Skill> profileSkills;
    private final Collection<ProjectExperience> projectExperiences;

    public BavariaSystemsProfile(final int employeeId, final String employeeName,
                    final Collection<Skill> profileSkills,
                    final Collection<ProjectExperience> projectExperiences) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.profileSkills = profileSkills;
        this.projectExperiences = projectExperiences;
    }

    @Override
    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public Collection<Skill> getProfileSkills() {
        return profileSkills;
    }

    public Collection<ProjectExperience> getProjectExperiences() {
        return projectExperiences;
    }

}
