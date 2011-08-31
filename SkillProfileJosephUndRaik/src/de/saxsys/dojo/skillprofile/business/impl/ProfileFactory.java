package de.saxsys.dojo.skillprofile.business.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.saxsys.dojo.skillprofile.business.SkillLevel;
import de.saxsys.dojo.skillprofile.business.impl.domain.Period;
import de.saxsys.dojo.skillprofile.business.impl.domain.Project;
import de.saxsys.dojo.skillprofile.business.impl.domain.Skill;
import de.saxsys.dojo.skillprofile.business.impl.domain.Technology;

public class ProfileFactory {

	public Period parsePeriod(String period) {
		Period p = new Period();

		Pattern pattern = Pattern
				.compile("([0-9][1-9])/([0-9]{2})\\s*-\\s*([0-9][1-9])/([0-9]{2})");
		Matcher matcher = pattern.matcher(period);

		if (!matcher.matches())
			return null;

		int startMonth = Integer.parseInt(matcher.group(1));
		int startYear = Integer.parseInt(matcher.group(2));
		int endMonth = Integer.parseInt(matcher.group(3));
		int endYear = Integer.parseInt(matcher.group(4));

		p.setStart(getDate(startMonth, startYear));
		p.setEnd(getDate(endMonth, endYear));

		return p;
	}

	private Date getDate(int month, int year) {
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MONTH, month);
		// FIXME
		date.set(Calendar.YEAR, 2000 + year);

		return date.getTime();
	}

	public Collection<Technology> parseTechnologies(String string) {

		Collection<Technology> result = new ArrayList<Technology>();

		if ("".equals(string.trim())) {
			return result;
		}

		for (String technologyName : string.split(",")) {
			if ("".equals(technologyName.trim()))
				continue;
			result.add(new Technology(technologyName.trim()));
		}

		return result;
	}

	public Project parseProject(String project) {
		String[] lines = project.split("\n");
		if (lines.length != 4)
			return null;

		Period period = parsePeriod(lines[2]);
		Collection<Technology> technologies = parseTechnologies(lines[3]);

		if (period == null || technologies == null)
			return null;

		return new Project(lines[1], lines[0], period, technologies);
	}

	public Collection<Skill> parseSkills(String skills) {
		Collection<Skill> result = new ArrayList<Skill>();
		for (String line : skills.split("\n")) {
			Skill skill = parseSkill(line);

			if (skill == null)
				return null;

			result.add(skill);
		}
		return result;
	}

	private Skill parseSkill(String line) {
		Pattern p = Pattern.compile("([PEFB]) (.*)");
		Matcher m = p.matcher(line);
		if (!m.matches())
			return null;

		String levelString = m.group(1);
		SkillLevel level = null;
		if (levelString.equals("B"))
			level = SkillLevel.BEGINNER;
		else if (levelString.equals("E"))
			level = SkillLevel.EXPERIENCED;
		else if (levelString.equals("F"))
			level = SkillLevel.INTERMEDIATE;
		else if (levelString.equals("P"))
			level = SkillLevel.PROFESSIONAL;

		return new Skill(level, new Technology(m.group(2)));
	}
}
