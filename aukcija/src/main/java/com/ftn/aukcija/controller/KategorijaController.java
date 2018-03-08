package com.ftn.aukcija.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.aukcija.model.Kategorija;
import com.ftn.aukcija.services.KategorijaService;

@RestController
@RequestMapping(value = "/category")
@CrossOrigin(origins = "http://localhost:4200")
public class KategorijaController {

	@Autowired
	private KategorijaService kategorijaService;
	
	@GetMapping("/getAllCategories")
	public ResponseEntity<List<Kategorija>> findAll(){
		List<Kategorija> kategorije = kategorijaService.findAll();
		return new ResponseEntity<List<Kategorija>>(kategorije, HttpStatus.OK);
	}
	
}
