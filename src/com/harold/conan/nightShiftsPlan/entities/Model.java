package com.harold.conan.nightShiftsPlan.entities;

import java.util.Map;

import com.harold.conan.nightShiftsPlan.entities.interfaces.Intervention;
import com.harold.conan.nightShiftsPlan.enumeration.Month;

public class Model {

	private String CodeModel;
	
	private Map<Month, Intervention> yearPlan;

	/**
	 * @return the codeModel
	 */
	public String getCodeModel() {
		return CodeModel;
	}

	/**
	 * @param codeModel the codeModel to set
	 */
	public void setCodeModel(String codeModel) {
		CodeModel = codeModel;
	}

	/**
	 * @return the yearPlan
	 */
	public Map<Month, Intervention> getYearPlan() {
		return yearPlan;
	}

	/**
	 * @param yearPlan the yearPlan to set
	 */
	public void setYearPlan(Map<Month, Intervention> yearPlan) {
		this.yearPlan = yearPlan;
	}

	public Model(String codeModel, Map<Month, Intervention> yearPlan) {
		super();
		CodeModel = codeModel;
		this.yearPlan = yearPlan;
	}
	
	
}
