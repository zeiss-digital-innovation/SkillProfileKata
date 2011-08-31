package de.saxsys.dojo.skillprofile.business;

import java.util.Collection;
import java.util.Date;

/**
 * ProfileManager interface for the BavariaSystems AG
 * <p>
 * CodingDojo - 01.07.2011 - Saxoniatag Sommer 2011
 * </p>
 * 
 * @author marco.dierenfeldt
 */
public interface ProfileManager {

	/**
	 * Accessor method to set the dir which holds the profile files.
	 * <p>
	 * Hint: there may also be other files in the directory which are not
	 * profiles.
	 * </p>
	 * 
	 * @param directoryName
	 *            absolute path
	 */
	public void setProfileDir(String directoryName);

	/**
	 * Accessor method that returns the full pathname of the current profile
	 * directory, e.g. "C:\\bavariasystems\\skills\\profiles"
	 */
	public String getProfileDir();

	/**
	 * Returns the profile for the given ID.
	 */
	public Profile getProfilesForID(int employeeId);

	/**
	 * Returns the profile(s) for the employee specified by employeeName.
	 * employeeName is formatted "Firstname Lastname".
	 */
	public Collection<Profile> getProfilesForName(String employeeName);

	/**
	 * Returns all profiles which contain a skill at a given skill level.
	 * <p>
	 * Returns an empty collection if no profile with skill at the given level
	 * or better is found.
	 * </p>
	 */
	public Collection<Profile> getProfilesForSkill(String skill,
			SkillLevel level);

	/**
	 * Returns the profiles of employees which have worked in a customer project
	 * at a given date.
	 * <p>
	 * Returns an empty collection if no profile with the given project and date
	 * is found.
	 * </p>
	 */
	public Collection<Profile> getProfilesForProjectAndDate(
			String customerName, String projectName, Date date);

	/**
	 * Returns the unique project names for the customer specified by
	 * customerName.
	 * <p>
	 * Returns an empty collection if the customer is not found.
	 * </p>
	 */
	public Collection<String> getProjectsForCustomer(String customerName);

	/**
	 * Validates a profile.
	 * <p>
	 * A profile is valid if all skills that have been used in projects also
	 * show up in the employee's skill list.
	 * </p>
	 * 
	 * @return {@code true} if profile is valid, otherwise {@code false}.
	 */
	public boolean validateProfile(Profile profile);
}
