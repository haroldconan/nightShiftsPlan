package com.harold.conan.nightShiftsPlan.windows;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MainWindows {

	protected Shell shlNightShiftsPlanning;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindows window = new MainWindows();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlNightShiftsPlanning.open();
		shlNightShiftsPlanning.layout();
		while (!shlNightShiftsPlanning.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlNightShiftsPlanning = new Shell();
		shlNightShiftsPlanning.setSize(1150, 845);
		shlNightShiftsPlanning.setText("Night shifts planning");

	}

}
