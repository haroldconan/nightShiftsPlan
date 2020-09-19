package com.harold.conan.nightShiftsPlan.entities;

import com.harold.conan.nightShiftsPlan.Year;

public class Student extends ThirdParty{

	private Model modelInterShips;
	
	private Model modelNightShips;
	
	private Year studentYear;
	
	private int interShipNumber;

	private int nightShipNumber;
	
	private String studentNumber;



	public Student(String firstName, String lastName,Model modelInterShips,Model modelNightShips,Year studentYear,String studentNumber) {
		super(firstName, lastName);
		setModelInterShips(modelInterShips);
		setStudentYear(studentYear);
		setStudentNumber(studentNumber);
	}


	
	/**
	 * @return the model
	 */
	public Model getModelInterShips() {
		return modelInterShips;
	}

	/**
	 * @param model the model to set
	 */
	public void setModelInterShips(Model modelInterShips) {
		this.modelInterShips = modelInterShips;
	}
	/**
	 * @return the modelNightShips
	 */
	public Model getModelNightShips() {
		return modelNightShips;
	}



	/**
	 * @param modelNightShips the modelNightShips to set
	 */
	public void setModelNightShips(Model modelNightShips) {
		this.modelNightShips = modelNightShips;
	}
	/**
	 * @return the studentYear
	 */
	public Year getStudentYear() {
		return studentYear;
	}

	/**
	 * @param studentYear the studentYear to set
	 */
	public void setStudentYear(Year studentYear) {
		this.studentYear = studentYear;
	}
	/**
	 * @return the interShipNumber
	 */
	public int getInterShipNumber() {
		return interShipNumber;
	}



	/**
	 * @param interShipNumber the interShipNumber to set
	 */
	public void setInterShipNumber(int interShipNumber) {
		this.interShipNumber = interShipNumber;
	}



	/**
	 * @return the nightShipNumber
	 */
	public int getNightShipNumber() {
		return nightShipNumber;
	}



	/**
	 * @param nightShipNumber the nightShipNumber to set
	 */
	public void setNightShipNumber(int nightShipNumber) {
		this.nightShipNumber = nightShipNumber;
	}
	

	/**
	 * @return the studentNumber
	 */
	public String getStudentNumber() {
		return studentNumber;
	}



	/**
	 * @param studentNumber the studentNumber to set
	 */
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}


	

}
