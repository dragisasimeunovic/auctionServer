package com.ftn.aukcija.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.aukcija.model.Kategorija;
import com.ftn.aukcija.repository.KategorijaRepository;


@Service
public class KategorijaService {

	@Autowired
	private KategorijaRepository kategorijaRepository;
	
	public List<Kategorija> findAll() {
		return kategorijaRepository.findAll();
	}
	
}
