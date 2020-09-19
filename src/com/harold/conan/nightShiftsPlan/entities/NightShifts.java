package com.harold.conan.nightShiftsPlan.entities;

import com.harold.conan.nightShiftsPlan.entities.interfaces.Intervention;
import com.harold.conan.nightShiftsPlan.enumeration.InternShip;

public class NightShifts extends Intervention{

	private InternShip department;
	
	private String schedulePublicHolidays,schedule;
	
	private Boolean restDay;

	/**
	 * @return the department
	 */
	public InternShip getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(InternShip department) {
		this.department = department;
	}

	/**
	 * @return the schedulePublicHolidays
	 */
	public String getSchedulePublicHolidays() {
		return schedulePublicHolidays;
	}

	/**
	 * @param schedulePublicHolidays the schedulePublicHolidays to set
	 */
	public void setSchedulePublicHolidays(String schedulePublicHolidays) {
		this.schedulePublicHolidays = schedulePublicHolidays;
	}

	/**
	 * @return the schedule
	 */
	public String getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	/**
	 * @return the restDay
	 */
	public Boolean getRestDay() {
		return restDay;
	}

	/**
	 * @param restDay the restDay to set
	 */
	public void setRestDay(Boolean restDay) {
		this.restDay = restDay;
	}

	public NightShifts(InternShip department, String schedulePublicHolidays, String schedule, Boolean restDay) {
		super();
		this.department = department;
		this.schedulePublicHolidays = schedulePublicHolidays;
		this.schedule = schedule;
		this.restDay = restDay;
	}
	
	
	
}
