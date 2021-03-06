/**
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 */
package ti.modules.titanium.xml;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.appcelerator.titanium.TiContext;
import org.appcelerator.titanium.TiModule;
import org.appcelerator.titanium.util.Log;
import org.xml.sax.SAXException;

public class XMLModule extends TiModule {

	private static DocumentBuilder builder;
	private static final String LCAT = "XMLModule";
	
	static {
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			Log.e(LCAT, "Error finding DOM implementation", e);
		}
	}
	
	public XMLModule(TiContext context) {
		super(context);
	}
	
	public DocumentProxy parseString(String xml)
	{
		return parse(getTiContext(), xml);
	}
	
	public static DocumentProxy parse(TiContext context, String xml)
	{
		if (builder != null) {
			try {
				return new DocumentProxy(context, builder.parse(new ByteArrayInputStream(xml.getBytes())));
			} catch (SAXException e) {
				Log.e(LCAT, "Error parsing XML", e);
			} catch (IOException e) {
				Log.e(LCAT, "Error reading XML", e);
			}
		}
		return null;
	}
}
