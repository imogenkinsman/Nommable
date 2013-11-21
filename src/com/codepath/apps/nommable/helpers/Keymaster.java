package com.codepath.apps.nommable.helpers;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

/**
 * 
 * Used for managing secret keys, which are stored in an external file and not committed to source control.
 * 
 * "I am the Keymaster!" - Rick Moranis
 *
 */

public class Keymaster {
	
	private static final String filename = "secrets.xml";
	
	private static String parseXml(String elementId) {
		
		try {
			
			URL dir_url = Keymaster.class.getClassLoader().getResource(filename);
			File xmlFile = new File(dir_url.toURI());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("key");
						
			for (int pos = 0; pos < nList.getLength(); pos++) {
				Element element = (Element) nList.item(pos);
				
				if (element.getAttribute("type") == elementId){
					return element.getAttribute("value");
				}
			}
			
		} catch (URISyntaxException e) {
			Log.e("ERROR", "URISyntaxException while attempting to parse elementId: " + elementId);
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			Log.e("ERROR", "ParserConfigurationException while attempting to parse elementId: " + elementId);
			e.printStackTrace();
		} catch (SAXException e) {
			Log.e("ERROR", "SAXException while attempting to parse elementId: " + elementId);
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("ERROR", "IOException while attempting to parse elementId: " + elementId);
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static String getClientId() {
		return parseXml("clientid");
	}
	
	public static String getClientSecret() {
		return parseXml("clientsecret");
	}
}
