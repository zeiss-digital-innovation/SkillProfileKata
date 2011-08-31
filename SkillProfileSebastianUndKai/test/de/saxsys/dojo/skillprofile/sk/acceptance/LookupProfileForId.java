package de.saxsys.dojo.skillprofile.sk.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.ProfileManager;
import de.saxsys.dojo.skillprofile.sk.business.BavariaSystemsProfileManager;

public class LookupProfileForId {

    @Test
    public void parseThe0815Profile() {
        final ProfileManager manager = new BavariaSystemsProfileManager();
        manager.setProfileDir("test/example/valid");
        final Profile profile = manager.getProfilesForID(815);
        assertEquals(815, profile.getEmployeeId());
    }

    @Test
    public void parseEmptyDirectory() {
        final BavariaSystemsProfileManager manager = new BavariaSystemsProfileManager();
        manager.setProfileDir("test/example/empty");
        final Profile profile = manager.getProfilesForID(815);
        assertNull("No profiles should be found.", profile);
    }
}
