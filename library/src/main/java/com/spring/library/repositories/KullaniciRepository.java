package com.spring.library.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.library.entities.Kullanici;

public interface KullaniciRepository extends CrudRepository<Kullanici, Long> {

	@Query("SELECT k FROM Kullanici k WHERE k.eposta = :eposta")
	public Kullanici getKullaniciByEposta(@Param("eposta") String eposta);
	
	@Query("SELECT k FROM Kullanici k WHERE k.durum = 1")
	public List<Kullanici> listTumAktiveKullanicilar();
}
