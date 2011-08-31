package de.saxsys.dojo.skillprofile.rd.business.model;


public class Project {

	private String customerName;
	private String projectName;
	private String period;
	private String[] skillNames;
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getPeriod() {
		return period;
	}
	
	public void setPeriod(String period) {
		this.period = period;
	}
	
	public String[] getSkillNames() {
		return skillNames;
	}
	
	public void setSkillNames(String[] skillNames) {
		this.skillNames = skillNames;
	}
	
}
