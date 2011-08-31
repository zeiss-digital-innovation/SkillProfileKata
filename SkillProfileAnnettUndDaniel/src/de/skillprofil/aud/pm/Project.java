package de.skillprofil.aud.pm;

import java.util.Date;
import java.util.List;

public class Project {
	private String customer;
	private String name;
	private Date begin;
	private Date end;
	private List<String> skillNames;
	
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCustomer() {
		return customer;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getBegin() {
		return begin;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public Date getEnd() {
		return end;
	}
	public void setSkillNames(List<String> skillNames) {
		this.skillNames = skillNames;
	}
	public List<String> getSkillNames() {
		return skillNames;
	}
}
