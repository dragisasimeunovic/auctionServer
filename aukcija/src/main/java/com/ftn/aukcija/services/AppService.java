package com.ftn.aukcija.services;

import java.io.IOException;
import java.util.Properties;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.aukcija.App;
import com.ftn.aukcija.model.Firma;
import com.ftn.aukcija.model.Kategorija;
import com.ftn.aukcija.model.Korisnik;

@Component
public class AppService {
	
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private KategorijaService kategorijaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private FirmaService firmaService;
	
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
		
		System.out.println("\nInicijalizacija grupa korisnika!\n");
			
	}
	
	public void initializeCategories() {
		
		kategorijaService.deleteAll();
		
		kategorijaService.save(new Kategorija("brale", "Brale usluge"));
		kategorijaService.save(new Kategorija("svadbe", "Torlakovic svadbeni salon"));
		kategorijaService.save(new Kategorija("kupoprodaja", "Zuna"));
		kategorijaService.save(new Kategorija("rostilj", "Rakonja"));
		
		System.out.println("\nInicijalizacija kategorija!\n");
		
	}
	
	public void initializeUsers() {
		
		Korisnik korisnik1 = new Korisnik(1L, "John", "McClane", "jm@gmail.com", "aa", "aa", "Los Angeles", "Nakatomi 123/12", 45.267136, 19.833549, "21000", "korisnik", true, null);
		Korisnik korisnik2 = new Korisnik(2L, "Leonardo", "Da Vinci", "ldv@gmail.com", "bb", "bb", "Los Angeles", "Nakatomi 123/12", 45.267136, 20.833549, "21000", "firma", true, null);
		Korisnik korisnik3 = new Korisnik(3L, "Tirion", "Lanister", "tl@gmail.com", "cc", "cc", "Los Angeles", "Nakatomi 123/12", 45.267136, 21.833549, "21000", "firma", true, null);
		
		identityService.deleteUser("john");
		identityService.deleteUser("leonardo");
		identityService.deleteUser("tirion");
		
		korisnikService.save(korisnik1);
		korisnikService.save(korisnik2);
		korisnikService.save(korisnik3);
		
		org.activiti.engine.identity.User activityUser1;
		org.activiti.engine.identity.User activityUser2;
		org.activiti.engine.identity.User activityUser3;
		
		activityUser1 = identityService.newUser(korisnik1.getKorisnickoIme());
		activityUser1.setFirstName(korisnik1.getIme());
		activityUser1.setLastName(korisnik1.getPrezime());
		activityUser1.setEmail(korisnik1.getEmail());
		activityUser1.setPassword(korisnik1.getSifra());
		identityService.saveUser(activityUser1);
		identityService.createMembership(activityUser1.getId(), korisnik1.getTip());
		
		activityUser2 = identityService.newUser(korisnik2.getKorisnickoIme());
		activityUser2.setFirstName(korisnik2.getIme());
		activityUser2.setLastName(korisnik2.getPrezime());
		activityUser2.setEmail(korisnik2.getEmail());
		activityUser2.setPassword(korisnik2.getSifra());
		identityService.saveUser(activityUser2);
		identityService.createMembership(activityUser2.getId(), korisnik2.getTip());
		
		activityUser3 = identityService.newUser(korisnik3.getKorisnickoIme());
		activityUser3.setFirstName(korisnik3.getIme());
		activityUser3.setLastName(korisnik3.getPrezime());
		activityUser3.setEmail(korisnik3.getEmail());
		activityUser3.setPassword(korisnik3.getSifra());
		identityService.saveUser(activityUser3);
		identityService.createMembership(activityUser3.getId(), korisnik3.getTip());
		
		System.out.println("\nInicijalizacija korisnika!\n");
		
		
	}
	
	public void initializeFirms() {
		
		firmaService.deleteAll();
		
		Firma firma1 = new Firma(1L, "Bosch", 25, "kupoprodaja", korisnikService.findById(2L));
		Firma firma2 = new Firma(2L, "Mico Kazan", 50, "brale", korisnikService.findById(3L));
		
		firmaService.save(firma1);
		firmaService.save(firma2);
		
		System.out.println("\nInicijalizacija firmi!\n");
		
	}

}
