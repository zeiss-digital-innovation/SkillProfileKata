package de.saxsys.dojo.skillprofile.business;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author marco.dierenfeldt
 */
public class ProfileImpl implements Profile{
    private int employeeNumber;
    private String employeeName;
    private String[] skills;
    private String[][] projects;

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeename) {
        this.employeeName = employeename;
    }

    public String[][] getProjects() {
        return projects;
    }

    public void setProjects(String[][] projekts) {
        this.projects = projekts;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Profile{");
        sb.append("employeeNumber=" );
        sb.append(employeeNumber);
        sb.append(", employeename=");
        sb.append(employeeName);
        sb.append("Skills[");
        for (String string : skills) {
            sb.append(string);
            sb.append(",");
        }
        sb.append("]");
        for (int i = 0; i < projects.length; i++) {
            String[] projekt = projects[i];
            sb.append("Projects[");
            for (String line : projekt) {
                sb.append(line);
                sb.append(",");
            }
            sb.append("]");
        }
        return sb.toString();
    }

    @Override
    public int getEmployeeId() {
        
        return employeeNumber;
    }
    
    public boolean validate(){
        boolean valid = false;
        int invalidityCounter = 0;
        HashSet<String> projectSkillHash = generateProjectSkillHash();
        HashSet<String> profileSkillHash = generateProfileSkillHash();
        for (String projectSkill : projectSkillHash) {
            if(!profileSkillHash.contains(projectSkill)){
                invalidityCounter++;
            }
        }
        valid = (invalidityCounter==0)?true:false;
        return valid;
    }

    private HashSet<String> generateProjectSkillHash() {
        HashSet<String> projectSkills = new HashSet<String>();
        for (String[] project : projects) {
            String[] skills = project[3].split(",");
            for (String string : skills) {
                projectSkills.add(string.trim());
            }
        }
        return projectSkills;
    }

    private HashSet<String> generateProfileSkillHash() {
        HashSet<String> skillHash = new HashSet<String>();
        for (String skill : skills) {
            skillHash.add(skill.substring(2));
        }
        return skillHash;
    }
}
