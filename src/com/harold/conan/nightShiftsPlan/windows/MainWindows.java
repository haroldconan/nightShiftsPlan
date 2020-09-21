package com.harold.conan.nightShiftsPlan.windows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
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
	private Map<String, TableViewer> tableViewer = new HashMap<String, TableViewer>();
	private TabFolder tabFolder;

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
			// tableViewer.getTable().setBounds(205, 10,
			// shlNightShiftsPlanning.getBounds().width-230,
			// shlNightShiftsPlanning.getBounds().height-110);
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
		shlNightShiftsPlanning.setSize(1670, 845);
		shlNightShiftsPlanning.setText("Night shifts planning");
		menu = new Menu(shlNightShiftsPlanning, SWT.BAR);
		optionFichier = new MenuItem(menu, SWT.CASCADE);
		optionFichier.setText("Fichier");
		menuFichier = new Menu(shlNightShiftsPlanning, SWT.DROP_DOWN);

		tabFolder = new TabFolder(shlNightShiftsPlanning, SWT.NONE);
		tabFolder.setBounds(10, 37, 1634, 739);

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
						TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
						tabItem.setText(key);

						tableViewer.put(key, new TableViewer(tabFolder));
						Table table = tableViewer.get(key).getTable();
						tabItem.setControl(table);
						UpdateTable(key);
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

	}

	private void UpdateTable(String tableName) {
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		tableViewer.get(tableName).getTable().setLayoutData(gridData);
		tableViewer.get(tableName).getTable().setHeaderVisible(true);
		tableViewer.get(tableName).getTable().setLinesVisible(true);
		if (tableOfTable != null && tableName != null && tableName != "") {
			TableLayout tlayout = new TableLayout();
			TableViewerColumn column = null;
			for (int i = 0; i < tableOfTable.get(tableName).get(0).size(); i++) {
				column = createTableViewerColumn(tableName, tableOfTable.get(tableName).get(0).get(i), 150, i);
				column.setLabelProvider(new ColumnLabelProvider() {
					public String getText(Object element) {
						if (((ArrayList<String>) element).size() > 0)
							return ((ArrayList<String>) element).get(0);
						else
							return "";
					}
				});
			}
			tableViewer.get(tableName).getTable().setLayout(tlayout);
		} else {
			System.out.println("Pas de données");
		}
		// The input will be an array or a collection.
		// We will need every element of this input.
		tableViewer.get(tableName).setContentProvider(new ArrayContentProvider());

		// For very element to display, we will select what to display
		// in function of the column index
		tableViewer.get(tableName).setLabelProvider(new ITableLabelProvider() {

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
				if (((ArrayList<String>) element).size() > 0)
				try {
					if (((ArrayList<String>) element).size() > colmnIndex)
						result = ((ArrayList<String>) element).get(colmnIndex);
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

		tableViewer.get(tableName).setInput(tableOfTable.get(tableName));
		tableViewer.get(tableName).refresh();
	}

	private TableViewerColumn createTableViewerColumn(String tableNameKey, String header, int width, int idx) {
		TableViewerColumn column = new TableViewerColumn(tableViewer.get(tableNameKey), SWT.LEFT, idx);
		column.getColumn().setText(header);
		column.getColumn().setWidth(width);
		column.getColumn().setResizable(true);
		column.getColumn().setMoveable(true);

		return column;
	}
}
