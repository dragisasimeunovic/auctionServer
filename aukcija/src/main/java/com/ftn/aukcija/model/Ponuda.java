package com.ftn.aukcija.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ponuda implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ZahtevZaNabavku zahtevZaNabavku;
	
	@ManyToOne
	private Firma firma;
	
	private String status;
	
	private Double cijena;
	
	private String rokZaIzvrsavanje;
	
	
	public Ponuda() {

	}

	public Ponuda(Long id, ZahtevZaNabavku zahtevZaNabavku, Firma firma, String status, Double cijena,
			String rokZaIzvrsavanje) {
		super();
		this.id = id;
		this.zahtevZaNabavku = zahtevZaNabavku;
		this.firma = firma;
		this.status = status;
		this.cijena = cijena;
		this.rokZaIzvrsavanje = rokZaIzvrsavanje;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZahtevZaNabavku getZahtevZaNabavku() {
		return zahtevZaNabavku;
	}

	public void setZahtevZaNabavku(ZahtevZaNabavku zahtevZaNabavku) {
		this.zahtevZaNabavku = zahtevZaNabavku;
	}

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getCijena() {
		return cijena;
	}

	public void setCijena(Double cijena) {
		this.cijena = cijena;
	}

	public String getRokZaIzvrsavanje() {
		return rokZaIzvrsavanje;
	}

	public void setRokZaIzvrsavanje(String rokZaIzvrsavanje) {
		this.rokZaIzvrsavanje = rokZaIzvrsavanje;
	}
	
}
