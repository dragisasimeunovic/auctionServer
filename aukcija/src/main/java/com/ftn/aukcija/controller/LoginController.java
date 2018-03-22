package com.ftn.aukcija.controller;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.aukcija.model.Korisnik;
import com.ftn.aukcija.services.KorisnikService;

@RestController
@RequestMapping(value = "/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	
	@GetMapping("/{username}/{password}")
	public ResponseEntity<Korisnik> checkLoginData(@PathVariable String username, @PathVariable String password) {

		Korisnik korisnik = korisnikService.findByKorisnickoImeAndSifra(username, password);
		if (korisnik == null) {
			//return new ResponseEntity<Korisnik>(korisnik, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
	}

}
