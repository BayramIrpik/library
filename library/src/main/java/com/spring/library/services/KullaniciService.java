package com.spring.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.library.details.KullaniciDetay;
import com.spring.library.entities.Kullanici;
import com.spring.library.repositories.KullaniciRepository;

@Service
public class KullaniciService implements UserDetailsService {

	@Autowired
	private KullaniciRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
	
	Kullanici kullanici = repo.getKullaniciByEposta(username);
		
		if (kullanici == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		return new KullaniciDetay(kullanici);
	}
	
	
	public List<Kullanici> listAll() {		
		return (List<Kullanici>) repo.findAll();
	}
	
	public void save(Kullanici kullanici) {
		repo.save(kullanici);
	}
	
	public Kullanici get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public String sifreEncode(String eposta) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
		String encodedPassword = encoder.encode(eposta);
		
		return encodedPassword;
	}
	
	public List<Kullanici> listTumAktiveKullanicilar() {		
		return (List<Kullanici>) repo.listTumAktiveKullanicilar();
	}
	
	public boolean sifreKontrol(String sifre,String encoded) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(sifre, encoded);
	}

}

