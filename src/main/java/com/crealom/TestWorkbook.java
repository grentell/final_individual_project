package com.crealom;

import com.crealom.workbook.Workbook;
import com.crealom.workbook.Worksheet;
import com.crealom.workbook.WorksheetObject;

import com.crealom.files.FileObject;

public class TestWorkbook {
	public static void main(String[] args) {
		Workbook wb = new Workbook();
		
		Worksheet ws1 = new Worksheet();
		ws1.setTitle("MyWorksheet");
		wb.add(ws1);
		WorksheetObject wo = new FileObject();
		ws1.add(wo);
		
		Worksheet ws2 = new Worksheet();
		ws2.setTitle("BrettsWorksheet");
		wb.add(ws2);
		WorksheetObject wo2 = new FileObject();
		ws2.add(wo2);
		wo2.setPosX(5 + "px");
		wo2.setPosY(10 + "px");
		((FileObject)wo2).setFileId("" + Math.floor(Math.random() * 1000000 + 1));
		((FileObject)wo2).setFilename("Congratulations.pdf");
		WorksheetObject wo3 = new FileObject();
		ws2.add(wo3);
		
		System.out.println(wb.toJSON());
	}
}
