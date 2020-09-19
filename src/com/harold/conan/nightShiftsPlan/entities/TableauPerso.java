package com.harold.conan.nightShiftsPlan.entities;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.osgi.framework.util.ArrayMap;

public class TableauPerso extends ArrayMap<String, ArrayList<String>>{
	
	public TableauPerso(List<String> keys, List<ArrayList<String>> values) {
		super(keys, values);
		// TODO Auto-generated constructor stub
	}

	private List<String> pageName;
	
	private List<ArrayList<String>> table;
	/**
	 * @return the pageName
	 */
	public List<String> getPageName() {
		return pageName;
	}

	/**
	 * @param pageName the pageName to set
	 */
	public void setPageName(List<String> pageName) {
		this.pageName = pageName;
	}

	/**
	 * @return the table
	 */
	public List<ArrayList<String>> getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(List<ArrayList<String>> table) {
		this.table = table;
	}

	

	
}
