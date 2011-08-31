package de.saxsys.dojo.skillprofile.sk.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.ProfileManager;
import de.saxsys.dojo.skillprofile.sk.business.BavariaSystemsProfileManager;

public class LookupProfileForName {

    @Test
    public void parseThe0815Profile() {
        final ProfileManager manager = new BavariaSystemsProfileManager();
        manager.setProfileDir("test/example/valid");
        final Collection<Profile> profiles = manager.getProfilesForName("Stefan Mustermann");
        assertEquals("Wrong number of parsed profiles.", 1, profiles.size());
        for (final Profile profile : profiles) {
            assertEquals("Unexpected employee id of the parsed profile.", 815,
                            profile.getEmployeeId());
        }
    }

    @Test
    public void parseEmptyDirectory() {
        final BavariaSystemsProfileManager manager = new BavariaSystemsProfileManager();
        manager.setProfileDir("test/example/empty");
        final Collection<Profile> profiles = manager.getProfilesForName("Stefan Mustermann");
        assertTrue("No profiles should be found.", profiles.isEmpty());
    }

}
