package com.ftn.aukcija.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.aukcija.model.Ponuda;
import com.ftn.aukcija.model.ZahtevZaNabavku;

public interface PonudaRepository extends JpaRepository<Ponuda, Long>{

	Collection<Ponuda> findByFirma_Agent_IdAndZahtevZaNabavku_RokZaPonudeGreaterThan(Long id, String today);
	
	Collection<Ponuda> findByZahtevZaNabavkuAndStatus(ZahtevZaNabavku zahtevZaNabavku, String status);
	
	Collection<Ponuda> findByZahtevZaNabavkuAndStatusIn(ZahtevZaNabavku zahtevZaNabavku, Collection<String> statusi);
	

}