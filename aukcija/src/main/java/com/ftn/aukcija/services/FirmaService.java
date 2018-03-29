package com.ftn.aukcija.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.aukcija.model.Firma;
import com.ftn.aukcija.repository.FirmaRepository;

@Service
public class FirmaService {

	@Autowired
	private FirmaRepository firmaRepository;
	
	public Firma save(Firma firma) {
		return firmaRepository.save(firma);
	}
	
	public Collection<Firma> findByKategorija(String kategorija) {
		return firmaRepository.findByKategorija(kategorija);
	}
	
	public void deleteAll() {
		firmaRepository.deleteAll();
	}
	
	public Collection<Firma> findByKategorijaAndIdNotIn(String kategorija, Collection<Long> ids) {
		return firmaRepository.findByKategorijaAndIdNotIn(kategorija, ids);
	}
	
}
