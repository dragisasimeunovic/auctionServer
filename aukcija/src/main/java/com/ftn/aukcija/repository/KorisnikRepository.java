package com.ftn.aukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.aukcija.model.Korisnik;


public interface KorisnikRepository extends JpaRepository<Korisnik, String> {

	Korisnik findById(Long id);
	
	Korisnik findByKorisnickoImeAndSifra(String korisnickoIme, String sifra);
	
}
