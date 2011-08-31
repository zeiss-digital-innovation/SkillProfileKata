package de.saxsys.dojo.skillprofile.sk.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.ProfileManager;
import de.saxsys.dojo.skillprofile.business.SkillLevel;
import de.saxsys.dojo.skillprofile.sk.technical.FileSystemParser;

public class BavariaSystemsProfileManager implements ProfileManager {

    private final FileSystemParser parser = new FileSystemParser();

    private String directoryName;

    @Override
    public void setProfileDir(final String directoryName) {
        this.directoryName = directoryName;
    }

    @Override
    public String getProfileDir() {
        return directoryName;
    }

    @Override
    public Profile getProfilesForID(final int employeeId) {
        Profile result = null;
        for (final BavariaSystemsProfile profile : getAllProfiles()) {
            if (employeeId == profile.getEmployeeId()) {
                result = profile;
                break;
            }
        }
        return result;
    }

    @Override
    public Collection<Profile> getProfilesForName(final String employeeName) {
        final Collection<Profile> result = new ArrayList<Profile>();
        for (final BavariaSystemsProfile profile : getAllProfiles()) {
            if (employeeName.equals(profile.getEmployeeName())) {
                result.add(profile);
            }
        }
        return result;
    }

    @Override
    public Collection<Profile> getProfilesForSkill(final String skill, final SkillLevel level) {
        final Collection<Profile> result = new ArrayList<Profile>();
        for (final BavariaSystemsProfile profile : getAllProfiles()) {
            for (final Skill profileSkill : profile.getProfileSkills()) {
                if (skillMatches(skill, level, profileSkill)) {
                    result.add(profile);
                    break;
                }
            }
        }
        return result;
    }

    private boolean skillMatches(final String expectedSkillName, final SkillLevel expectedLevel,
                    final Skill actual) {
        boolean result = false;
        if (expectedSkillName.equals(actual.getName())) {
            switch (actual.getLevel()) {
            case PROFESSIONAL:
                result = true;
                break;
            case INTERMEDIATE:
                result = SkillLevel.BEGINNER.equals(expectedLevel);
                result |= SkillLevel.EXPERIENCED.equals(expectedLevel);
                result |= SkillLevel.INTERMEDIATE.equals(expectedLevel);
                break;
            case EXPERIENCED:
                result = SkillLevel.BEGINNER.equals(expectedLevel);
                result |= SkillLevel.EXPERIENCED.equals(expectedLevel);
                break;
            case BEGINNER:
                result = SkillLevel.BEGINNER.equals(expectedLevel);
                break;
            default:
                break;
            }
        }
        return result;
    }

    @Override
    public Collection<Profile> getProfilesForProjectAndDate(final String customerName,
                    final String projectName, final Date date) {
        // TODO: To be delivered within milestone 2
        return null;
    }

    @Override
    public Collection<String> getProjectsForCustomer(final String customerName) {
        // TODO: To be delivered within milestone 2
        return null;
    }

    @Override
    public boolean validateProfile(final Profile profile) {
        // TODO: To be delivered within milestone 2
        return false;
    }

    /**
     * Delivers all parsable profiles within the currently set directory.
     * 
     * @return
     */
    final Collection<BavariaSystemsProfile> getAllProfiles() {
        return parser.getProfilesFor(directoryName);
    }

}
