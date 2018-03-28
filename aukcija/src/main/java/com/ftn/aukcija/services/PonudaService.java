package com.ftn.aukcija.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.aukcija.model.Ponuda;
import com.ftn.aukcija.repository.PonudaRepository;

@Service
public class PonudaService {

	@Autowired
	private PonudaRepository ponudaRepository;
	
	public Ponuda save(Ponuda ponuda) {
		return ponudaRepository.save(ponuda);
	}
	
	public List<Ponuda> getAllActiveOffers(Long id) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String todaysDate = dateFormat.format(date);
		return (List<Ponuda>) ponudaRepository.findByFirma_Agent_IdAndZahtevZaNabavku_RokZaPonudeGreaterThan(id, todaysDate);
	}
	
}
