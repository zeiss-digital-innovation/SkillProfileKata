package de.saxsys.dojo.skillprofile.business.impl.domain;

import java.util.Collection;

public class Project {

	String projectName;
	String customerName;
	Period period;
	Collection<Technology> techs;

	public Project() {
	}

	public Project(String projectName, String customerName, Period period,
			Collection<Technology> techs) {
		this.projectName = projectName;
		this.customerName = customerName;
		this.period = period;
		this.techs = techs;

	}

	public String getProjectName() {
		return projectName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Period getProjectPeriod() {
		return period;
	}

	public Collection<Technology> getTechnologies() {
		return techs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((period == null) ? 0 : period.hashCode());
		result = prime * result
				+ ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result + ((techs == null) ? 0 : techs.hashCode());
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
		Project other = (Project) obj;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (period == null) {
			if (other.period != null)
				return false;
		} else if (!period.equals(other.period))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (techs == null) {
			if (other.techs != null)
				return false;
		} else if (!techs.equals(other.techs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project[" + customerName + "," + projectName + "," + period
				+ "," + techs + "]";
	}

}
