package com.crealom.workbook;

import java.util.ArrayList;

/**
 * Stores information about a workbook.  Each workbook contains a number of 
 * workbook pages called "worksheets".
 * 
 * @author brett
 */
public class Workbook {
	ArrayList<Worksheet> worksheets;
	
	public Workbook() {
		worksheets = new ArrayList<>();
	}
	
	public void add(Worksheet worksheet) {
		worksheets.add(worksheet);
	}
	
	public ArrayList<Worksheet> getList() {
		return worksheets;
	}
	
	/*
	* This is for client side only.
	*/
	public String toJSON() {
		StringBuilder output = new StringBuilder();
		
		output.append("{");
		
		output.append("\"worksheets\": [");
		for (int i=0; i < worksheets.size(); i++) {
			if (i != 0)
				output.append(",");
			output.append(worksheets.get(i).toJSON());
		}
		output.append("]");
		
		output.append("}");
		
		return output.toString();
	}
}
