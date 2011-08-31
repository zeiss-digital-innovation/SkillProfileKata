package de.saxsys.dojo.skillprofile.machters;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

import de.saxsys.dojo.skillprofile.business.Profile;

public class ProfileMatchers {
	public static Matcher<? extends Profile> withEmployeeId(final int employeeId) {
		return new TypeSafeMatcher<Profile>() {

			@Override
			public void describeTo(Description desc) {
				desc.appendText("a profile with employee id ");
				desc.appendValue(employeeId);
			}

			@Override
			public boolean matchesSafely(Profile profile) {
				return profile.getEmployeeId() == employeeId;
			}
		};
	}
}
