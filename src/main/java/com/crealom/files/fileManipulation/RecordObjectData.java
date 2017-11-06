package com.crealom.files.fileManipulation;

import com.crealom.files.FileObject;
import com.crealom.workbook.WorkbookSerializeSingleton;
import com.crealom.workbook.Worksheet;
import com.crealom.workbook.WorksheetFiles;
import com.crealom.workbook.WorksheetObject;
import com.opensymphony.xwork2.ActionContext;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;

public class RecordObjectData {
	
	String objectid;
	String param;
	String value;

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public void execute() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String workbook = request.getParameter("workbook");
		String worksheetFile = request.getParameter("worksheet");
		
		//WorkbookSerializeSingleton wss = WorkbookSerializeSingleton.Instance();
		//Worksheet worksheet = wss.load("helloworksheet.xml");
		
		Worksheet worksheet = WorksheetFiles.loadWorksheet(workbook, worksheetFile);
		
		ActionContext ac = ActionContext.getContext();
		HttpParameters params = ac.getParameters();
		objectid = params.get("objectid").getValue();
		param = params.get("param").getValue();
		value = params.get("value").getValue();
		System.out.println("objectsId: " + objectid + ", param: " + param + ", value: " + value);
		
		WorksheetObject wo = worksheet.getObject(objectid);
		if(wo == null) {
			throw new Exception("WorksheetObject is null!");
		} else {
			switch(param) {
				case "posX":
					wo.setPosX(value);
					break;
				case "posY":
					wo.setPosY(value);
					break;
				default:
					break;
			}

			if(wo instanceof FileObject) {
				switch(param) {
					case "filename":
						((FileObject)wo).setFilename(value);
						break;
					default:
						break;
				}
			}
			//wss.save(worksheet);
			WorksheetFiles.saveWorksheet(workbook, worksheet);
		}
	}
}
