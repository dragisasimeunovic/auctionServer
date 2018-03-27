package com.ftn.aukcija.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Korisnik implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ime;
	
	private String prezime;
	
	@Column(unique = true)
	private String email;
	
	private String sifra;
	
	@Column(unique = true)
	private String korisnickoIme;
	
	private String grad;
	
	private String adresa;
	
	private Double latitude;
	
	private Double longitude;
	
	private String postanskiBroj;
	
	private String tip;
	
	private boolean potvrdjenMail;
	
	@ManyToOne
	private Firma firma;
	
	public Korisnik() {
	}

	public Korisnik(Long id, String ime, String prezime, String email, String sifra, String korisnickoIme, String grad,
			String adresa, Double latitude, Double longitude, String postanskiBroj, String tip, boolean potvrdjenMail,
			Firma firma) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.sifra = sifra;
		this.korisnickoIme = korisnickoIme;
		this.grad = grad;
		this.adresa = adresa;
		this.latitude = latitude;
		this.longitude = longitude;
		this.postanskiBroj = postanskiBroj;
		this.tip = tip;
		this.potvrdjenMail = potvrdjenMail;
		this.firma = firma;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isPotvrdjenMail() {
		return potvrdjenMail;
	}

	public void setPotvrdjenMail(boolean potvrdjenMail) {
		this.potvrdjenMail = potvrdjenMail;
	}

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getPostanskiBroj() {
		return postanskiBroj;
	}

	public void setPostanskiBroj(String postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}
	
}
