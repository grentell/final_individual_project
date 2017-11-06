package com.crealom.icons;

import com.crealom.workbook.WorksheetObject;

public class LeftArrowObject extends IconObject {
	public LeftArrowObject() {
		this("", "", "");
	}
	
	public LeftArrowObject(String iconId, String posX, String posY) {
		super(iconId, "leftarrow", posX, posY);
	}
	
	public String toJSON() {
		StringBuilder output = new StringBuilder();
		output.append("{\"objectid\": \"" + objectId + "\"");
		output.append(",\"objecttype\": \"icon\"");
		output.append(",\"objectdesc\": \"leftarrow\"");
		output.append(", \"posx\" : \"" + posX + "\"");
		output.append(", \"posy\" : \"" + posY + "\"");
		output.append("}");
		
		return output.toString();
	}
}
