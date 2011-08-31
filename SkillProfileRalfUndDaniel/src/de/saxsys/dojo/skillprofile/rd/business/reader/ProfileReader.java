package de.saxsys.dojo.skillprofile.rd.business.reader;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import de.saxsys.dojo.skillprofile.rd.business.ProfileImpl;

public class ProfileReader {

	private File profileFolder;

	public ProfileReader(String pathToProfileFolder) {
		this.profileFolder = new File(pathToProfileFolder);
	}

	public List<ProfileImpl> readProfiles() {

		File[] profileFiles = getProfileFiles();
		List<ProfileImpl> profiles = new ArrayList<ProfileImpl>();

		for (File profileFile : profileFiles) {
			ProfileParser parser = new ProfileParser(profileFile);
			profiles.add(parser.parseFile());
		}

		return profiles;
	}

	private File[] getProfileFiles() {
		return profileFolder.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File file, String name) {
				
				return name.matches(".*\\.profile");
			}
		});
	}

}
