package de.saxsys.dojo.skillprofile.sk.business;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

import java.util.Collection;

import org.junit.Test;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.ProfileManager;
import de.saxsys.dojo.skillprofile.business.SkillLevel;
import de.saxsys.dojo.skillprofile.sk.business.BavariaSystemsProfileManager;

public class TestThatABavariaSystemsProfileManager {

    @Test
    @SuppressWarnings("unused")
    public void canBeInitialized() {
        final ProfileManager manager = new BavariaSystemsProfileManager();
    }

    @Test
    public void setAProfileDirectory() {
        final ProfileManager manager = new BavariaSystemsProfileManager();
        manager.setProfileDir("test/example/empty");
        assertEquals("test/example/empty", manager.getProfileDir());
    }

    @Test
    public void readProfileData() {
        final BavariaSystemsProfileManager manager = new BavariaSystemsProfileManager();
        manager.setProfileDir("test/example/valid");
        assertFalse("Expected parsed profiles.", manager.getAllProfiles().isEmpty());
    }

    @Test
    public void findProfilesByGivenName() {
        final BavariaSystemsProfileManager manager = new BavariaSystemsProfileManager();
        manager.setProfileDir("test/example/valid");
        final Collection<Profile> profiles = manager.getProfilesForName("Stefan Mustermann");
        assertEquals(1, profiles.size());
        assertEquals(815, profiles.iterator().next().getEmployeeId());

    }

    @Test
    public void findProfileByGivenId() {
        final BavariaSystemsProfileManager manager = new BavariaSystemsProfileManager();
        manager.setProfileDir("test/example/valid");
        final Profile profile = manager.getProfilesForID(815);
        assertEquals(815, profile.getEmployeeId());
    }

    @Test
    public void findProfileBySkill() {
        final BavariaSystemsProfileManager manager = new BavariaSystemsProfileManager();
        manager.setProfileDir("test/example/valid");
        final Collection<Profile> profiles = manager.getProfilesForSkill("Eclipse",
                        SkillLevel.BEGINNER);
        assertEquals("Wrong number of parsed profiles.", 1, profiles.size());
    }
}
