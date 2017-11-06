package com.crealom.files;

import com.crealom.workbook.WorksheetObject;

public class FileObject extends WorksheetObject {
	private String fileId;
	
	private String filename;

	public FileObject() {
		this("", "", "", "");
	}
	
	public FileObject(String filename, String fileId, String posX, String posY) {
		super(posX, posY);
		this.filename = filename;
		this.fileId = fileId;
	}
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String toJSON() {
		StringBuilder output = new StringBuilder();
		output.append("{\"objectid\": \"" + objectId + "\"");
		output.append(",\"objecttype\": \"file\"");
		output.append(",\"fileid\" : \"" + fileId + "\"");
		output.append(", \"filename\" : \"" + filename);
			output.append("\"");
		output.append(", \"posx\" : \"" + posX + "\"");
		output.append(", \"posy\" : \"" + posY + "\"");
		output.append("}");
		
		return output.toString();
	}
}
