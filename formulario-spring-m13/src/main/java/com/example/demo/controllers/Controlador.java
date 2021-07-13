package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //Lo convertimos en un servlet que atiende peticiones HTTP
@RequestMapping("")
public class Controlador {

	@GetMapping("/")
	public String iniciar() {
		return "Login";
	}

	@PostMapping("/")
	public String login() {
		return "Consulta";
	}

}