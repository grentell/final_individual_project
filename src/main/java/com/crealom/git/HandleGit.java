/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.crealom.git;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author brett
 */
public class HandleGit extends HttpServlet {

	private static final String REMOTE_URL = "https://github.com/github/testrepo.git";
	private static final String LOCAL_DATA = "C:\\Users\\brett\\Documents\\SENG4800B\\seng4800individual\\localdata";
	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			/*out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet HandleGit</title>");			
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet HandleGit at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
			response.sendRedirect("/seng4800individual/index.jsp");*/
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		File localPath = new File("C:\\Users\\brett\\Documents\\SENG4800B\\seng4800individual\\localdata");
		if (!localPath.delete()) {
			throw new IOException("Could not delete temporary file " + localPath);
		}

		System.out.println("Cloning from " + REMOTE_URL + " to " + localPath);
		try (Git result = Git.cloneRepository()
				.setURI(REMOTE_URL)
				.setDirectory(localPath)
				.call()) {
			System.out.println("Having repository: " + result.getRepository().getDirectory());
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
