package de.skillprofil.aud.pm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.saxsys.dojo.skillprofile.business.Profile;
import de.saxsys.dojo.skillprofile.business.SkillLevel;

public class ProfileImpl implements Profile{

	private int employeeId;
	private String mitarbeiterName;
	private List<Skill> skills;
	private List<Project> projects;
	
	public ProfileImpl() {
		skills = new ArrayList<Skill>();
		projects = new ArrayList<Project>();
	}
	
	@Override
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getMitarbeiterName() {
		return mitarbeiterName;
	}

	public void setMitarbeiterName(String mitarbeiterName) {
		this.mitarbeiterName = mitarbeiterName;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + employeeId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfileImpl other = (ProfileImpl) obj;
		if (employeeId != other.employeeId)
			return false;
		return true;
	}

	public void addSkill(String nextSkillLine) {
		String[] splittedSkill = nextSkillLine.split(" ", 2);
		if(splittedSkill.length == 2) {
			Skill skill = new Skill();
			String level = splittedSkill[0];
			if(level.equals("P")) {
				skill.setLevel(SkillLevel.PROFESSIONAL);
			} else if (level.equals("E")) {
				skill.setLevel(SkillLevel.EXPERIENCED);
			} else if (level.equals("F")) {
				skill.setLevel(SkillLevel.INTERMEDIATE);
			} else if (level.equals("B")) {
				skill.setLevel(SkillLevel.BEGINNER);
			} else {
				return;
			}
			skill.setName(splittedSkill[1]);
			skills.add(skill);
		}
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void addProject(List<String> projectLines) {
		Project project = new Project();
		project.setCustomer(projectLines.get(0));
		project.setName(projectLines.get(1));
		
		String[] dates = projectLines.get(2).split(" - ");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
		Date begin;
		Date end;
		try {
			 begin = sdf.parse(dates[0]);
			 end = sdf.parse(dates[1]);
			 project.setBegin(begin);
			 GregorianCalendar cal = new GregorianCalendar();
			 cal.setTime(end);
			 cal.add(Calendar.MONTH, 1);
			 project.setEnd(cal.getTime());
		} catch (ParseException e) {
			System.out.println("Fehler: " + e.getLocalizedMessage());
			return;
		}
		String[] skillNames = projectLines.get(3).split(", ");
		List<String> skills = new ArrayList<String>();
		for (String skillName : skillNames) {
			skills.add(skillName);
		}
		project.setSkillNames(skills);
		projects.add(project);
	}

}
