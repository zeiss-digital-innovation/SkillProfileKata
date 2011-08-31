package de.saxsys.dojo.skillprofile.sk.technical;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import de.saxsys.dojo.skillprofile.sk.business.BavariaSystemsProfile;
import de.saxsys.dojo.skillprofile.sk.business.ProjectExperience;
import de.saxsys.dojo.skillprofile.sk.business.Skill;
import de.saxsys.dojo.skillprofile.sk.technical.FileSystemParser;

public class TestFileSystemParser {

    @Test
    public void getEmptyProfileListForEmptyDirectories() {
        final FileSystemParser parser = new FileSystemParser();
        final Collection<BavariaSystemsProfile> profiles = parser
                        .getProfilesFor("test/example/empty");
        assertTrue(profiles.isEmpty());
    }

    @Test
    public void getAnyButOneProfileFromValidDirectory() {
        final FileSystemParser parser = new FileSystemParser();
        final List<BavariaSystemsProfile> profiles = parser.getProfilesFor("test/example/valid");
        assertEquals("Unexpected number of profiles.", 1, profiles.size());
    }

    @Test
    public void getCorrectProfileFromValidDirectory() {
        final FileSystemParser parser = new FileSystemParser();
        final List<BavariaSystemsProfile> profiles = parser.getProfilesFor("test/example/valid");
        assertEquals("Unexpected number of profiles.", 1, profiles.size());
        final BavariaSystemsProfile profile = profiles.get(0);
        assertEquals("Unexpected employee id.", 815, profile.getEmployeeId());
        assertEquals("Unexpected employee name.", "Stefan Mustermann", profile.getEmployeeName());
        // profile skills
        final Collection<Skill> profileSkills = profile.getProfileSkills();
        assertEquals("Unexpected number of profile skills.", 8, profileSkills.size());
        // project experience
        final Collection<ProjectExperience> projectExperience = profile.getProjectExperiences();
        assertEquals("Unexpected number of project experiences.", 3, projectExperience.size());
    }

}
