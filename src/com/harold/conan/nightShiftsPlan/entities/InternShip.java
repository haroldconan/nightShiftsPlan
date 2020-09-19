package com.harold.conan.nightShiftsPlan.entities;

import com.harold.conan.nightShiftsPlan.entities.interfaces.Intervention;

public class InternShip extends Intervention{

	private String internShipName;

	/**
	 * @return the internShipName
	 */
	public String getInternShipName() {
		return internShipName;
	}

	/**
	 * @param internShipName the internShipName to set
	 */
	public void setInternShipName(String internShipName) {
		this.internShipName = internShipName;
	}

	public InternShip(String internShipName) {
		super();
		this.internShipName = internShipName;
	}
	
	
	
}
