package com.crealom.workbook;

/**
 * Stores information about a single displayable object contained within a 
 * worksheet.
 * 
 * @author brett
 */
public abstract class WorksheetObject {
	
	protected String posX;
	
	protected String posY;
	
	protected String objectId;

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	public WorksheetObject(String posX, String posY) {
		this.posX = posX;
		this.posY = posY;
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
	
	public abstract String toJSON();
}
