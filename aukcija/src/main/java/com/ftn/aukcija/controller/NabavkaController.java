package com.ftn.aukcija.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.aukcija.model.ZahtevZaNabavku;
import com.ftn.aukcija.services.NabavkaService;

@RestController
@RequestMapping(value = "/nabavka")
@CrossOrigin(origins = "http://localhost:4200")
public class NabavkaController {

	private NabavkaService nabavkaService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/saveSupplyRequest")
	public ResponseEntity<ZahtevZaNabavku> saveSupplyRequest(@RequestBody ZahtevZaNabavku zahtevZaNabavku) throws ParseException{
	
		SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMdd");
		Date date = format1.parse(zahtevZaNabavku.getRokZaPonude());
		System.out.println(format2.format(date));
		
		return new ResponseEntity<ZahtevZaNabavku>(zahtevZaNabavku, HttpStatus.OK);
	}
	
}
