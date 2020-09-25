package com.spring.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.library.entities.Kitap;
import com.spring.library.repositories.KitapRepository;



@Service
public class KitapService {
	
	@Autowired
	private KitapRepository repo;
	
	public List<Kitap> listAll() {		
		return repo.findAll();
	}
	
	public void save(Kitap kitap) {
		repo.save(kitap);
	}
	
	public Kitap get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}

	public List<Kitap> listSilinmemisKitap() {		
		return repo.listSilinmemisKitaplar();
	}
	
	public List<Kitap> listRezerveAlınmısKitaplar() {		
		return repo.listRezerveAlınmısKitaplar();
	}
}
