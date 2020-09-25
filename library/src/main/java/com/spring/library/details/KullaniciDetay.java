package com.spring.library.details;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.library.entities.Kullanici;
import com.spring.library.entities.Rol;

public class KullaniciDetay implements UserDetails {

	private Kullanici kullanici;
	
	public KullaniciDetay(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Rol> roller = kullanici.getRoller();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Rol rol : roller) {
			authorities.add(new SimpleGrantedAuthority(rol.getRolAdi()));
		}
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return kullanici.getSifre();
	}

	@Override
	public String getUsername() {
		return ""+ kullanici.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return kullanici.isIzin();
	}

}

