package com.ftn.aukcija.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.aukcija.model.ZahtevZaNabavku;
import com.ftn.aukcija.repository.ZahtevZaNabavkuRepository;

@Service
public class NabavkaService {
	
	@Autowired
	private ZahtevZaNabavkuRepository zahtevZaNabavkuRepository;
	
	public ZahtevZaNabavku save(ZahtevZaNabavku zahtevZaNabavku) {
		return zahtevZaNabavkuRepository.save(zahtevZaNabavku);
	}
	

}
