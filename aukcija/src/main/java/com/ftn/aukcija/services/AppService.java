package com.ftn.aukcija.services;

import java.io.IOException;
import java.util.Properties;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.aukcija.App;
import com.ftn.aukcija.model.Kategorija;

@Component
public class AppService {
	
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private KategorijaService kategorijaService;
	
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
	
	public void initializeGroups() {
		
		System.out.println("\nInicijalizacija grupa korisnika!\n");

		identityService.deleteGroup("korisnik");
		identityService.deleteGroup("firma");
		
		Group korisnikG;
		
		korisnikG = identityService.newGroup("korisnik");
		korisnikG.setName("korisnik");
		korisnikG.setType("assigment");
		identityService.saveGroup(korisnikG);
		
		Group firmaG;
		
		firmaG = identityService.newGroup("firma");
		firmaG.setName("firma");
		firmaG.setType("assigment");
		identityService.saveGroup(firmaG);
			
	}
	
	public void initializeCategories() {
		
		System.out.println("\nInicijalizacija kategorija!\n");
		
		kategorijaService.deleteAll();
		
		kategorijaService.save(new Kategorija("brale", "Brale usluge"));
		kategorijaService.save(new Kategorija("svadbe", "Torlakovic svadbeni salon"));
		kategorijaService.save(new Kategorija("kupoprodaja", "Zuna"));
		kategorijaService.save(new Kategorija("rostilj", "Rakonja"));
		
	}

}
