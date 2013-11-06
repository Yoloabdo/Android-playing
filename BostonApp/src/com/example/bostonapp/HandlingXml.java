package com.example.bostonapp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class HandlingXml extends DefaultHandler {
	XmlCollected info = new XmlCollected();
	
	public String getInfo(){
		return info.dataToString();
	}
	private static final double  kelvinConvert = 273.15;
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		
		if (localName.equals("city")) {
			String city = attributes.getValue("name");
			info.setCity(city);
		}else if(localName.equals("temperature")) {
			String temp = attributes.getValue("value");
			double tem = Double.parseDouble(temp);
			// the api returns a kelvin value, converting it into celisus 
			//via substracting the kelvin value
			tem = tem -kelvinConvert;
			info.setTemp(tem);
		}
		
	}
	
}
