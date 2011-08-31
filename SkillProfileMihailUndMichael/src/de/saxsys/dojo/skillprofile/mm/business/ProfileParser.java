package de.saxsys.dojo.skillprofile.mm.business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.saxsys.dojo.skillprofile.business.SkillLevel;

public class ProfileParser {

	private static final String PROJECT_HEADER = "[Projekterfahrung]";

	public static ProfileImpl parse(String pathName)
			throws NumberFormatException, IOException {
		BufferedReader r = new BufferedReader(new FileReader(pathName));
		ProfileImpl profile = new ProfileImpl();

		profile.setEmployeeId(Integer.parseInt(r.readLine()));
		profile.setEmployeeName(r.readLine());

		r.readLine(); // skip skill header

		String line;
		Map<String, SkillLevel> skills = new HashMap<String, SkillLevel>();
		line = r.readLine();
		while ((line != null) && !line.equals(PROJECT_HEADER)) {
			skills.put(line.substring(2), getSkillLevel(line.charAt(0)));
			line = r.readLine();
		}
		profile.setSkills(skills);

		// project header is already skipped here

		List<Project> projects = new ArrayList<Project>();
		line = r.readLine();
		while (line != null) {
			Project p = new Project();
			p.setCustomer(line);
			p.setProjectName(r.readLine());

			line = r.readLine();
			p.setStart(getDate(line.substring(0, 5), true));
			p.setEnd(getDate(line.substring(8), false));

			line = r.readLine();
			String[] projectSkills = line.split(",");
			p.setSkills(Arrays.asList(trimStrings(projectSkills)));

			projects.add(p);

			line = r.readLine();
		}

		profile.setProjects(projects);

		return profile;
	}

	private static Date getDate(String date, boolean firstDateOfMonth) {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, Integer.parseInt(date.substring(3, 5)) + 2000); // we
																				// assume
																				// all
																				// years
																				// are
																				// after
																				// 2000!
		cal.set(Calendar.MONTH, Integer.parseInt(date.substring(0, 2)) - 1); // we
																				// subtract
																				// 1
																				// since
																				// calendar
																				// uses
																				// 0-based
																				// months
		cal.set(Calendar.DAY_OF_MONTH, firstDateOfMonth ? 1 : 31); // calendar
																	// sets the
																	// date
																	// automatically
																	// to the
																	// last
																	// valid
																	// date of
																	// the month
		cal.set(Calendar.HOUR_OF_DAY, firstDateOfMonth ? 0 : 23);
		cal.set(Calendar.MINUTE, firstDateOfMonth ? 0 : 59);
		cal.set(Calendar.SECOND, firstDateOfMonth ? 0 : 59);

		return cal.getTime();

	}

	private static String[] trimStrings(String[] strings) {
		for (int i = 0; i < strings.length; i++) {
			strings[i] = strings[i].trim();
		}

		return strings;
	}

	private static SkillLevel getSkillLevel(char c) {
		switch (c) {
		case 'B':
			return SkillLevel.BEGINNER;
		case 'E':
			return SkillLevel.EXPERIENCED;
		case 'F':
			return SkillLevel.INTERMEDIATE;
		case 'P':
			return SkillLevel.PROFESSIONAL;
		default:
			throw new IllegalArgumentException("Unknown skill level: " + c);
		}
	}

}
