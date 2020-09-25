package com.spring.library.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.library.entities.Rol;
import com.spring.library.repositories.RolRepository;


@Service
public class RolService {
	
	@Autowired
	private RolRepository repo;
	
	public List<Rol> listAll() {		
		return repo.findAll();
	}
}
