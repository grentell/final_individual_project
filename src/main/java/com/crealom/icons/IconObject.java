package com.crealom.icons;

import com.crealom.workbook.WorksheetObject;

public abstract class IconObject extends WorksheetObject {
	private String iconType = "";
	
	public IconObject() {
		this("", "", "", "");
	}
	
	public String getIconType() {
		return iconType;
	}

	public void setIconType(String iconType) {
		this.iconType = iconType;
	}

	public IconObject(String iconId, String iconType, String posX, String posY) {
		super(posX, posY);
		this.iconType = iconType;
	}
	
	public abstract String toJSON();
}
