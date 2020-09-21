package com.harold.conan.nightShiftsPlan.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.osgi.framework.util.ArrayMap;

import com.harold.conan.nightShiftsPlan.services.ReadTableService;

public class ReadTableServiceImpl implements ReadTableService {

	@Override
	public ArrayMap<String, ArrayList<ArrayList<String>>> readeTabler(String dir) {
		ArrayMap<String, ArrayList<ArrayList<String>>> tablesContent = null;
		try {
			String readingFileResult = "";
			File file = new File(dir); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);

			tablesContent = new ArrayMap<String, ArrayList<ArrayList<String>>>(
					wb.getNumberOfSheets());
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {// on iter sur les tableur
				XSSFSheet sheet = wb.getSheetAt(i); // creating a Sheet object to retrieve object
				Iterator<Row> itr = sheet.iterator(); // iterating over excel file
				ArrayList<ArrayList<String>> contentSheetArrayList = new ArrayList<ArrayList<String>>();
				while (itr.hasNext()) { // on iter sur les lignes
					Row row = itr.next();
					ArrayList<String> contentRowArrayList = new ArrayList<String>();
					Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
					cellIterator.hasNext();

					while (cellIterator.hasNext()) {// on iter sur les cellule

						Cell cell = cellIterator.next();
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
								contentRowArrayList.add(cell.getStringCellValue());
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							contentRowArrayList.add(cell.getNumericCellValue() + "");
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							contentRowArrayList.add(cell.getBooleanCellValue() + "");
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							contentRowArrayList.add("");
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							contentRowArrayList.add(cell.getErrorCellValue() + "");
							break;
						case HSSFCell.CELL_TYPE_FORMULA: {
							try {
								if (HSSFDateUtil.isCellDateFormatted(cell))
									contentRowArrayList.add(cell.getDateCellValue() + "");
								else
									contentRowArrayList.add(cell.getCachedFormulaResultType() + "");
							} catch (Exception e) {
								// nothing to do
							}
							break;
						}
						default: {
							System.out.println(
									"READING ERROR ! \t\t\t" + cell.getClass() + "\nline : " + cell.getRowIndex());
						}

						}
					}
					contentSheetArrayList.add( contentRowArrayList);
				}
				tablesContent.put(sheet.getSheetName(), contentSheetArrayList);
			}

		} catch (Exception e) {
			System.out.println("Error reading xlsx : \n" + e.getMessage());
		}
		return tablesContent;
	}

}
