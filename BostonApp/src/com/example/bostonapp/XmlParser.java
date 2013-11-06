package com.example.bostonapp;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class XmlParser extends Activity implements OnClickListener{
	String baseURL ="http://api.openweathermap.org/data/2.5/weather?q=";
	TextView tv;
	EditText city, state;
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = 
			        new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			}
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xml_parser);
		Button b = (Button) findViewById(R.id.bGetWeather);
		tv = (TextView)findViewById(R.id.tvWeather);
		city = (EditText)findViewById(R.id.etWCity);
		state = (EditText)findViewById(R.id.etWState);
		b.setOnClickListener(this);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.xml_parser, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String c = city.getText().toString();
		String s = state.getText().toString();
		
		StringBuilder URL = new StringBuilder(baseURL);
		URL.append(c+ "&mode=xml");
		String fullUrl = URL.toString();
		try {
			URL website = new URL(fullUrl);
			// Getting XML reader data 
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			HandlingXml doingWork = new HandlingXml();
			xr.setContentHandler(doingWork);
			xr.parse(new InputSource(website.openStream()));
			String information = doingWork.getInfo();
			tv.setText(information);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			tv.setText(e.toString());
		}
		
	}
	
}
