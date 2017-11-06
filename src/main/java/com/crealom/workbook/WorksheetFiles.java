package com.crealom.workbook;

import java.io.File;
import java.util.ArrayList;

public class WorksheetFiles {

	private static String workbookDirectory = "C:\\Workbooks\\";
	
	public static String[] getWorkbookList() {
		File workbookDir = new File(workbookDirectory);
		return workbookDir.list();
	}
	
	public static String[] getWorksheets(String workbookName) {
		File worksheetDir = new File(workbookDirectory + "\\" + workbookName + "\\");
		ArrayList<String> worksheets = new ArrayList<>();
		for(String worksheet : worksheetDir.list()) {
			File testWorksheet = new File(workbookDirectory + "\\" + workbookName + "\\" + worksheet);
			if(testWorksheet.isDirectory())
				continue;
			
			worksheets.add(worksheet);
		}
		String[] worksheetsArray = new String[worksheets.size()];
		int counter = 0;
		for(String worksheet : worksheets)
			worksheetsArray[counter++] = worksheet;
		
		return worksheetsArray;
	}
	
	public static Worksheet loadWorksheet(String workbookName, String worksheetName) {
		WorkbookSerializeSingleton wss = WorkbookSerializeSingleton.Instance();
		return wss.load(workbookDirectory + "\\" + workbookName + "\\" + worksheetName + ".xml");
	}
	
	public static void saveWorksheet(String wb, Worksheet ws) {
		WorkbookSerializeSingleton wss = WorkbookSerializeSingleton.Instance();
		try {
			wss.save(workbookDirectory + "\\" + wb + "\\", ws);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getWorbookDirectory() {
		return workbookDirectory;
	}
}
