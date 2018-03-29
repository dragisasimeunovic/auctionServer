package com.ftn.aukcija.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.aukcija.constants.Constants;
import com.ftn.aukcija.model.Ponuda;
import com.ftn.aukcija.model.ZahtevZaNabavku;
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
	
	public List<Ponuda> getAllSentOffersForRequest(ZahtevZaNabavku zahtevZaNabavku) {
		return (List<Ponuda>) ponudaRepository.findByZahtevZaNabavkuAndStatus(zahtevZaNabavku, Constants.PONUDA_POSLATA);
	}
	
	public List<Ponuda> getAllWaitingOffersForRequest(ZahtevZaNabavku zahtevZaNabavku) {
		return (List<Ponuda>) ponudaRepository.findByZahtevZaNabavkuAndStatus(zahtevZaNabavku, Constants.PONUDA_CEKANJE);
	}
	
	public Collection<Ponuda> getAllSentOrRefusedOffersForRequest(ZahtevZaNabavku zahtevZaNabavku) {
		ArrayList<String> statusi = new ArrayList<>();
		statusi.add(Constants.PONUDA_POSLATA);
		statusi.add(Constants.PONUDA_ODBIJENA);
		return ponudaRepository.findByZahtevZaNabavkuAndStatusIn(zahtevZaNabavku, statusi);
	}
	
}
