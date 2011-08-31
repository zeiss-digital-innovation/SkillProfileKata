package de.skillprofil.aud.pm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.ProfileManager;
import de.saxsys.dojo.skillprofile.business.SkillLevel;

public class DaProfileManagerImpl implements ProfileManager{

	private static final String PROJEKTERFAHRUNG_LINE = "[Projekterfahrung]";
	private static final String SKILLS_LINE = "[Skills]";
	private String profileDirectory;
	private List<Profile> profiles;
	
	@Override
	public void setProfileDir(String directoryName) {
		if(!directoryName.endsWith("\\")){
			directoryName += "\\";
		}
		profileDirectory = directoryName;
	}

	@Override
	public String getProfileDir() {
		return profileDirectory;
	}

	@Override
	public Profile getProfilesForID(int employeeId) {
		init();
		for(Profile profile : profiles) {
			if(employeeId == profile.getEmployeeId())
				return profile;
		}
		return null;
	}

	@Override
	public Collection<Profile> getProfilesForName(String employeeName) {
		init();
		Collection<Profile> profilesByName = new ArrayList<Profile>();
		for (Profile profile : profiles){
			ProfileImpl profileImpl = (ProfileImpl) profile;
			if (profileImpl.getMitarbeiterName().equals(employeeName)){
				profilesByName.add(profile);
			}
		}
		return profilesByName;
	}

	@Override
	public Collection<Profile> getProfilesForSkill(String skillName,
			SkillLevel level) {
		init();
		Collection<Profile> profilesBySkill = new HashSet<Profile>();
		for (Profile profile : profiles){
			ProfileImpl profileImpl = (ProfileImpl) profile;
			List<Skill> skills = profileImpl.getSkills();
			for (Skill skill : skills) {
				if (skill.getLevel().equals(level) && 
						skill.getName().equals(skillName)){
					profilesBySkill.add(profile);
				}
			}
		}
		return profilesBySkill;	
	}

	@Override
	public Collection<Profile> getProfilesForProjectAndDate(
			String customerName, String projectName, Date date) {
		init();
		Collection<Profile> profilesByProjectAndCustomer = new ArrayList<Profile>();
		for (Profile profile : profiles){
			ProfileImpl profileImpl = (ProfileImpl) profile;
			for(Project project : profileImpl.getProjects()) {
				if(project.getCustomer().equals(customerName) &&
						!project.getBegin().after(date) &&
						project.getEnd().after(date) &&
						project.getName().equals(projectName)) {
					profilesByProjectAndCustomer.add(profile);
				}
			}
		}
		return profilesByProjectAndCustomer;
	}

	@Override
	public Collection<String> getProjectsForCustomer(String customerName) {
		init();
		Collection<String> projectsByCustomer = new HashSet<String>();
		for (Profile profile : profiles){
			ProfileImpl profileImpl = (ProfileImpl) profile;
			for(Project project:profileImpl.getProjects()) {
				if(project.getCustomer().equals(customerName)){
					projectsByCustomer.add(project.getName());
				}
			}
		}
		return projectsByCustomer;
	}

	@Override
	public boolean validateProfile(Profile profile) {
		init();
		Set<String> availableSkills = new HashSet<String>();
		for(Skill skill: ((ProfileImpl)profile).getSkills()) {
			availableSkills.add(skill.getName());
		}
		
		for(Project project : ((ProfileImpl)profile).getProjects()) {
			for(String skillName : project.getSkillNames()){
				if(!availableSkills.contains(skillName)) {
					return false;
				}
			}
		}
		
		return true;
	}

	private void init() {
		if(profiles==null){
			parseProfiles();
		}
	}
	
	public List<File> getProfileFiles() {
		
		File dir = new File(getProfileDir());
		String[] files = dir.list();
		
		List<File> profileList = new ArrayList<File>();
		for(String file : files){
			if(file.endsWith(".profile")) {
				profileList.add(new File(getProfileDir() + file));
			}
		}
		return profileList;
	}

	public void parseProfiles() {
		List<File> profileFiles = getProfileFiles();
		profiles = new ArrayList<Profile>();
		
		for(File file : profileFiles) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				ProfileImpl profile = new ProfileImpl();
				
				profile.setEmployeeId(Integer.parseInt(br.readLine()));
				profile.setMitarbeiterName(br.readLine());
				
				if(br.readLine().equals(SKILLS_LINE)){
					String nextSkillLine = br.readLine();
					while (!nextSkillLine.equals(PROJEKTERFAHRUNG_LINE)) {
						profile.addSkill(nextSkillLine);
						nextSkillLine = br.readLine();
					} 
				}
				
				String projectLine = br.readLine();
				while (projectLine != null) {
					List<String> projectLines = new ArrayList<String>();
					for (int i=0;i<4;i++) {
						projectLines.add(projectLine);
						projectLine = br.readLine();
					}
					profile.addProject(projectLines);
				}
				
				profiles.add(profile);
			}
			catch (FileNotFoundException foe) {
				System.out.println("Fehler: " + foe.getLocalizedMessage()); 
			}
			catch (IOException ioe) {
				System.out.println("Fehler: " + ioe.getLocalizedMessage());
			}
		}
	}
}
