package de.saxsys.dojo.skillprofile.rd.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.ProfileManager;
import de.saxsys.dojo.skillprofile.business.SkillLevel;
import de.saxsys.dojo.skillprofile.rd.business.model.Project;
import de.saxsys.dojo.skillprofile.rd.business.model.Skill;
import de.saxsys.dojo.skillprofile.rd.business.reader.ProfileReader;

public class RdProfileManagerImpl implements ProfileManager {

	private String profileDir;

	@Override
	public void setProfileDir(String directoryName) {
		this.profileDir = directoryName;
	}

	@Override
	public String getProfileDir() {
		return profileDir;
	}

	@Override
	public Profile getProfilesForID(int employeeId) {

		List<ProfileImpl> profiles = getProfiles();

		Profile result = null;

		for (Profile profile : profiles) {
			if (profile.getEmployeeId() == employeeId) {
				result = profile;
				break;
			}
		}

		return result;
	}

	@Override
	public Collection<Profile> getProfilesForName(String employeeName) {

		List<ProfileImpl> profiles = getProfiles();
		List<Profile> result = new ArrayList<Profile>();

		for (ProfileImpl profile : profiles) {
			if (profile.getName().equals(employeeName)) {
				result.add(profile);
			}
		}

		return result;
	}

	@Override
	public Collection<Profile> getProfilesForSkill(String skill,
			SkillLevel level) {
		List<ProfileImpl> profiles = getProfiles();
		List<Profile> result = new ArrayList<Profile>();

		for (ProfileImpl profile : profiles) {

			for (Skill profileSkill : profile.getSkills()) {

				if (profileSkill.getName().equals(skill)
						&& isSkillEqualOrBetter(profileSkill.getLevel(), level)) {
					result.add(profile);
				}
			}
		}

		return result;
	}

	private boolean isSkillEqualOrBetter(SkillLevel profileLevel,
			SkillLevel searchLevel) {
		if (searchLevel == SkillLevel.BEGINNER) {
			return true;
		} else if (searchLevel == SkillLevel.EXPERIENCED
				&& profileLevel != SkillLevel.BEGINNER) {
			return true;
		} else if (searchLevel == SkillLevel.INTERMEDIATE
				&& (profileLevel == SkillLevel.INTERMEDIATE || profileLevel == SkillLevel.PROFESSIONAL)) {
			return true;
		} else if (searchLevel == SkillLevel.PROFESSIONAL
				&& profileLevel == SkillLevel.PROFESSIONAL) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Collection<Profile> getProfilesForProjectAndDate(
			String customerName, String projectName, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<String> getProjectsForCustomer(String customerName) {

		List<ProfileImpl> profiles = getProfiles();
		Set<String> result = new HashSet<String>();

		for (ProfileImpl profile : profiles) {

			for (Project project : profile.getProjects()) {

				if (project.getCustomerName().equals(customerName)) {
					result.add(project.getProjectName());
				}
			}
		}

		return result;
	}

	@Override
	public boolean validateProfile(Profile profile) {
		// TODO Auto-generated method stub
		return false;
	}

	private List<ProfileImpl> getProfiles() {
		ProfileReader reader = new ProfileReader(profileDir);
		List<ProfileImpl> profiles = reader.readProfiles();
		return profiles;
	}
}
