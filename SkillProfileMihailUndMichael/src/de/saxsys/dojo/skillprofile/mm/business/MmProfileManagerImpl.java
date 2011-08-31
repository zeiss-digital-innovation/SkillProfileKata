package de.saxsys.dojo.skillprofile.mm.business;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.ProfileManager;
import de.saxsys.dojo.skillprofile.business.SkillLevel;

public class MmProfileManagerImpl implements ProfileManager {

	private static final String PROJECT_FILE_SUFFIX = ".profile";

	private String profileDir;
	private List<ProfileImpl> profiles;

	@Override
	public void setProfileDir(String directoryName) {
		profileDir = directoryName;
		profiles = new ArrayList<ProfileImpl>();

		File dir = new File(directoryName);
		String[] profileFiles = dir.list(new FilenameFilter() {
			@Override
			public boolean accept(File directory, String fileName) {
				return fileName.endsWith(PROJECT_FILE_SUFFIX);
			}
		});

		try {
			for (String projectFile : profileFiles) {
				profiles.add(ProfileParser
						.parse(profileDir + "/" + projectFile));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getProfileDir() {

		return profileDir;
	}

	@Override
	public Profile getProfilesForID(int employeeId) {
		for (ProfileImpl profile : profiles) {
			if (profile.getEmployeeId() == employeeId) {
				return profile;
			}
		}
		return null;
	}

	@Override
	public Collection<Profile> getProfilesForName(String employeeName) {
		Collection<Profile> resultList = new ArrayList<Profile>();

		for (ProfileImpl profile : profiles) {
			if (profile.getEmployeeName().equals(employeeName)) {
				resultList.add(profile);
			}
		}
		return resultList;
	}

	@Override
	public Collection<Profile> getProfilesForSkill(String skill,
			SkillLevel level) {
		Collection<Profile> resultList = new ArrayList<Profile>();

		for (ProfileImpl profile : profiles) {
			SkillLevel sLevel = profile.getSkills().get(skill);
			if (sLevel != null && sLevel.equals(level)) {
				resultList.add(profile);
			}
		}
		return resultList;
	}

	@Override
	public Collection<Profile> getProfilesForProjectAndDate(
			String customerName, String projectName, Date date) {
		Collection<Profile> resultList = new ArrayList<Profile>();

		for (ProfileImpl profile : profiles) {
			List<Project> projectList = profile.getProjects();
			for (Project p : projectList) {
				if (p.getCustomer().equals(customerName)
						&& p.getProjectName().equals(projectName)
						&& p.getStart().before(date) && p.getEnd().after(date)) {
					resultList.add(profile);
				}
			}
		}

		return resultList;

	}

	@Override
	public Collection<String> getProjectsForCustomer(String customerName) {
		Collection<String> resultList = new ArrayList<String>();

		for (ProfileImpl profile : profiles) {
			List<Project> projectList = profile.getProjects();
			for (Project p : projectList) {
				if (p.getCustomer().equals(customerName)) {
					if (!resultList.contains(p.getProjectName())) {
						resultList.add(p.getProjectName());
					}
				}
			}
		}

		return resultList;
	}

	@Override
	public boolean validateProfile(Profile profile) {
		List<Project> projectList = ((ProfileImpl) profile).getProjects();

		List<String> skills = new ArrayList<String>();
		for (Project p : projectList) {
			skills.addAll(p.getSkills());
		}
		skills.removeAll(((ProfileImpl) profile).getSkills().keySet());
		return skills.isEmpty();
	}

}
