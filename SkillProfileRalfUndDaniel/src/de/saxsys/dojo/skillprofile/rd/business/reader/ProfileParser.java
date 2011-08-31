package de.saxsys.dojo.skillprofile.rd.business.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.saxsys.dojo.skillprofile.business.SkillLevel;
import de.saxsys.dojo.skillprofile.rd.business.ProfileImpl;
import de.saxsys.dojo.skillprofile.rd.business.model.Project;
import de.saxsys.dojo.skillprofile.rd.business.model.Skill;

public class ProfileParser {

	private File profileFile;
	private BufferedReader bufReader;
	private ProfileImpl profile;

	public ProfileParser(File profileFile) {
		this.profileFile = profileFile;
		profile = new ProfileImpl();
	}
	
	public ProfileImpl parseFile() {
		
		try {
			bufReader = new BufferedReader(new FileReader(profileFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		parseEmployeeId();
		parseName();
		parseSkills();
		parseProjects();
		
		return profile;
	}

	private void parseEmployeeId() {
		try {
			profile.setEmployeeId(Integer.parseInt(bufReader.readLine()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void parseName() {
		try {
			profile.setName(bufReader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void parseSkills() {
		try {
			List<Skill> skills = new ArrayList<Skill>();
			
			String line = bufReader.readLine();
			while(!line.equals("[Projekterfahrung]")) {
				
				if(!line.equals("[Skills]")) {
					Skill skill = new Skill();
					skill.setLevel(getSkillLevel(line.substring(0, 1)));
					skill.setName(line.substring(2));
					skills.add(skill);
				}
				line = bufReader.readLine();
			}
			profile.setSkills(skills);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void parseProjects() {
		try {
			List<Project> projects = new ArrayList<Project>();
			
			String line = bufReader.readLine();
			while(line != null) {
				
				Project project = new Project();
				project.setCustomerName(line);
				project.setProjectName(bufReader.readLine());
				project.setPeriod(bufReader.readLine());
				
				project.setSkillNames(bufReader.readLine().split(", "));
				projects.add(project);
				
				line = bufReader.readLine();
			}
			profile.setProjects(projects);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private SkillLevel getSkillLevel(String string) {
			if (string.equals("B")) {
				return SkillLevel.BEGINNER;
			} else if (string.equals("E")) {
				return SkillLevel.EXPERIENCED;
			} else if (string.equals("F")) {
				return SkillLevel.INTERMEDIATE;
			} else {
				return SkillLevel.PROFESSIONAL;
			}
			
	}
	
}
