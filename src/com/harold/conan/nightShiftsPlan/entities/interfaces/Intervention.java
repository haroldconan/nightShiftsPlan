package com.harold.conan.nightShiftsPlan.entities.interfaces;

public abstract class Intervention {

	private int studentNumberMax,studentNumbreMin;

	/**
	 * @return the studentNumberMax
	 */
	public int getStudentNumberMax() {
		return studentNumberMax;
	}

	/**
	 * @param studentNumberMax the studentNumberMax to set
	 */
	public void setStudentNumberMax(int studentNumberMax) {
		this.studentNumberMax = studentNumberMax;
	}

	/**
	 * @return the studentNumbreMin
	 */
	public int getStudentNumbreMin() {
		return studentNumbreMin;
	}

	/**
	 * @param studentNumbreMin the studentNumbreMin to set
	 */
	public void setStudentNumbreMin(int studentNumbreMin) {
		this.studentNumbreMin = studentNumbreMin;
	}
	
}
