package com.spring.library.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.library.entities.Kullanici;
import com.spring.library.entities.Rol;
import com.spring.library.services.KullaniciService;
import com.spring.library.services.RolService;




@Controller
public class KullaniciController {
	@Autowired
	private KullaniciService kullaniciService;
	
	@Autowired
	private RolService rolService;

	@RequestMapping("/")
	public String viewAnaSayfa(Model model) {
		
		
		return "index";
	}
	
	@RequestMapping("/kisi")
	public String viewKisi(Model model) {
		List<Kullanici> listKullanicilar = kullaniciService.listTumAktiveKullanicilar();
		model.addAttribute("listKullanicilar", listKullanicilar);
		
		List<Rol> roller = rolService.listAll();
		model.addAttribute("roller", roller);
		
		  model.addAttribute("kullanici", new Kullanici());
		
		return "kisi";
	}
	
	@RequestMapping(value = "/yeniKullanici", method = RequestMethod.POST)
	public String yeniKullanici(@ModelAttribute("kullanici") Kullanici kullanici) {
		
		String encryptedSifre = kullaniciService.sifreEncode(kullanici.getEposta());
		kullanici.setSifre(encryptedSifre);
		kullanici.setIzin(true);
		kullanici.setDurum(true);
		kullaniciService.save(kullanici);
		
		return "redirect:/kisi";
	}
	
		
	@RequestMapping("/kullaniciDuzenle/{id}")
	public String showEditForm(Model model,@PathVariable(name = "id") Long id) {
		
		Kullanici kullanici = kullaniciService.get(id);
		 model.addAttribute("kullanici", kullanici);
		 
		 List<Rol> roller = rolService.listAll();
			model.addAttribute("roller", roller);
		
		return "kullaniciDuzenle";
	}	
	
	@RequestMapping("/deleteKullanici/{id}")
	public String deleteKisi(@PathVariable(name = "id") Long id) {
		Kullanici kullanici=kullaniciService.get(id);
		kullanici.setDurum(false);
		kullanici.setIzin(false);
		kullaniciService.save(kullanici);
		return "redirect:/kisi";
	}
	
	@RequestMapping("/hesabiKitle/{id}")
	public String hesabiKitle(@PathVariable(name = "id") Long id) {
		Kullanici kullanici=kullaniciService.get(id);
		
		kullanici.setIzin(false);
		kullaniciService.save(kullanici);
		return "redirect:/kisi";
	}
	
	@RequestMapping("/hesabiAc/{id}")
	public String hesabiAc(@PathVariable(name = "id") Long id) {
		Kullanici kullanici=kullaniciService.get(id);
		
		kullanici.setIzin(true);
		kullaniciService.save(kullanici);
		return "redirect:/kisi";
	}
	
	@RequestMapping("/sifre/{id}")
	public String sifre(Model model,@PathVariable(name = "id") Long id) {
		String mesaj="";
		if(id==1L)
			mesaj= "Eski şifreniz yanlış işlem başarısız.";
		
		if(id==2L)
			mesaj= "Yeni şifreniz aynı değil işlem başarısız.";
		
		if(id==3L)
			mesaj= "Şifre başarılı bir şekilde değişti.";
		
		model.addAttribute("mesaj", mesaj);
		
		return "sifre";
	}
	
	@RequestMapping("/sifreDegistir")
	public String sifreDegistir(Model model,
			@RequestParam("eskiSifre") String eskiSifre,
			@RequestParam("yeniSifre") String yeniSifre,
			@RequestParam("yeniSifreTekrar") String yeniSifreTekrar,
			Principal principal) {
		String s=principal.getName();
		Long kullaniciId=Long.parseLong(s);
		
		Kullanici kullanici=new Kullanici();
		kullanici=kullaniciService.get(kullaniciId);
		
		if(kullaniciService.sifreKontrol(eskiSifre, kullanici.getSifre())) {
			
			if(yeniSifre.equals(yeniSifreTekrar)) {
				
				kullanici.setSifre(kullaniciService.sifreEncode(yeniSifre));
				
				kullaniciService.save(kullanici);
				return "redirect:/sifre/3";
			}
			return "redirect:/sifre/2";
		}
		
		return "redirect:/sifre/1";
	}
	
}