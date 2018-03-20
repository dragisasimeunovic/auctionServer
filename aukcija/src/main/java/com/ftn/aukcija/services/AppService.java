package com.ftn.aukcija.services;

import java.io.IOException;
import java.util.Properties;

import com.ftn.aukcija.App;

public class AppService {
	
	public static String getServerPort() {
		Properties properties = new Properties();
		
		try {
		    //load a properties file from class path, inside static method
			properties.load(App.class.getClassLoader().getResourceAsStream("application.properties"));

		    //get the property value and print it out
		    System.out.println(properties.getProperty("server.port"));
		    return properties.getProperty("server.port");
		} 
		catch (IOException ex) {
		    ex.printStackTrace();
		}
		
		return null;
	}

}
