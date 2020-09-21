package com.harold.conan.nightShiftsPlan.windows;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.osgi.framework.util.ArrayMap;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import com.harold.conan.nightShiftsPlan.services.ReadTableService;
import com.harold.conan.nightShiftsPlan.services.impl.ReadTableServiceImpl;

public class MainWindows {

	protected Shell shlNightShiftsPlanning;
	private Menu menu;
	private Menu menuFichier;
	private MenuItem optionFichier;
	private MenuItem optionOuvrir;
	private MenuItem optionFermer;
	private MenuItem optionAide;

	private ReadTableService readTableService;
	private ArrayMap<String, ArrayList<ArrayList<String>>> tableOfTable = null;
	private TableViewer tableViewer = null;
	private Combo combo;

	/**
	 * Launch the application.
	 * 
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
			tableViewer.getTable().setBounds(205, 10, shlNightShiftsPlanning.getBounds().width-230, shlNightShiftsPlanning.getBounds().height-110);
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * 
	 * @param shlNightShiftsPlanning2
	 */
	protected void createContents() {
		readTableService = new ReadTableServiceImpl();

		shlNightShiftsPlanning = new Shell();
		shlNightShiftsPlanning.setBackground(SWTResourceManager.getColor(SWT.COLOR_DARK_GRAY));
		shlNightShiftsPlanning.setSize(1150, 845);
		shlNightShiftsPlanning.setText("Night shifts planning");
		menu = new Menu(shlNightShiftsPlanning, SWT.BAR);
		optionFichier = new MenuItem(menu, SWT.CASCADE);
		optionFichier.setText("Fichier");
		menuFichier = new Menu(shlNightShiftsPlanning, SWT.DROP_DOWN);
		optionOuvrir = new MenuItem(menuFichier, SWT.PUSH);
		optionOuvrir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dialog = new FileDialog(shlNightShiftsPlanning);

				String filePath = dialog.open();

				if (filePath != null) {
					tableOfTable = null;
					tableOfTable = readTableService.readeTabler(filePath);
					for (String key : tableOfTable.getKeys()) {
						combo.add(key);
					}
				}

			}
		});
		optionOuvrir.setText("Ouvrir");
		optionFermer = new MenuItem(menuFichier, SWT.PUSH);
		optionFermer.setText("Fermer");
		optionFichier.setMenu(menuFichier);
		optionAide = new MenuItem(menu, SWT.CASCADE);
		optionAide.setText("Aide");
		shlNightShiftsPlanning.setMenuBar(menu);

		tableViewer = new TableViewer(shlNightShiftsPlanning);
		combo = new Combo(shlNightShiftsPlanning, SWT.NONE);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UpdateTable(combo.getText());
				System.out.println(combo.getText());
			}
		});
		combo.setBounds(10, 10, 189, 23);

	}

	private void UpdateTable(String tableName) {
		restTable();
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tableViewer.getTable().setLayoutData(gridData);
		tableViewer.getTable().setHeaderVisible(true);
		tableViewer.getTable().setLinesVisible(true);
		if (tableOfTable != null && tableName!= null && tableName != "") {
			TableLayout tlayout = new TableLayout();
			TableViewerColumn column = null;
			for (int i = 0; i < tableOfTable.get(tableName).get(0).size(); i++) {
				column = createTableViewerColumn(tableOfTable.get(tableName).get(0).get(i), 150, i);
				column.setLabelProvider(new ColumnLabelProvider() {
					public String getText(Object element) {
						if (((ArrayList<String>) element).size() > 0)
							return ((ArrayList<String>) element).get(0);
						else
							return "";
					}
				});
			}
			tableViewer.getTable().setLayout(tlayout);
		} else {
			System.out.println("Pas de données");
		}
		// The input will be an array or a collection.
		// We will need every element of this input.
		tableViewer.setContentProvider(new ArrayContentProvider());

		// For very element to display, we will select what to display
		// in function of the column index
		tableViewer.setLabelProvider(new ITableLabelProvider() {

			@Override
			public void removeListener(ILabelProviderListener arg0) {
				// nothing
			}

			@Override
			public boolean isLabelProperty(Object arg0, String arg1) {
				return false;
			}

			@Override
			public void dispose() {
				// nothing
			}

			@Override
			public void addListener(ILabelProviderListener arg0) {
				// nothing
			}

			@Override
			public String getColumnText(Object element, int colmnIndex) {

				String result = "";
				try {
					switch (colmnIndex) {
					case 0: {
						if (((ArrayList<String>) element).size() > 0)
							result = ((ArrayList<String>) element).get(0);
						break;
					}
					case 1: {
						if (((ArrayList<String>) element).size() > 1)
							result = ((ArrayList<String>) element).get(1);
						break;
					}
					case 2: {
						if (((ArrayList<String>) element).size() > 2)
							result = ((ArrayList<String>) element).get(2);
						break;
					}
					case 3:
						if (((ArrayList<String>) element).size() > 3)
							result = ((ArrayList<String>) element).get(3);
						break;
					case 4:
						if (((ArrayList<String>) element).size() > 4)
							result = ((ArrayList<String>) element).get(4);
						break;
					case 5:
						if (((ArrayList<String>) element).size() > 5)
							result = ((ArrayList<String>) element).get(5);
						break;
					case 6:
						if (((ArrayList<String>) element).size() > 6)
							result = ((ArrayList<String>) element).get(6);
						break;
					case 7:
						if (((ArrayList<String>) element).size() > 7)
							result = ((ArrayList<String>) element).get(7);
						break;
					case 8:
						if (((ArrayList<String>) element).size() > 8)
							result = ((ArrayList<String>) element).get(8);
						break;
					case 9:
						if (((ArrayList<String>) element).size() > 9)
							result = ((ArrayList<String>) element).get(9);
						break;
					default:
						if (((ArrayList<String>) element).size() > 0)
							result = ((ArrayList<String>) element).get(0);
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				return result;
			}

			@Override
			public Image getColumnImage(Object element, int colmnIndex) {
				return null;
			}
		});

		tableViewer.setInput(tableOfTable.get(tableName));
		tableViewer.refresh();
	}
	
	private void restTable() {
		tableViewer.getTable().clearAll();
		tableViewer.getTable().removeAll();
		tableViewer.refresh(); 
	}

	private TableViewerColumn createTableViewerColumn(String header, int width, int idx) {
		TableViewerColumn column = new TableViewerColumn(tableViewer, SWT.LEFT, idx);
		column.getColumn().setText(header);
		column.getColumn().setWidth(width);
		column.getColumn().setResizable(true);
		column.getColumn().setMoveable(true);

		return column;
	}
}
