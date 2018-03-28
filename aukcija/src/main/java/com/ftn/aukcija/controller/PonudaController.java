package com.ftn.aukcija.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.aukcija.model.Kategorija;
import com.ftn.aukcija.model.Ponuda;
import com.ftn.aukcija.model.ZahtevZaNabavku;
import com.ftn.aukcija.services.PonudaService;

@RestController
@RequestMapping(value = "/ponude")
@CrossOrigin(origins = "http://localhost:4200")
public class PonudaController {
	
	@Autowired
	private PonudaService ponudaService;

	@GetMapping("/getAllActiveOffers/{korisnikID}")
	public ResponseEntity<List<Ponuda>> getAllActiveOffers(@PathVariable Long korisnikID){
		List<Ponuda> ponude = ponudaService.getAllActiveOffers(korisnikID);
		return new ResponseEntity<List<Ponuda>>(ponude, HttpStatus.OK);
	}
	
	@PostMapping("/saveOffer")
	public ResponseEntity<Ponuda> saveOffer(@RequestBody Ponuda ponuda) throws ParseException{
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
		Date date = format1.parse(ponuda.getRokZaIzvrsavanje());
		ponuda.setRokZaIzvrsavanje(format2.format(date));
		Ponuda savedOffer = ponudaService.save(ponuda);
		return new ResponseEntity<Ponuda>(savedOffer, HttpStatus.OK);
	}
	
}
