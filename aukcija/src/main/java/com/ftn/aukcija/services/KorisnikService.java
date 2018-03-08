package com.ftn.aukcija.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.aukcija.model.Korisnik;
import com.ftn.aukcija.repository.KorisnikRepository;


@Service
public class KorisnikService {
	
	@Autowired
	public KorisnikRepository korisnikRepository;
	
	public Korisnik save(Korisnik user){
		return korisnikRepository.save(user);
	}
	
	public Korisnik findById(Long id) {
		return korisnikRepository.findById(id);
	}

}
