package com.harold.conan.nightShiftsPlan.services;

import java.util.ArrayList;

import org.eclipse.osgi.framework.util.ArrayMap;

public interface ReadTableService {

	public ArrayMap<String, ArrayList<ArrayList<String>>> readeTabler(String dir);
	
}
