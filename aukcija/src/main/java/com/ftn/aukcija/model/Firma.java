package com.ftn.aukcija.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gs.collections.impl.list.mutable.ArrayListAdapter;

@Entity
public class Firma implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ime;
	
	private Integer udaljenost;
	
	private String kategorija;
	
	@ManyToOne
	private Korisnik agent;
	
	public Firma() {

	}
	
	public Firma(Long id, String ime, Integer udaljenost, String kategorija, Korisnik agent) {
		super();
		this.id = id;
		this.ime = ime;
		this.udaljenost = udaljenost;
		this.kategorija = kategorija;
		this.agent = agent;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Integer getUdaljenost() {
		return udaljenost;
	}

	public void setUdaljenost(Integer udaljenost) {
		this.udaljenost = udaljenost;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public Korisnik getAgent() {
		return agent;
	}

	public void setAgent(Korisnik agent) {
		this.agent = agent;
	}
	
}
