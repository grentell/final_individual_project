package com.crealom.files.fileManipulation;

import com.crealom.files.FileObject;
import com.crealom.workbook.WorkbookSerializeSingleton;
import com.crealom.workbook.Worksheet;
import com.crealom.workbook.WorksheetFiles;
import com.crealom.workbook.WorksheetObject;
import com.opensymphony.xwork2.ActionContext;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.HttpParameters;

public class CreateFileAction {
	
	private String objectid;
	private String objecttype;

	public String getObjecttype() {
		return objecttype;
	}

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public void setObjecttype(String objecttype) {
		this.objecttype = objecttype;
	}

	public String getPosX() {
		return posX;
	}

	public void setPosX(String posX) {
		this.posX = posX;
	}

	public String getPosY() {
		return posY;
	}

	public void setPosY(String posY) {
		this.posY = posY;
	}
	private FileObject fileObject;
	private String filename;
	private String posX;
	private String posY;
	
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String workbook = request.getParameter("workbook");
		String worksheet = request.getParameter("worksheet");
		
		fileObject = new FileObject();
		//WorkbookSerializeSingleton wss = WorkbookSerializeSingleton.Instance();
		//Worksheet ws = wss.load("helloworksheet.xml");
		Worksheet ws = WorksheetFiles.loadWorksheet(workbook, worksheet);
		WorksheetObject wo = null;
		try {
			switch(objecttype) {
				case "file":

					System.out.println("ObjectID: " + objectid + ", posX: " + posX + ", posY: " + posY);
					wo = new FileObject();
					((FileObject)wo).setFileId(objectid);
					((FileObject)wo).setObjectId(objectid);
					((FileObject)wo).setFilename(objectid);
					((FileObject)wo).setPosX(posX);
					((FileObject)wo).setPosY(posY);
					ws.add(wo);
					//wss.save(workbook, ws);
					WorksheetFiles.saveWorksheet(workbook, ws);
					break;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
				
		this.filename = filename;
	}
	
	public String getFilePosX() {
		return posX;
	}
	public void setFilePosX(String posX) {
		this.posX = posX;
	}
	
	public String getFilePosY() {
		return posY;
	}
	public void setFilePosY(String posY) {
		this.posY = posY;
	}
	
}
