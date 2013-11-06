package com.example.bostonapp;

public class XmlCollected {
	double temp = 0;
	String city = null;
	
	public void setCity(String c){
		city = c;
	}
	
	public void setTemp(double tem){
		temp = tem;
	}
	
	public String dataToString(){
		return "In " + city + " the current temprature in C is " 
				+temp + " Degree";
	}
}
