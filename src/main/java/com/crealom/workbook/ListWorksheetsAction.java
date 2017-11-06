package com.crealom.workbook;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class ListWorksheetsAction {
	private String workbook;
	private String worksheetsJson;
	
	public void execute() throws Exception {
		
		String[] worksheets = WorksheetFiles.getWorksheets(workbook);
		StringBuilder worksheetBuilder = new StringBuilder();
		worksheetBuilder.append("{\"worksheets\": [");
		boolean first = true;
		for(String worksheet : worksheets) {
			if(first)
				first = false;
			else
				worksheetBuilder.append(",");
			
			String worksheetName = worksheet.substring(0, worksheet.length() - 4);
			
			worksheetBuilder.append("\"");
			worksheetBuilder.append(worksheetName);
			worksheetBuilder.append("\"");
			
		}
		worksheetBuilder.append("]}");
		worksheetsJson = worksheetBuilder.toString();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		pw.write(worksheetsJson);
		pw.flush();
		
	}
	public void setWorksheetsJson(String worksheetsJson) {
		this.worksheetsJson = worksheetsJson;
	}
	public String getWorksheetsJson() {
		return worksheetsJson;
	}
	public String getWorkbook() {
		return workbook;
	}
	public void setWorkbook(String workbook) {
		this.workbook = workbook;
	}
	
}
