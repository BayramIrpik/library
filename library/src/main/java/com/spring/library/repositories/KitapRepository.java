package com.spring.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.library.entities.Kitap;

public interface KitapRepository extends JpaRepository<Kitap, Long>{

	@Query("SELECT k FROM Kitap k WHERE k.durum = 1")
	public List<Kitap> listSilinmemisKitaplar();
	
	@Query("SELECT k FROM Kitap k WHERE (k.rezerve= 1 or k.oduncVerilme=1) and k.durum = 1")
	public List<Kitap> listRezerveAlınmısKitaplar();
}
