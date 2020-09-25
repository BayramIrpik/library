package com.spring.library.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "kullanici")
public class Kullanici {
	
	@Id
	@Column(name = "kullanici_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String kullaniciAdi;
	
	private String kullaniciSoyadi;
	
	private String eposta;
	
	private String sifre;
	
	private boolean izin;
	
	private boolean durum;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "kullanici_roller",
			joinColumns = @JoinColumn(name = "kullanici_id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id")
			)
	private Set<Rol> roller = new HashSet<>();

	
	@OneToMany(mappedBy = "kullanici", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Kitap> kitap;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getKullaniciSoyadi() {
		return kullaniciSoyadi;
	}

	public void setKullaniciSoyadi(String kullaniciSoyadi) {
		this.kullaniciSoyadi = kullaniciSoyadi;
	}

	public String getEposta() {
		return eposta;
	}

	public void setEposta(String eposta) {
		this.eposta = eposta;
	}

	public String getSifre() {
		return sifre;
	}

	public void setSifre(String sifre) {
		this.sifre = sifre;
	}

	public boolean isIzin() {
		return izin;
	}

	public void setIzin(boolean izin) {
		this.izin = izin;
	}

	public Set<Rol> getRoller() {
		return roller;
	}

	public void setRoller(Set<Rol> roller) {
		this.roller = roller;
	}

	public boolean isDurum() {
		return durum;
	}

	public void setDurum(boolean durum) {
		this.durum = durum;
	}
	
	public String getRolAdlari(Set<Rol> roller) {
		String rolAdlari="";
		for(Rol rol:roller) {
			rolAdlari+=rol.getRolAdi()+" ";
		}
		
		return rolAdlari;
	}

	public Set<Kitap> getKitap() {
		return kitap;
	}

	public void setKitap(Set<Kitap> kitap) {
		this.kitap = kitap;
	}

}
