package com.spring.library.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kitap")
public class Kitap {
	
	@Id
	@Column(name = "kitap_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String kitapAdi;
	
	private String kitapAltAdi;
	
	private String kitapSeriAdi;
	
	private String yazarAdi;
	
	private String yazarAciklama;
	
	private String yayineviAdi;
	
	private String yayinEviAciklama;
	
	private String isbn;
	
	private boolean oduncVerilme;
	
	private boolean durum;
	
	private boolean rezerve;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Kullanici kullanici;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKitapAdi() {
		return kitapAdi;
	}

	public void setKitapAdi(String kitapAdi) {
		this.kitapAdi = kitapAdi;
	}

	public String getKitapAltAdi() {
		return kitapAltAdi;
	}

	public void setKitapAltAdi(String kitapAltAdi) {
		this.kitapAltAdi = kitapAltAdi;
	}

	public String getKitapSeriAdi() {
		return kitapSeriAdi;
	}

	public void setKitapSeriAdi(String kitapSeriAdi) {
		this.kitapSeriAdi = kitapSeriAdi;
	}

	public String getYazarAdi() {
		return yazarAdi;
	}

	public void setYazarAdi(String yazarAdi) {
		this.yazarAdi = yazarAdi;
	}

	public String getYazarAciklama() {
		return yazarAciklama;
	}

	public void setYazarAciklama(String yazarAciklama) {
		this.yazarAciklama = yazarAciklama;
	}

	public String getYayineviAdi() {
		return yayineviAdi;
	}

	public void setYayineviAdi(String yayineviAdi) {
		this.yayineviAdi = yayineviAdi;
	}

	public String getYayinEviAciklama() {
		return yayinEviAciklama;
	}

	public void setYayinEviAciklama(String yayinEviAciklama) {
		this.yayinEviAciklama = yayinEviAciklama;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean isOduncVerilme() {
		return oduncVerilme;
	}

	public void setOduncVerilme(boolean oduncVerilme) {
		this.oduncVerilme = oduncVerilme;
	}

	public boolean isDurum() {
		return durum;
	}

	public void setDurum(boolean durum) {
		this.durum = durum;
	}
	
	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	public boolean isRezerve() {
		return rezerve;
	}

	public void setRezerve(boolean rezerve) {
		this.rezerve = rezerve;
	}

}
