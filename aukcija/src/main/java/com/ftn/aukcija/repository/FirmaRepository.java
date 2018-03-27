package com.ftn.aukcija.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.aukcija.model.Firma;

public interface FirmaRepository extends JpaRepository<Firma, Long>{

	Collection<Firma> findByKategorija(String kategorija);
	
}
