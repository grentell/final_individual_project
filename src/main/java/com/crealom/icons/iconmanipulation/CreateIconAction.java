package com.crealom.icons.iconmanipulation;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.crealom.icons.IconObject;
import com.crealom.icons.LeftArrowObject;
import com.crealom.icons.RightArrowObject;
import com.crealom.icons.UpArrowObject;
import com.crealom.icons.DownArrowObject;
import com.crealom.workbook.WorkbookSerializeSingleton;
import com.crealom.workbook.Worksheet;
import com.crealom.workbook.WorksheetFiles;
import com.crealom.workbook.WorksheetObject;

public class CreateIconAction {
	private String objectid;
	private String objecttype;
	private String objectdesc;
	
	public String getObjectdesc() {
		return objectdesc;
	}

	public void setObjectdesc(String objectdesc) {
		this.objectdesc = objectdesc;
	}

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
	private IconObject fileObject;
	private String posX;
	private String posY;
	
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String workbook = request.getParameter("workbook");
		String worksheet = request.getParameter("worksheet");
		
		//WorkbookSerializeSingleton wss = WorkbookSerializeSingleton.Instance();
		//Worksheet ws = wss.load("helloworksheet.xml");
		Worksheet ws = WorksheetFiles.loadWorksheet(workbook, worksheet);
		WorksheetObject wo = null;
		if(objecttype.equals("icon")) {
			try {
				switch(objectdesc) {
					case "leftarrow":
	
						System.out.println("ObjectID: " + objectid + ", posX: " + posX + ", posY: " + posY);
						wo = new LeftArrowObject();
						((LeftArrowObject)wo).setObjectId(objectid);
						((LeftArrowObject)wo).setPosX(posX);
						((LeftArrowObject)wo).setPosY(posY);
						ws.add(wo);
						//wss.save(ws);
						WorksheetFiles.saveWorksheet(workbook, ws);
						
						break;
					case "rightarrow":
						
						System.out.println("ObjectID: " + objectid + ", posX: " + posX + ", posY: " + posY);
						wo = new RightArrowObject();
						((RightArrowObject)wo).setObjectId(objectid);
						((RightArrowObject)wo).setPosX(posX);
						((RightArrowObject)wo).setPosY(posY);
						ws.add(wo);
						//wss.save(ws);
						WorksheetFiles.saveWorksheet(workbook, ws);
						
						break;
					case "uparrow":
						
						System.out.println("ObjectID: " + objectid + ", posX: " + posX + ", posY: " + posY);
						wo = new UpArrowObject();
						((UpArrowObject)wo).setObjectId(objectid);
						((UpArrowObject)wo).setPosX(posX);
						((UpArrowObject)wo).setPosY(posY);
						ws.add(wo);
						//wss.save(ws);
						WorksheetFiles.saveWorksheet(workbook, ws);
						
						break;
					case "downarrow":
						
						System.out.println("ObjectID: " + objectid + ", posX: " + posX + ", posY: " + posY);
						wo = new DownArrowObject();
						((DownArrowObject)wo).setObjectId(objectid);
						((DownArrowObject)wo).setPosX(posX);
						((DownArrowObject)wo).setPosY(posY);
						ws.add(wo);
						//wss.save(ws);
						WorksheetFiles.saveWorksheet(workbook, ws);
						
						break;
					
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return "success";
	}
	
}
