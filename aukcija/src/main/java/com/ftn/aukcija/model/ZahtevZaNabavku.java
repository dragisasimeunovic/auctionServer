package com.ftn.aukcija.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ZahtevZaNabavku implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String kategorija;
	
	private String opis;
	
	private Double maxVrijednost;
	
	private String rokZaPonude;
	
	private Integer maxBrojPonuda;
	
	private String rokZaNabavku;
	
	@ManyToOne
	private Korisnik korisnik;
	
	public ZahtevZaNabavku() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Double getMaxVrijednost() {
		return maxVrijednost;
	}

	public void setMaxVrijednost(Double maxVrijednost) {
		this.maxVrijednost = maxVrijednost;
	}

	public String getRokZaPonude() {
		return rokZaPonude;
	}

	public void setRokZaPonude(String rokZaPonude) {
		this.rokZaPonude = rokZaPonude;
	}

	public Integer getMaxBrojPonuda() {
		return maxBrojPonuda;
	}

	public void setMaxBrojPonuda(Integer maxBrojPonuda) {
		this.maxBrojPonuda = maxBrojPonuda;
	}

	public String getRokZaNabavku() {
		return rokZaNabavku;
	}

	public void setRokZaNabavku(String rokZaNabavku) {
		this.rokZaNabavku = rokZaNabavku;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	
}
