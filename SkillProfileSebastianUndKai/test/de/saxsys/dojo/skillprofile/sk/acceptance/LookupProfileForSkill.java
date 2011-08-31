package de.saxsys.dojo.skillprofile.sk.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.ProfileManager;
import de.saxsys.dojo.skillprofile.business.SkillLevel;
import de.saxsys.dojo.skillprofile.sk.business.BavariaSystemsProfileManager;

public class LookupProfileForSkill {

    @Test
    public void parseSkillsWithAnyExistingSkill() {
        final ProfileManager manager = new BavariaSystemsProfileManager();
        manager.setProfileDir("test/example/valid");
        final Collection<Profile> profiles = manager.getProfilesForSkill("Eclipse",
                        SkillLevel.BEGINNER);
        assertEquals("Wrong number of parsed profiles.", 1, profiles.size());
    }

    @Test
    public void parseSkillsWithAnyExistingButTooLowSkill() {
        final ProfileManager manager = new BavariaSystemsProfileManager();
        manager.setProfileDir("test/example/valid");
        final Collection<Profile> profiles = manager.getProfilesForSkill("Eclipse",
                        SkillLevel.PROFESSIONAL);
        assertTrue("No profiles should be found.", profiles.isEmpty());
    }

    @Test
    public void parseSkillsWithNOTExistingSkill() {
        final ProfileManager manager = new BavariaSystemsProfileManager();
        manager.setProfileDir("test/example/valid");
        final Collection<Profile> profiles = manager.getProfilesForSkill("C#", SkillLevel.BEGINNER);
        assertTrue("No profiles should be found.", profiles.isEmpty());
    }

    @Test
    public void parseEmptyDirectory() {
        final BavariaSystemsProfileManager manager = new BavariaSystemsProfileManager();
        manager.setProfileDir("test/example/empty");
        final Collection<Profile> profiles = manager.getProfilesForSkill("Eclipse",
                        SkillLevel.BEGINNER);
        assertTrue("No profiles should be found.", profiles.isEmpty());
    }

}
