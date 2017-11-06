package com.crealom.files.fileRetrieval;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.crealom.workbook.WorksheetFiles;
import com.opensymphony.xwork2.ActionSupport;

public class GetFileObject {
	private String filename;
	private String worksheet;
	private String workbook;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getWorksheet() {
		return worksheet;
	}
	public void setWorksheet(String worksheet) {
		this.worksheet = worksheet;
	}
	public String getWorkbook() {
		return workbook;
	}
	public void setWorkbook(String workbook) {
		this.workbook = workbook;
	}
	public void execute() {
		HttpServletResponse hsr = ServletActionContext.getResponse();
		hsr.setContentType("application/octet-stream");
		hsr.setHeader("Content-Disposition", "attachment;filename=" + filename);
		String directory = WorksheetFiles.getWorbookDirectory() + "\\" + workbook + "\\" + worksheet;
		String file = directory + "\\" + filename;
		try {
			FileInputStream in = new FileInputStream(new File(file));
			
			ServletOutputStream out = hsr.getOutputStream();
			
			byte[] outputByte = new byte[1];
			while(in.read(outputByte, 0, outputByte.length) != -1) {
				out.write(outputByte, 0, outputByte.length);
			}
			in.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
