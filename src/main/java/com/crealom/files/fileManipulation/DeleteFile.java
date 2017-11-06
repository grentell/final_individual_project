package com.crealom.files.fileManipulation;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.crealom.workbook.Worksheet;
import com.crealom.workbook.WorksheetFiles;

public class DeleteFile {
	public void execute() {
		HttpServletRequest hsr = ServletActionContext.getRequest();
		String workbook = hsr.getParameter("workbook");
		String worksheet= hsr.getParameter("worksheet");
		String filename = hsr.getParameter("filename");
		
		File fileToDelete = new File(WorksheetFiles.getWorbookDirectory() + workbook + "\\" + worksheet + "\\" + filename);
		fileToDelete.delete();
		
		Worksheet ws = WorksheetFiles.loadWorksheet(workbook, worksheet);
		ws.remove(filename);
		WorksheetFiles.saveWorksheet(workbook, ws);
		
		HttpServletResponse hsp = ServletActionContext.getResponse();
		hsp.setContentType("text/x-json;charset=UTF-8");
		try {
			hsp.getWriter().write("{'success': 'true'}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
