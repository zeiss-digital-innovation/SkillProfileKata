package de.saxsys.dojo.skillprofile.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import de.saxsys.dojo.skillprofile.util.ProfileFileFileFilter;
import de.saxsys.dojo.skillprofile.util.ProfileFileReader;

/**
 * 
 * @author marco.dierenfeldt
 */
public class ProfileManagerImpl implements ProfileManager {

	private File profileDir;

	public ProfileManagerImpl(String profileDir) {
		this.profileDir = new File(profileDir);
	}

	@Override
	public Collection<String> getProjectsForCustomer(String customerName) {
		HashSet<String> projectNames = new HashSet<String>();
		List<File> profileFilesList = readProfileFilesList();

		for (File file : profileFilesList) {
			try {
				ProfileImpl profile = new ProfileFileReader(file)
						.readProfileFromFile();
				String[][] projects = profile.getProjects();
				for (int i = 0; i < projects.length; i++) {
					String[] project = projects[i];
					if (project[0].equals(customerName)) {
						projectNames.add(project[1]);
					}
				}
			} catch (FileNotFoundException ex) {
				// add nothing to the list
			} catch (IOException ex) {
				// add nothing to the list
			}
		}
		return projectNames;
	}

	@Override
	public Collection<Profile> getProfilesForName(String employeeName) {
		ArrayList<Profile> profileList = new ArrayList<Profile>();
		List<File> profileFilesList = readProfileFilesList();

		for (File file : profileFilesList) {
			try {
				ProfileImpl profile = new ProfileFileReader(file)
						.readProfileFromFile();
				if (profile.getEmployeeName().equals(employeeName)) {
					profileList.add(profile);
				}
			} catch (FileNotFoundException ex) {
				// add nothing to the list
			} catch (IOException ex) {
				// add nothing to the list
			}
		}
		return profileList;
	}

	@Override
	public void setProfileDir(String directoryName) {
		this.profileDir = new File(directoryName);
	}

	@Override
	public String getProfileDir() {
		return this.profileDir.getPath();
	}

	@Override
	public Collection<Profile> getProfilesForSkill(String skill,
			SkillLevel level) {
		ArrayList<Profile> profileList = new ArrayList<Profile>();
		String levelString = getLevelRepresentation(level);

		List<File> profileFilesList = readProfileFilesList();

		for (File file : profileFilesList) {
			try {
				ProfileImpl profileImpl = new ProfileFileReader(file)
						.readProfileFromFile();
				String[] skills = profileImpl.getSkills();
				for (String profileSkill : skills) {
					for (int i = 0; i < levelString.length(); i++) {
						String skillString = levelString.charAt(i) + " "
								+ skill;
						if (skillString.equals(profileSkill)) {
							profileList.add(profileImpl);
						}
					}
				}
			} catch (FileNotFoundException ex) {
				// add nothing to the list
			} catch (IOException ex) {
				// add nothing to the list
			}
		}
		return profileList;
	}

	@Override
	public boolean validateProfile(Profile profile) {
		ProfileImpl profileImpl = null;
		if (profile instanceof ProfileImpl) {
			profileImpl = (ProfileImpl) profile;
		} else {
			profileImpl = (ProfileImpl) getProfilesForID(profile
					.getEmployeeId());
		}
		return profileImpl.validate();
	}

	private List<File> readProfileFilesList() {
		File[] listFiles = profileDir.listFiles(new ProfileFileFileFilter());
		ArrayList<File> profileFilesList = new ArrayList<File>();

		for (int i = 0; i < listFiles.length; i++) {
			profileFilesList.add(listFiles[i]);
		}

		return profileFilesList;
	}

	@Override
	public Profile getProfilesForID(int employeeId) {
		Profile profile = null;
		List<File> profileFilesList = readProfileFilesList();

		for (File file : profileFilesList) {
			try {
				ProfileImpl profileImpl = new ProfileFileReader(file)
						.readProfileFromFile();
				if (profileImpl.getEmployeeId() == employeeId) {
					return profileImpl;
				}
			} catch (FileNotFoundException ex) {
				// leave profile as null
			} catch (IOException ex) {
				// leave profile as null
			}
		}
		return profile;
	}

	private String getLevelRepresentation(SkillLevel skillLevel) {
		String levels = "";
		switch (skillLevel) {
		default:
			break;
		case BEGINNER:
			levels += "B";
		case EXPERIENCED:
			levels += "E";
		case INTERMEDIATE:
			levels += "F";
		case PROFESSIONAL:
			levels += "P";
		}
		return levels;
	}

	@Override
	public Collection<Profile> getProfilesForProjectAndDate(
			String customerName, String projectName, Date date) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
