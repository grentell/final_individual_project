package com.crealom.files.fileRetrieval;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;

import com.crealom.workbook.WorksheetFiles;
import com.opensymphony.xwork2.ActionSupport;

public class PutFileObject {
	public void execute() {
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = ServletActionContext.getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// Parse the request
		try {
			HttpServletRequest hsr = ServletActionContext.getRequest();
			String workbook = hsr.getParameter("workbook");
			String worksheet = hsr.getParameter("worksheet");
			String filename = hsr.getParameter("filename");
			List<FileItem> items = upload.parseRequest(hsr);
			for(FileItem item : items) {
				File uploadedFile = new File(WorksheetFiles.getWorbookDirectory() + workbook + "\\" + worksheet + "\\" + filename);
				item.write(uploadedFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
