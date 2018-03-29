package com.ftn.aukcija.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.aukcija.model.Firma;
import com.ftn.aukcija.model.Korisnik;
import com.ftn.aukcija.model.Ponuda;
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
	
	@Autowired
	private PonudaService ponudaService;
	
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
	
	public Collection<Firma> getFirmsForAuctionExtraTime(ZahtevZaNabavku zahtevZaNabavku) {
		ArrayList<Long> firmeKojeSeNeUklapaju = new ArrayList<>();
		Collection<Ponuda> waitingOffers = ponudaService.getAllSentOrRefusedOffersForRequest(zahtevZaNabavku);
		for (Ponuda ponuda : waitingOffers) {
			firmeKojeSeNeUklapaju.add(ponuda.getFirma().getId());
		}
		
		Collection<Firma> firmsFromCategory = firmaService.findByKategorijaAndIdNotIn(zahtevZaNabavku.getKategorija(), firmeKojeSeNeUklapaju);
		ArrayList<Firma> candidates = new ArrayList<>();
		
		double latitude = zahtevZaNabavku.getKorisnik().getLatitude();
		double longitude = zahtevZaNabavku.getKorisnik().getLongitude();
		
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
