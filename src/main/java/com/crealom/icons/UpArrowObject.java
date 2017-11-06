package com.crealom.icons;

import com.crealom.workbook.WorksheetObject;

public class UpArrowObject extends IconObject {
	public UpArrowObject() {
		this("", "", "");
	}
	
	public UpArrowObject(String iconId, String posX, String posY) {
		super(iconId, "uparrow", posX, posY);
	}
	
	public String toJSON() {
		StringBuilder output = new StringBuilder();
		output.append("{\"objectid\": \"" + objectId + "\"");
		output.append(",\"objecttype\": \"icon\"");
		output.append(",\"objectdesc\": \"uparrow\"");
		output.append(", \"posx\" : \"" + posX + "\"");
		output.append(", \"posy\" : \"" + posY + "\"");
		output.append("}");
		
		return output.toString();
	}
}
