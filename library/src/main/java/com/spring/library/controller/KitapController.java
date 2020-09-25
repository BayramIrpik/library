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
import org.springframework.web.servlet.ModelAndView;

import com.spring.library.entities.Kitap;
import com.spring.library.entities.Kullanici;
import com.spring.library.services.KitapService;
import com.spring.library.services.KullaniciService;

@Controller
public class KitapController {
	@Autowired
	private KitapService kitapService;

	@Autowired
	private KullaniciService kullaniciService;

	@RequestMapping("/kitap")
	public String viewKitap(Model model) {
		List<Kitap> listkitap = kitapService.listSilinmemisKitap();
		model.addAttribute("listkitap", listkitap);

		model.addAttribute("kitap", new Kitap());

		return "kitap";
	}

	@RequestMapping(value = "/yeniKitap", method = RequestMethod.POST)
	public String saveKitap(@ModelAttribute("kitap") Kitap kitap) {
		Kullanici kullanici = kullaniciService.get(2L);
		kitap.setDurum(true);
		kitap.setKullanici(kullanici);
		kitapService.save(kitap);

		return "redirect:/kitap";
	}

	@RequestMapping("/kitapDuzenle/{id}")
	public String showEditForm(Model model, @PathVariable(name = "id") Long id) {


		Kitap kitap = kitapService.get(id);
		model.addAttribute("kitap", kitap);

		return "kitapDuzenle";
	}

	@RequestMapping("/deleteKitap/{id}")
	public String deleteKitap(@PathVariable(name = "id") Long id) {
		Kitap kitap = kitapService.get(id);
		kitap.setDurum(false);
		kitapService.save(kitap);
		return "redirect:/kitap";
	}

	@RequestMapping("/rezervasyon/{id}")
	public String rezervasyon(@PathVariable(name = "id") Long id, Principal principal) {
		String s = principal.getName();
		Long kullaniciId = Long.parseLong(s);

		Kullanici kullanici = new Kullanici();
		kullanici = kullaniciService.get(kullaniciId);

		Kitap kitap = kitapService.get(id);
		kitap.setKullanici(kullanici);
		kitap.setRezerve(true);
		kitapService.save(kitap);

		return "redirect:/odunc";
	}

	@RequestMapping("/rezervasyonIptal/{id}")
	public String rezervasyonIptal(@PathVariable(name = "id") Long id) {
		Kullanici kullanici = kullaniciService.get(2L);

		Kitap kitap = kitapService.get(id);
		kitap.setKullanici(kullanici);
		kitap.setRezerve(false);
		kitapService.save(kitap);

		return "redirect:/odunc";
	}

	@RequestMapping("/odunc")
	public String kitapOduncVerme(Model model, Principal principal) {

		List<Kitap> listkitap = kitapService.listSilinmemisKitap();
		model.addAttribute("listkitap", listkitap);
		String kullaniciId = principal.getName();

		model.addAttribute("kullaniciId", kullaniciId);
		return "odunc";
	}

	@RequestMapping("/kitapAlVer")
	public String kitapAlVer(Model model) {

		List<Kitap> listkitap = kitapService.listRezerveAlınmısKitaplar();
		model.addAttribute("listkitap", listkitap);

		return "kitapAlVer";
	}

	@RequestMapping("/iadeAl/{id}")
	public String iadeAl(@PathVariable(name = "id") Long id) {
		Kullanici kullanici = kullaniciService.get(2L);

		Kitap kitap = kitapService.get(id);
		kitap.setKullanici(kullanici);
		kitap.setOduncVerilme(false);
		kitap.setRezerve(false);
		kitapService.save(kitap);

		return "redirect:/kitapAlVer";
	}

	@RequestMapping("/oduncVer/{id}")
	public String oduncVer(@PathVariable(name = "id") Long id) {
		Kitap kitap = kitapService.get(id);
		kitap.setOduncVerilme(true);
		kitap.setRezerve(false);
		kitapService.save(kitap);

		return "redirect:/kitapAlVer";
	}
}
