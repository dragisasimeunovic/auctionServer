package com.ftn.aukcija.services;

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
	
}
