package com.crealom.workbook;

import com.crealom.files.FileObject;
import com.crealom.icons.IconObject;
import com.crealom.icons.LeftArrowObject;
import com.crealom.icons.RightArrowObject;
import com.crealom.icons.UpArrowObject;
import com.crealom.icons.DownArrowObject;

import java.util.ArrayList;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class WorkbookSerializeSingleton {
	
	private static WorkbookSerializeSingleton instance;
	
	private WorkbookSerializeSingleton() {}
	
	public static WorkbookSerializeSingleton Instance() {
		if(instance == null)
			instance = new WorkbookSerializeSingleton();
		return instance;
	}
	
	public Worksheet load(String filename) {
		Worksheet worksheet = null;
		try {
			File xmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
			
			String title = doc.getDocumentElement().getAttribute("title");
			
			worksheet = new Worksheet();
			
			NodeList nList = doc.getElementsByTagName("worksheetObject");
			for (int i=0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) nNode;
					
					WorksheetObject wo = null;
					
					String objectId = element.getAttribute("objectid");
					String objectType = element.getAttribute("objecttype");
					String posX = element.getAttribute("posX");
					String posY = element.getAttribute("posY");
					
					switch (objectType) {
						case "file":
							wo = new FileObject();
							Element fileElement = (Element)element.getElementsByTagName("fileObject").item(0);
							String fileId = fileElement.getAttribute("fileId");
							String objectFilename = fileElement.getAttribute("filename");
							FileObject fo = (FileObject)wo;
							fo.setFilename(objectFilename);
							fo.setFileId(fileId);
							break;
						case "icon":
							
							String iconType = element.getAttribute("icontype");
							if(iconType.equals("leftarrow")) {
								wo = new LeftArrowObject(objectId, posX, posY);
							}
							else if(iconType.equals("rightarrow")) {
								wo = new RightArrowObject(objectId, posX, posY);
							}
							else if(iconType.equals("downarrow")) {
								wo = new DownArrowObject(objectId, posX, posY);
							}
							else if(iconType.equals("uparrow")) {
								wo = new UpArrowObject(objectId, posX, posY);
							} else {
								wo = new LeftArrowObject(objectId, posX, posY);
							}
							break;
						default:
							wo = null;
							break;						
					}
					wo.setPosX(posX);
					wo.setPosY(posY);
					wo.setObjectId(objectId);
					
					worksheet.add(wo);
					
				}
			}
			worksheet.setTitle(title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return worksheet;
	}
	
	public void save(String directory, Worksheet worksheet) throws ParserConfigurationException, TransformerException {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("worksheet");
		doc.appendChild(rootElement);

		ArrayList<WorksheetObject> objects = worksheet.getList();
		for(int j=0; j < objects.size(); j++) {

			Element objectElement = doc.createElement("worksheetObject");

			doc.getDocumentElement().setAttribute("title", worksheet.getTitle());

			WorksheetObject wo = objects.get(j);
			String objectIdent = wo.getObjectId();
			String objectType = "";
			String posX = wo.getPosX();
			String posY = wo.getPosY();

			if(wo instanceof FileObject) {

				Element fileObjectElement = doc.createElement("fileObject");

				FileObject fo = (FileObject)wo;

				objectType = "file";
				String fileId = fo.getFileId();
				String filename = fo.getFilename();

				Attr attrFileId = doc.createAttribute("fileId");
				attrFileId.setValue("" + fileId);
				fileObjectElement.setAttributeNode(attrFileId);

				Attr attrFilename = doc.createAttribute("filename");
				attrFilename.setValue(filename);
				fileObjectElement.setAttributeNode(attrFilename);

				objectElement.appendChild(fileObjectElement);
			}
			if(wo instanceof IconObject) {
				objectType = "icon";
				if(wo instanceof LeftArrowObject) {
					Attr iconType = doc.createAttribute("icontype");
					iconType.setValue("leftarrow");
					objectElement.setAttributeNode(iconType);
				}
				if(wo instanceof RightArrowObject) {
					Attr iconType = doc.createAttribute("icontype");
					iconType.setValue("rightarrow");
					objectElement.setAttributeNode(iconType);
				}
				if(wo instanceof UpArrowObject) {
					Attr iconType = doc.createAttribute("icontype");
					iconType.setValue("uparrow");
					objectElement.setAttributeNode(iconType);
				}
				if(wo instanceof DownArrowObject) {
					Attr iconType = doc.createAttribute("icontype");
					iconType.setValue("downarrow");
					objectElement.setAttributeNode(iconType);
				}
			}

			Attr objId = doc.createAttribute("objectid");
			objId.setValue(objectIdent);
			objectElement.setAttributeNode(objId);

			Attr objType = doc.createAttribute("objecttype");
			objType.setValue(objectType);
			objectElement.setAttributeNode(objType);

			Attr attrPosX = doc.createAttribute("posX");
			attrPosX.setValue("" + posX);
			objectElement.setAttributeNode(attrPosX);

			Attr attrPosY = doc.createAttribute("posY");
			attrPosY.setValue("" + posY);
			objectElement.setAttributeNode(attrPosY);

			rootElement.appendChild(objectElement);
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		
		DOMSource source = new DOMSource(doc);
		
		
		File file = new File(directory + worksheet.getTitle() + ".xml");
		System.out.println(file.getAbsolutePath());
		StreamResult result = new StreamResult(file);

		//StreamResult result = new StreamResult(System.out);
		transformer.transform(source, result);
		
	}
	
}
