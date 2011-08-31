package de.saxsys.dojo.skillprofile.sk.technical;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import de.saxsys.dojo.skillprofile.business.SkillLevel;
import de.saxsys.dojo.skillprofile.sk.business.BavariaSystemsProfile;
import de.saxsys.dojo.skillprofile.sk.business.ProjectExperience;
import de.saxsys.dojo.skillprofile.sk.business.Skill;

public class FileSystemParser {

    public List<BavariaSystemsProfile> getProfilesFor(final String directory) {
        final List<BavariaSystemsProfile> result = new ArrayList<BavariaSystemsProfile>();
        final File dir = new File(directory);
        for (final File file : dir.listFiles()) {
            final BavariaSystemsProfile profile = parseProfile(file);
            if (null != profile) {
                result.add(profile);
            }
        }
        return result;
    }

    private BavariaSystemsProfile parseProfile(final File file) {
        BavariaSystemsProfile result = null;
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(file));
            try {
                result = readProfile(reader);
            } finally {
                reader.close();
            }
        } catch (final IOException exception) {
            // ignored because unparsable profiles are skipped
        }
        return result;
    }

    private BavariaSystemsProfile readProfile(final BufferedReader reader) throws IOException {
        final int employeeId = Integer.parseInt(reader.readLine());
        final String employeeName = reader.readLine();
        // needs to be cleaned up
        reader.readLine(); // [Skill]
        String line = null;
        final Collection<Skill> profileSkills = new ArrayList<Skill>();
        while (!"[Projekterfahrung]".equals(line = reader.readLine())) {
            profileSkills.add(parseSkill(line));
        }
        final Collection<ProjectExperience> projectExperiences = new ArrayList<ProjectExperience>();
        ProjectExperience experience;
        while (null != (experience = parseProjectExperience(reader))) {
            projectExperiences.add(experience);
        }
        return new BavariaSystemsProfile(employeeId, employeeName, profileSkills,
                        projectExperiences);
    }

    private ProjectExperience parseProjectExperience(final BufferedReader reader)
                    throws IOException {
        ProjectExperience result = null;
        final String company = reader.readLine();
        if (null != company) {
            final String project = reader.readLine();
            final String time = reader.readLine();
            final Collection<String> usedSkills = Arrays.asList(reader.readLine().split(", "));
            result = new ProjectExperience(company, project, time.substring(0, 5),
                            time.substring(8), usedSkills);
        }
        return result;
    }

    private Skill parseSkill(final String line) throws IOException {
        final char levelChar = line.charAt(0);
        SkillLevel level;
        switch (levelChar) {
        case 'B':
            level = SkillLevel.BEGINNER;
            break;
        case 'E':
            level = SkillLevel.EXPERIENCED;
            break;
        case 'F':
            level = SkillLevel.INTERMEDIATE;
            break;
        case 'P':
            level = SkillLevel.PROFESSIONAL;
            break;
        default:
            // DIRTY: using this exception
            throw new IOException("Unparsable skill level: " + levelChar);
        }
        final String skillname = line.substring(2);
        return new Skill(level, skillname);
    }
}
