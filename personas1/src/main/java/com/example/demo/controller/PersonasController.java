package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.example.demo.repository.PersonasRepository;

@Controller
public class PersonasController {
	@Autowired
	private PersonasRepository PersonaRepository;

	@GetMapping("/personas1")
	public String listarPersonas(Model model) {
		model.addAttribute("personas1", PersonaRepository.findAll());
		return "index";
	}



	@GetMapping("eliminar/(id)")
	public String delete(Model model, @PathVariable long id) {
		   PersonaRepository.deleteById(id);
		   return "redirect:/index";
		}
		}