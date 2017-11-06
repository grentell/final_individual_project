package com.crealom;

import com.crealom.files.FileObject;
import com.crealom.workbook.Workbook;
import com.crealom.workbook.WorkbookSerializeSingleton;
import com.crealom.workbook.Worksheet;
import com.crealom.workbook.WorksheetFiles;
import com.crealom.workbook.WorksheetObject;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class TestWorkbookWebpageAction {
	
	private String workbookJson;
	
	public void execute() throws Exception {
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
		wo2.setPosX(50 + "px");
		wo2.setPosY(100 + "px");
		wo2.setObjectId("anobjectid");
		((FileObject)wo2).setFileId(""+ Math.floor(Math.random() * 1000000 + 1));
		((FileObject)wo2).setFilename("Congratulations.pdf");
		WorksheetObject wo3 = new FileObject();
		ws2.add(wo3);
		
		workbookJson = wb.toJSON();
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		PrintWriter pw = response.getWriter();
		pw.write(workbookJson);
		pw.flush();
		
		//WorkbookSerializeSingleton wss = WorkbookSerializeSingleton.Instance();
		//wss.save(wb);
	}
		
	public void execute2() {
		try {
			
			HttpServletRequest request = ServletActionContext.getRequest();
			String workbook = request.getParameter("workbook");
			String worksheet = request.getParameter("worksheet");
			
			Workbook wb = new Workbook();
			//WorkbookSerializeSingleton wss = WorkbookSerializeSingleton.Instance();
			//Worksheet ws1 = wss.load("helloworksheet.xml");
			Worksheet ws1 = WorksheetFiles.loadWorksheet(workbook, worksheet);
			wb.add(ws1);
			workbookJson = wb.toJSON();

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/json");
			PrintWriter pw = response.getWriter();
			pw.write(workbookJson);
			pw.flush();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setWorkbookJson(String json) {
		this.workbookJson = json;
	}
	
	public String getWorkbookJson() {
		return this.workbookJson;
	}
}
