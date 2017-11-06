package com.crealom.icons;

import com.crealom.workbook.WorksheetObject;

public class RightArrowObject extends IconObject {
	public RightArrowObject() {
		this("", "", "");
	}
	
	public RightArrowObject(String iconId, String posX, String posY) {
		super(iconId, "rightarrow", posX, posY);
	}
	
	public String toJSON() {
		StringBuilder output = new StringBuilder();
		output.append("{\"objectid\": \"" + objectId + "\"");
		output.append(",\"objecttype\": \"icon\"");
		output.append(",\"objectdesc\": \"rightarrow\"");
		output.append(", \"posx\" : \"" + posX + "\"");
		output.append(", \"posy\" : \"" + posY + "\"");
		output.append("}");
		
		return output.toString();
	}
}
