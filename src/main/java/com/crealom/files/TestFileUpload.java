package com.crealom.files;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class TestFileUpload extends ActionSupport {
	//private List<File> files = new ArrayList<File>();
	private String myFileContentType;
	private String myFileFileName;
	private File myFile;
	
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}
	public String getMyFileContentType() {
		return myFileContentType;
	}
	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}
	public String getMyFileFileName() {
		return myFileFileName;
	}
	public String execute() {
		return SUCCESS;
	}
}
