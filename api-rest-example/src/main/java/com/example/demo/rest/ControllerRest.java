package com.example.demo.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.models.Producto;

@RestController //Indica que esta clase va a ser un servicio REST
@RequestMapping("/") //Indica en qué URL se van a exponer los servicios de esta clase
public class ControllerRest {
	
	@GetMapping
	public ResponseEntity<Producto> getProducto() {
		Producto producto = new Producto();
		producto.setId(1);
		producto.setName("Producto 1");
		return ResponseEntity.ok(producto);
	}
	
	//@GetMapping("hello") //Servicio disponible mediante GET (localhost: 8080/)
	//@RequestMapping(value="hello", method=RequestMethod.GET)
	public String hello() {
		return "Hello World!";
	}

}
