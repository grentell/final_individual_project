package com.crealom.icons;

import com.crealom.workbook.WorksheetObject;

public class DownArrowObject extends IconObject {
	public DownArrowObject() {
		this("", "", "");
	}
	
	public DownArrowObject(String iconId, String posX, String posY) {
		super(iconId, "downarrow", posX, posY);
	}
	
	public String toJSON() {
		StringBuilder output = new StringBuilder();
		output.append("{\"objectid\": \"" + objectId + "\"");
		output.append(",\"objecttype\": \"icon\"");
		output.append(",\"objectdesc\": \"downarrow\"");
		output.append(", \"posx\" : \"" + posX + "\"");
		output.append(", \"posy\" : \"" + posY + "\"");
		output.append("}");
		
		return output.toString();
	}
}
