package com.ftn.aukcija.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.aukcija.model.Firma;
import com.ftn.aukcija.model.Korisnik;
import com.ftn.aukcija.model.ZahtevZaNabavku;
import com.ftn.aukcija.repository.ZahtevZaNabavkuRepository;

@Service
public class NabavkaService {
	
	@Autowired
	private ZahtevZaNabavkuRepository zahtevZaNabavkuRepository;
	
	@Autowired
	private FirmaService firmaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	public ZahtevZaNabavku save(ZahtevZaNabavku zahtevZaNabavku) {
		return zahtevZaNabavkuRepository.save(zahtevZaNabavku);
	}
	
	public Collection<Firma> getFirmsForAuction(ZahtevZaNabavku zahtevZaNabavku, Long korisnikID){
		
		Collection<Firma> firmsFromCategory = firmaService.findByKategorija(zahtevZaNabavku.getKategorija());
		ArrayList<Firma> candidates = new ArrayList<>();
		
		Korisnik korisnik = korisnikService.findById(korisnikID);
		zahtevZaNabavku.setKorisnik(korisnik);
		
		double latitude = korisnik.getLatitude();
		double longitude = korisnik.getLongitude();
		
		for (Firma firma : firmsFromCategory) {
			Integer distance = (int) DistanceService.distance(latitude, longitude, firma.getAgent().getLatitude(), firma.getAgent().getLongitude(), 'K');
			if (distance > firma.getUdaljenost()) {
				continue;
			}
			candidates.add(firma);
		}
		
		return candidates;
		
	}

}
