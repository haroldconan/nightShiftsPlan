package com.harold.conan.nightShiftsPlan;

import com.harold.conan.nightShiftsPlan.entities.Group;

public class Year {

	private Group group;
	
	private String Name;

	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	public Year(Group group, String name) {
		super();
		this.group = group;
		Name = name;
	}
	
	
	
}
