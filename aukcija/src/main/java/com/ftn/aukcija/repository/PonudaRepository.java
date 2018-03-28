package com.ftn.aukcija.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.aukcija.model.Ponuda;

public interface PonudaRepository extends JpaRepository<Ponuda, Long>{

	Collection<Ponuda> findByFirma_Agent_IdAndZahtevZaNabavku_RokZaPonudeGreaterThan(Long id, String today);

}