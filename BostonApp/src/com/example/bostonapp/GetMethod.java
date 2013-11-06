package com.example.bostonapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetMethod {
	public String getIntrnetData() throws Exception {
		
		BufferedReader in = null;
		String data = null;
		try {
			HttpClient Client= new DefaultHttpClient();
			URI website = new URI("https://www.facebook.com/");
			HttpGet request = new HttpGet();
			request.setURI(website);
			HttpResponse response = Client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sp = new StringBuffer("");
			String line = "";
			String Nl = System.getProperty("line.seperator");
			while ((line = in.readLine() ) != null) {
				sp.append(line + Nl);
			}
			in.close();
			data = sp.toString();
			return data;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		return data;
	}

}
