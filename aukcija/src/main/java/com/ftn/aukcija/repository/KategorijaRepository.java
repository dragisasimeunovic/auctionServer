package com.ftn.aukcija.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.aukcija.model.Kategorija;

public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {

	Kategorija findBySifra(String sifra);
	
}
