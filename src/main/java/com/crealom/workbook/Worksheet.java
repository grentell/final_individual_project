package com.crealom.workbook;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A worksheet stores a number of objects which represent either files or 
 * drawable objects.
 * 
 * @author brett
 */
public class Worksheet {
	ArrayList<WorksheetObject> worksheetObjects;
	String title;
	
	public Worksheet() {
		worksheetObjects = new ArrayList<>();
	}
	
	public void add(WorksheetObject wo) {
		worksheetObjects.add(wo);
	}
	
	public ArrayList<WorksheetObject> getList() {
		return worksheetObjects;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void remove(String objectId) {
		WorksheetObject obj = getObject(objectId);
		worksheetObjects.remove(obj);
	}
	
	public WorksheetObject getObject(String objectId) {
		WorksheetObject obj = null;
		
		ArrayList<WorksheetObject> objects = worksheetObjects;
		Iterator<WorksheetObject> iter = objects.iterator();
		
		while(iter.hasNext()) {
			WorksheetObject wo = iter.next();
			if(wo.getObjectId().equals(objectId)) {
				obj = wo;
				break;
			}
		}
		
		return obj;
	}
	
	public String toJSON() {
		StringBuilder output = new StringBuilder();
		output.append("{");
		
		output.append("\"title\" : \"");
			output.append(title);
			output.append("\"");
			
		output.append(", \"objects\" : [");
		for (int i=0; i < worksheetObjects.size(); i++) {
			if(i != 0)
				output.append(",");
			output.append(worksheetObjects.get(i).toJSON());
		}
		output.append("]");
		
		output.append("}");
		
		return output.toString();
	}
}

