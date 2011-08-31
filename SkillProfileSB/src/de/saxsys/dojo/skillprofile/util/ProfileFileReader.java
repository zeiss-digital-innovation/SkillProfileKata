package de.saxsys.dojo.skillprofile.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import de.saxsys.dojo.skillprofile.business.ProfileImpl;

/**
 * 
 * @author marco.dierenfeldt
 */
public class ProfileFileReader {
	private static final String SKILLS_MARKER = "[Skills]";
	private static final String PROJECTS_MARKER = "[Projekterfahrung]";
	private static final String PROJECT_SKILL_SEPERATOR_CHAR = ",";

	private File profileFile;

	public ProfileFileReader(File profileFile) {
		this.profileFile = profileFile;
	}

	public ProfileImpl readProfileFromFile() throws FileNotFoundException,
			IOException {
		ProfileImpl profile = null;
		RandomAccessFile raf = new RandomAccessFile(profileFile, "r");

		String employeeNumber = raf.readLine();
		String employeename = raf.readLine();
		if (!raf.readLine().equals(SKILLS_MARKER)) {
			throw new IllegalStateException("format of profile" + profileFile
					+ " is not correct");
		}
		ArrayList<String> skills = new ArrayList<String>();

		String currentSkill = raf.readLine();
		while (!currentSkill.trim().equals(PROJECTS_MARKER)) {
			skills.add(currentSkill);
			currentSkill = raf.readLine();
		}
		ArrayList<String[]> projects = new ArrayList<String[]>();
		String currentLine = raf.readLine();
		while (currentLine != null) {
			String[] project = new String[4];
			project[0] = currentLine;
			project[1] = raf.readLine();
			project[2] = raf.readLine();
			project[3] = raf.readLine();

			projects.add(project);
			currentLine = raf.readLine();
		}

		profile = new ProfileImpl();
		profile.setEmployeeNumber(Integer.parseInt(employeeNumber));
		profile.setEmployeeName(employeename);
		profile.setSkills(skills.toArray(new String[0]));
		profile.setProjects(projects.toArray(new String[0][0]));
		raf.close();
		return profile;
	}

}
